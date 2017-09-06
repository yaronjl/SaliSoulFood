package com.baras.salisoulfood.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.baras.salisoulfood.models.MenuItem;
import com.baras.salisoulfood.R;
import com.baras.salisoulfood.models.Recipe;
import com.baras.salisoulfood.models.RecipeGroup;
import com.baras.salisoulfood.adapters.ImageAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class RecipeGroupActivity extends AppCompatActivity{
    GridView menu;
    ImageAdapter gridAdapter;
    ArrayList<Recipe> menuItems = new ArrayList();
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_group_menu);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFCB1F")));
        this.gson = new Gson();
        try {
            this._loadMenu();
        } catch (UnsupportedEncodingException e) {
            Toast.makeText(RecipeGroupActivity.this, "could not load recipes",
                    Toast.LENGTH_SHORT).show();
        }


        menu = (GridView) findViewById(R.id.menu);
        gridAdapter = new ImageAdapter(this, menuItems);
        menu.setAdapter(gridAdapter);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Intent intent = new Intent(RecipeGroupActivity.this, RecipeActivity.class);
                Recipe recipe = (Recipe) gridAdapter.getItem(position);
                intent.putExtra("recipeID", recipe.getId());
                startActivityForResult(intent, 1);
            }

        });
    }

    private void _loadMenu() throws UnsupportedEncodingException {
        Bundle extras = getIntent().getExtras();
        final String groupID = extras.getString("groupID");

        InputStream is = getResources().openRawResource(R.raw.recipes);
        JsonParser jsonParser = new JsonParser();
        JsonElement recipesJson = (JsonElement) jsonParser.parse(new InputStreamReader(is, "UTF-8"));
        Type type = new TypeToken<ArrayList<Recipe>>() {}.getType();
        ArrayList<Recipe> recipes = this.gson.fromJson(recipesJson, type);
        ArrayList<Recipe> result = new ArrayList<>();
        for(Recipe recipe: recipes){
            if(recipe.getGroupID().equals(groupID)){
                this.menuItems.add(recipe);
            }
        }

    }
}

