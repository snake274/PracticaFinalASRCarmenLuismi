package wasdev.sample;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;

public class Sentimiento 
{
	public static String analyze(String texto)
	{
		ToneAnalyzer service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19);
		service.setUsernameAndPassword("d6808083-3e24-47dd-97e1-47b587a1c5af", "vg1BYzoshJYx");
		


		ToneAnalysis tone = service.getTone(texto, null).execute();
		
		
		String traduccionJSON = tone.toString();
		
		//System.out.println(traduccionJSON+"\n--- ----\n");
		
		JsonParser parser = new JsonParser();
		JsonObject rootObj = parser.parse(traduccionJSON).getAsJsonObject();
		
		System.out.println("Objeto JSON " +rootObj);
		
		JsonObject document_tone = rootObj.getAsJsonObject("document_tone");
		
		System.out.println("Document Tone: " + document_tone);
		
		JsonArray tone_categories = document_tone.getAsJsonArray("tone_categories");
		System.out.println("Tone Categories: " + tone_categories);
		JsonObject categorias = tone_categories.get(0).getAsJsonObject();
		System.out.println("Categories: " + categorias);
		JsonArray tones = categorias.getAsJsonArray("tones");
		System.out.println("Tonos: " + tones);
                
                List<String> listaTonos = new ArrayList<String>();
		List<String> listaScores = new ArrayList<String>();
		for(int i = 0; i < tones.size(); i++){
		    listaTonos.add(tones.get(i).getAsJsonObject().get("tone_id").toString());
		    listaScores.add(tones.get(i).getAsJsonObject().get("score").toString());
		}
		System.out.println("Lista Clases: " + listaTonos);
		System.out.println(listaTonos.size());
		System.out.println("Lista Scores: " + listaScores);
                System.out.println(listaScores.size());
                
                double max = 0;
		for(int i=0; i<listaTonos.size(); i++){
			if(max < Double.parseDouble(listaScores.get(i)))
				max = Double.parseDouble(listaScores.get(i));
		}
		int index = listaScores.indexOf(String.valueOf(max));
		
		String resultado = listaTonos.get(index).replace("\"","");
		
		System.out.println(resultado);
                String resultadoLike = new String();
                if(resultado.equals("joy"))
                    resultadoLike="like";
                else
                    resultadoLike="dislike";
                
        System.out.println(resultadoLike);

		
		return resultadoLike;
	}
}