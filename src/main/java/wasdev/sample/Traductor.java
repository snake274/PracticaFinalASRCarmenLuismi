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
		service.setUsernameAndPassword("6533994a-2a3f-43af-a594-5231b4ca7da5", "wEca1MCK7kDP");
		
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