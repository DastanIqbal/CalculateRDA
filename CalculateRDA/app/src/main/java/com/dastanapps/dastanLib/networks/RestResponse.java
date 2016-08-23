package com.dastanapps.dastanLib.networks;

import com.dastanapps.calculaterda.NutrientItemsB;
import com.dastanapps.calculaterda.RDAResultB;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by IQBAL-MEBELKART on 8/16/2016.
 */

public class RestResponse {
    public static ArrayList<NutrientItemsB> getNutrients(String json) {
        ArrayList<NutrientItemsB> nutrientsList = new ArrayList<>();
        try {
            JSONArray nutrientsArray = new JSONArray(json);
            if (nutrientsArray.length() != 0) {
                for (int i = 0; i < nutrientsArray.length(); i++) {
                    JSONObject mailObj = nutrientsArray.getJSONObject(i);
                    String nutrients = mailObj.getString("nutrients");
                    String rda_lower = mailObj.getString("rda_lower");
                    String rda_upper = mailObj.getString("rda_upper");
                    String quantity_in_gms = mailObj.getString("quantity_in_gms");
                    String calories_in_kcal = mailObj.getString("calories_in_kcal");
                    String lower_calories_in_gram = mailObj.getString("lower_calories_in_gram");
                    String upper_calories_in_gram = mailObj.getString("upper_calories_in_gram");

                    NutrientItemsB mailB = new NutrientItemsB();
                    mailB.nutrients = nutrients;
                    mailB.rda_lower = rda_lower;
                    mailB.rda_upper = rda_upper;
                    mailB.calories_in_kcal = calories_in_kcal;
                    mailB.quantity_in_gms = quantity_in_gms;
                    mailB.lower_calories_in_gram = lower_calories_in_gram;
                    mailB.upper_calories_in_gram = upper_calories_in_gram;
                    nutrientsList.add(mailB);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return nutrientsList;
    }

    public static RDAResultB parseNutrientsData(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            RDAResultB rdaResultB = new RDAResultB();
            rdaResultB.status = jsonObject.getString("status");
            if(jsonObject.has("msg")){
                rdaResultB.msg=jsonObject.getString("msg");
            }
            rdaResultB.bmr = jsonObject.getString("BMR");
            rdaResultB.tcr = jsonObject.getString("TCR");
            rdaResultB.data = jsonObject.getString("data");
            return rdaResultB;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
