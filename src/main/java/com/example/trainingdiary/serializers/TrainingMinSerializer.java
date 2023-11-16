package com.example.trainingdiary.serializers;

import com.example.trainingdiary.models.ExerciseType;
import com.example.trainingdiary.models.TrainingTemplate;

import java.util.List;

public class TrainingMinSerializer {
    public static String serialize(TrainingTemplate trainingTemplate){
        String json = "{";
        json += "\"id\": " + trainingTemplate.getId() + ", ";
        json += "\"title\": \"" + trainingTemplate.getTitle() + "\",";
        json += "\"difficult\": " + trainingTemplate.getDifficult() + ",";
        json += "\"calories\": " + trainingTemplate.getCalories() + ",";
        json += "\"time\": " + trainingTemplate.getTime();
        json += "}";
        return json;
    }

    public static String serializeList(List<TrainingTemplate> trainingTemplateList){
        String json = "[ ";
        for (int i = 0; i < trainingTemplateList.size(); i++){
            json += TrainingMinSerializer.serialize(trainingTemplateList.get(i));
            if(i < trainingTemplateList.size() -1){
                json += ", ";
            }
        }

        json += " ] ";
        return json;
    }
}
