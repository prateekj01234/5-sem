package com.example.parveenjain.trainservice.parser;


import com.example.parveenjain.trainservice.model.trainRouteModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class trainRouteParser {

    public static ArrayList<trainRouteModel> parseFeed(String content) {
        try {
            JSONArray ar = new JSONArray(content);
            ArrayList<trainRouteModel> trainRouteList = new ArrayList<>();
            for(int i = 0;i < ar.length();i++) {
                JSONObject obj = ar.getJSONObject(i);
                trainRouteModel trainRouteModel;
                trainRouteModel = new trainRouteModel();

                trainRouteModel.setLatitude(obj.getString("lat"));
                trainRouteModel.setLongitude(obj.getString("lng"));
                trainRouteModel.setNo(obj.getInt("no"));
                trainRouteModel.setFullname(obj.getString("fullname"));

                trainRouteList.add(trainRouteModel);
            }
            return trainRouteList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
