package com.udacity.sandwichclub.utils;

import android.widget.ArrayAdapter;
import android.widget.EdgeEffect;

import com.udacity.sandwichclub.DetailActivity;
import com.udacity.sandwichclub.MainActivity;
import com.udacity.sandwichclub.R;
import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = new Sandwich();

        //Implementation of the JSON parsing:
        try {
            JSONObject root = new JSONObject(json);
            JSONObject name = root.getJSONObject("name");
            String mainName = name.getString("mainName");
            String placeOfOrigin = root.getString("placeOfOrigin");
            String description = root.getString("description");
            String imageLink = root.getString("image");
            //To get the JSON array elements for alsoKnownAs into an ArrayList
            JSONArray alsoKnownAs = name.getJSONArray("alsoKnownAs");
                List<String> otherNamesArray = new ArrayList<String>();

                for (int i= 0; i<alsoKnownAs.length(); i++){
                String otherNameString = alsoKnownAs.getString(i);
                otherNamesArray.add(otherNameString);
            }

            //To get the JSON array elements for placeOfOrigin into an ArrayList
            JSONArray ingredients = root.getJSONArray("ingredients");

                List<String> ingredientsArray = new ArrayList<String>();
                for (int i=0; i<ingredients.length();i++){

                String ingredientString = ingredients.getString(i);
                ingredientsArray.add(ingredientString);
            }


            //Assign JSON strings into sandwich objects

            sandwich.setMainName(mainName);
            sandwich.setDescription(description);
            sandwich.setPlaceOfOrigin(placeOfOrigin);
            sandwich.setAlsoKnownAs(otherNamesArray);
            sandwich.setIngredients(ingredientsArray);
            sandwich.setImage(imageLink);

        }

        catch (Exception e){
            e.printStackTrace();
        }

        return sandwich;


    }
}
