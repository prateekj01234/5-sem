package com.example.parveenjain.trainservice.parser;


import com.example.parveenjain.trainservice.model.stationModel;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class stationParser {
    public static ArrayList<stationModel> parseFeed(String content) {
        try {
            JSONArray ar = new JSONArray(content);
            ArrayList<stationModel> stationList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                stationModel stationModel = new stationModel();

                stationModel.setNo(obj.getInt("no"));
                stationModel.setSrc_departure_time(obj.getString("src_departure_time"));
                stationModel.setTo(obj.getString("to"));
                stationModel.setDest_arrival_time(obj.getString("dest_arrival_time"));
                stationModel.setNumber(obj.getInt("number"));
                stationModel.setName(obj.getString("name"));
                stationModel.setFrom(obj.getString("from"));


                stationList.add(stationModel);
            }
            return stationList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
