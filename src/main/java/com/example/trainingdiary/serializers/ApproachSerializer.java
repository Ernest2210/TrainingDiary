package com.example.trainingdiary.serializers;

import com.example.trainingdiary.models.Approach;

import java.util.List;

public class ApproachSerializer {
    public static String serialize(Approach approach){
        String json = "{ ";
        json += "\"id\": " + approach.getId() + ",";
        json += "\"count\": " + approach.getCount() + ",";
        json += "\"weight\": " + approach.getWeight();
        json += "}";
        return json;
    }

    public static String serializeList(List<Approach> approachList){
        String json = "[ ";
        for (int i = 0; i < approachList.size(); i++){
            json += ApproachSerializer.serialize(approachList.get(i));
            if(i < approachList.size() -1){
                json += ", ";
            }
        }

        json += " ] ";
        return json;
    }
}
