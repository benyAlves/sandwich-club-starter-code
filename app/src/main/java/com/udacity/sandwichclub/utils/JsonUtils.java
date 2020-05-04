package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJsonObject = new JSONObject(json);
            JSONObject name = sandwichJsonObject.getJSONObject("name");

            String mainName = name.getString("mainName");

            List<String> alsoKnowns = new ArrayList<>();
            JSONArray alsoKnownsArray = name.getJSONArray("alsoKnownAs");
            if (alsoKnownsArray != null) {
                for (int i = 0; i < alsoKnownsArray.length(); i++) {
                    alsoKnowns.add(alsoKnownsArray.getString(i));
                }
            }

            String placeOfOrigin = sandwichJsonObject.getString("placeOfOrigin");
            String description = sandwichJsonObject.getString("description");
            String image = sandwichJsonObject.getString("image");

            List<String> igredients = new ArrayList<>();
            JSONArray igredientsArray = sandwichJsonObject.getJSONArray("ingredients");

            if (igredientsArray != null) {
                for (int i = 0; i < igredientsArray.length(); i++) {
                    igredients.add(igredientsArray.getString(i));
                }
            }
            return new Sandwich(mainName, alsoKnowns, placeOfOrigin, description, image, igredients);

        } catch (JSONException e) {
            Log.e(TAG, "parseSandwichJson: " + e.getMessage());
        }
        return null;
    }
}
