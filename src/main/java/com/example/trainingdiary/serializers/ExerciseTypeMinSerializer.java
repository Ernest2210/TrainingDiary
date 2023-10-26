package com.example.trainingdiary.serializers;

import com.example.trainingdiary.models.ExerciseType;

import java.util.List;

public class ExerciseTypeMinSerializer {
    public static String serialize(ExerciseType exerciseType){
        String json = "{";
        json += "\"id\": " + exerciseType.getId() + ", ";
        json += "\"title\": \"" + exerciseType.getTitle() + "\"";
        json += "}";
        return json;
    }

    public static String serializeList(List<ExerciseType> exerciseTypeList){
        String json = "[ ";
        for (int i = 0; i < exerciseTypeList.size(); i++){
            json += ExerciseTypeMinSerializer.serialize(exerciseTypeList.get(i));
            if(i < exerciseTypeList.size() -1){
                json += ", ";
            }
        }

        json += " ] ";
        return json;
    }
}
