/*
 * Copyright (c) 2020.  The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

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

    /*
     * Parse JSON to Sandwich object
     * */
    public static Sandwich parseSandwichJson(String json) {
        try {
            JSONObject sandwichJsonObject = new JSONObject(json);
            JSONObject name = sandwichJsonObject.getJSONObject("name");

            String mainName = name.getString("mainName");

            List<String> alsoKnownAs = new ArrayList<>();
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            if (alsoKnownAsArray != null) {
                for (int i = 0; i < alsoKnownAsArray.length(); i++) {
                    alsoKnownAs.add(alsoKnownAsArray.getString(i));
                }
            }

            String placeOfOrigin = sandwichJsonObject.getString("placeOfOrigin");
            String description = sandwichJsonObject.getString("description");
            String image = sandwichJsonObject.getString("image");

            List<String> ingredients = new ArrayList<>();
            JSONArray ingredientsArray = sandwichJsonObject.getJSONArray("ingredients");

            if (ingredientsArray != null) {
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    ingredients.add(ingredientsArray.getString(i));
                }
            }
            return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

        } catch (JSONException e) {
            Log.e(TAG, "parseSandwichJson: " + e.getMessage());
        }
        return null;
    }
}
