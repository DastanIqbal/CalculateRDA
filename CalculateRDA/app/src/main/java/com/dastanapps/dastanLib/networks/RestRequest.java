package com.dastanapps.dastanLib.networks;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by IQBAL-MEBELKART on 8/16/2016.
 */

public class RestRequest {

    public static HashMap<String, String> prepareRDA(String weightInkg, String heightIncm, String age, String gender, String activity) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("weight_in_kg", weightInkg);
            jsonObject.put("height_in_cm", heightIncm);
            jsonObject.put("age", age);
            jsonObject.put("gender", gender);
            jsonObject.put("activity", activity);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        HashMap<String, String> rdaparams = new HashMap<>();
        rdaparams.put("json", jsonObject.toString());
        return rdaparams;
    }
}
