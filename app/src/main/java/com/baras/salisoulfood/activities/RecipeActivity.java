package com.baras.salisoulfood.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baras.salisoulfood.R;
import com.baras.salisoulfood.models.Recipe;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Text;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {
    Gson gson;
    TextView nameView;
    TextView preperationView;
//    ListView ingredientsList;
    TextView ingredientsView;
    TextView servingView;
    ImageView imageView;
    ArrayList ingredients = new ArrayList();
    ArrayAdapter<String> ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFCB1F")));

        nameView = (TextView)findViewById(R.id.textView);
//        ingredientsList = (ListView)findViewById(R.id.ingredients);
        ingredientsView = (TextView)findViewById(R.id.ingredients);
//        ingredientsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, this.ingredients);
//        ingredientsList.setAdapter(ingredientsAdapter);
        preperationView = (TextView)findViewById(R.id.preperation);
        servingView = (TextView)findViewById(R.id.serving);
        imageView = (ImageView)findViewById(R.id.imageView2);
        this.gson = new Gson();
        try {
            this._loadRecipe();
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(RecipeActivity.this, "could not load recipe",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void _loadRecipe() throws UnsupportedEncodingException {
        Bundle extras = getIntent().getExtras();
        final String recipeID = extras.getString("recipeID");

        InputStream is = getResources().openRawResource(R.raw.recipes);
        JsonParser jsonParser = new JsonParser();
        JsonElement recipesJson = (JsonElement) jsonParser.parse(new InputStreamReader(is, "UTF-8"));
        Type type = new TypeToken<ArrayList<Recipe>>() {}.getType();
        ArrayList<Recipe> recipes = gson.fromJson(recipesJson, type);
        for(Recipe recipe: recipes){
            if(recipe.getId().equals(recipeID)){
                Integer imageID = getResources().getIdentifier(recipe.getDrawableName(), "drawable", getPackageName());
                this.imageView.setImageResource(imageID);
                this.nameView.setText(recipe.getName());
                this.ingredientsView.setText(StringUtils.join(recipe.getIngredients(), "\n"));
//                this.ingredients.addAll(recipe.getIngredients());
//                this.ingredientsAdapter.notifyDataSetChanged();
                this.preperationView.setText(recipe.getPreperation());
                this.servingView.setText(recipe.getServing());
            }
        }

    }
}
