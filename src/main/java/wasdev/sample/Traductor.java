package wasdev.sample;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;

public class Traductor
{
	public static String translate(String palabra)
	{
		LanguageTranslator service = new LanguageTranslator();
		service.setUsernameAndPassword("244b9afc-bfa0-441c-b94c-cea345a754e7", "YNRZyG3Um0cB");
		
		service.setEndPoint("https://gateway.watsonplatform.net/language-translator/api");
		TranslationResult translationResult = service.translate(palabra, Language.SPANISH, Language.ENGLISH).execute();
		
		String traduccionJSON = translationResult.toString();
		
		System.out.println(traduccionJSON+"\n-----------\n");
		
		JsonParser parser = new JsonParser();
		JsonObject rootObj = parser.parse(traduccionJSON).getAsJsonObject();
		String wordCount = rootObj.get("word_count").getAsString();
		JsonArray traducciones = rootObj.getAsJsonArray("translations");
		String traduccionPrimera = palabra;
		if(traducciones.size()>0)
			traduccionPrimera = traducciones.get(0).getAsJsonObject().get("translation").getAsString();
			
			return traduccionPrimera;
	}
}