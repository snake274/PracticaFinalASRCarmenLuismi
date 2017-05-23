package wasdev.sample;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

import okhttp3.HttpUrl;




public class Imagen 
{
	public static String analyze(String texto) throws MalformedURLException
	{
		VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
		service.setEndPoint("https://gateway-a.watsonplatform.net/visual-recognition/api");
		service.setApiKey("b1872c0dab4dce84de2805637c5c413425f3b8e2");
		


		ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
		    .url(texto)
		    .build();
		VisualClassification result = service.classify(options).execute();
		System.out.println(result);
		
		String traduccionJSON = result.toString();
		
		System.out.println(traduccionJSON+"\n--- ----\n");
		
		JsonParser parser = new JsonParser();
		JsonObject rootObj = parser.parse(traduccionJSON).getAsJsonObject();
	//	String wordCount = rootObj.get("word_count").getAsString();
		JsonArray images = rootObj.getAsJsonArray("images");
		JsonObject clasificadores = images.get(0).getAsJsonObject();
		JsonArray clasificadores2 = clasificadores.getAsJsonArray("classifiers");
		JsonArray clases = clasificadores2.get(0).getAsJsonObject().getAsJsonArray("classes");
		List<String> listaClass = new ArrayList<String>();
		List<String> listaScore = new ArrayList<String>();
		for(int i = 0; i < clases.size(); i++){
		    listaClass.add(clases.get(i).getAsJsonObject().get("class").toString());
		    listaScore.add(clases.get(i).getAsJsonObject().get("score").toString());
		}
		
		double max = 0;
		for(int i=0; i<listaClass.size(); i++){
			if(max < Double.parseDouble(listaScore.get(i)))
				max = Double.parseDouble(listaScore.get(i));
		}
		int index = listaScore.indexOf(String.valueOf(max));
		
		String resultado = listaClass.get(index).replace("\"","");
		
		LanguageTranslator service2 = new LanguageTranslator();
		service2.setUsernameAndPassword("244b9afc-bfa0-441c-b94c-cea345a754e7", "YNRZyG3Um0cB");
		service2.setEndPoint("https://gateway.watsonplatform.net/language-translator/api");
		TranslationResult translationResult = service2.translate(
		  resultado, Language.ENGLISH, Language.SPANISH)
		  .execute();
		
		String traduccionJSON2 = translationResult.toString();
		
		System.out.println(traduccionJSON2+"\n--- ----\n");
		
		JsonParser parser2 = new JsonParser();
		JsonObject rootObj2 = parser.parse(traduccionJSON2).getAsJsonObject();
		String wordCount2 = rootObj2.get("word_count").getAsString();
		JsonArray traducciones2 = rootObj2.getAsJsonArray("translations");
		String traduccionPrimera = resultado;
		if(traducciones2.size()>0)
			traduccionPrimera = traducciones2.get(0).getAsJsonObject().get("translation").getAsString();
		
		return traduccionPrimera;
		
	}
}
