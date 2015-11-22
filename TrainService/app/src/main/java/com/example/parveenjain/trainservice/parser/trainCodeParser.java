package com.example.parveenjain.trainservice.parser;


import com.example.parveenjain.trainservice.model.trainCodeModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class trainCodeParser {
    public static List<trainCodeModel> parseFeed(String content) {
        try {
            JSONArray ar = new JSONArray(content);
            ArrayList<trainCodeModel> trainCodeList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                trainCodeModel trainCodeModel = new trainCodeModel();

                trainCodeModel.setFullname(obj.getString("fullname"));
                trainCodeModel.setCode(obj.getString("code"));

                trainCodeList.add(trainCodeModel);
            }
            return trainCodeList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
