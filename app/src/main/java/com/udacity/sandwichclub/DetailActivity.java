package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    TextView txtOrigin, txtAlsoKnownAs, txtIngredients, txtPlaceOfOrigin, txtDetailDescription;
    ImageView imgImage;

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    //sandwich declared as instance variable to enable access from populateUI method

    Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //Assign views as java objects
        ImageView ingredientsIv = findViewById(R.id.image_iv);
        txtAlsoKnownAs = findViewById(R.id.also_known_tv);
        txtIngredients = findViewById(R.id.ingredients_tv);
        txtPlaceOfOrigin = findViewById(R.id.origin_tv);
        txtDetailDescription = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


    //This method populates the user interface

    private void populateUI() {

        txtPlaceOfOrigin.setText(sandwich.getPlaceOfOrigin());
        txtDetailDescription.setText(sandwich.getDescription());

        //To list out values of alsoKnownAs
        List<String> alsoKnownAsDetail = sandwich.getAlsoKnownAs();
        for (int i = 0; i < alsoKnownAsDetail.size(); i++) {
            txtAlsoKnownAs.append((alsoKnownAsDetail.get(i) + "\n"));
        }
        //To list out values of alsoKnownAs
            List<String> ingredientsDetail = sandwich.getIngredients();
            for (int j = 0; j < ingredientsDetail.size(); j++) {
                txtIngredients.append((ingredientsDetail.get(j) + "\n"));
            }

        }
    }


