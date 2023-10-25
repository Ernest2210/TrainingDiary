package com.example.trainingdiary.serializers;

import com.example.trainingdiary.models.ExerciseType;

public class ExerciseTypeMinSerializer {
    public static String serialize(ExerciseType exerciseType){
        String json = "{";
        json += "\"id\": " + exerciseType.getId() + ", ";
        json += "\"title\": \"" + exerciseType.getTitle() + "\"";
        json += "}";
        return json;
    }
}
