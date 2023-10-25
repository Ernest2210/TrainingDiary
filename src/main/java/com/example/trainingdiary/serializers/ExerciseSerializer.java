package com.example.trainingdiary.serializers;

import com.example.trainingdiary.models.Approach;
import com.example.trainingdiary.models.Exercise;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.zip.DataFormatException;

public class ExerciseSerializer {
    public static String serialize(Exercise exercise){
        String json = "{ ";
        json += "\"id\": " + exercise.getId() + ", ";
        json += "\"user_id\": " + exercise.getUserId() + ", ";
        json += "\"exercise_type\": " + ExerciseTypeMinSerializer.serialize(exercise.getExerciseType()) + ", ";
        json += "\"date\": \"" + exercise.getDate().toString() + "\", ";
        json += "\"comment\": \"" + exercise.getComment() + "\", ";
        json += "\"difficult\": " + exercise.getDifficult() + ", ";
        json += "\"approaches\" : " + ApproachSerializer.serializeList(exercise.getApproaches());
        json += "}";
        return json;
    }

    public static String serializeList(List<Exercise> exerciseList){
        String json = "[";
        for (int i = 0; i < exerciseList.size(); i++){
            json += serialize(exerciseList.get(i));
            if(i < exerciseList.size() - 1){
                json += ", ";
            }
        }
        json += " ] ";
        return json;
    }
}
