package QuizJava;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Random;

public class Helper {
    public JSONArray generateFiveQuestion(JSONArray jsonArray){
        Random random = new Random();
        JSONArray questionsArray = new JSONArray();
        while (questionsArray.size()<5){
            int randomIndex = random.nextInt(15);
            JSONObject jsonObject= (JSONObject) jsonArray.get(randomIndex);
            if(!questionsArray.contains(jsonObject)){
                questionsArray.add(jsonObject);
            }
        }
        return questionsArray;
    }
}
