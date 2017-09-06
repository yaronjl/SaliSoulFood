package com.baras.salisoulfood.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.baras.salisoulfood.models.MenuItem;
import com.baras.salisoulfood.R;
import com.baras.salisoulfood.models.Recipe;
import com.baras.salisoulfood.models.RecipeGroup;
import com.baras.salisoulfood.adapters.ImageAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView menu;
    Gson gson;
    ImageAdapter gridAdapter;
    ArrayList<MenuItem> menuItems = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFCB1F")));
        this.gson = new Gson();

        try {
            this._loadMenu();
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "could not load menu",
                    Toast.LENGTH_SHORT).show();
        }

        menu = (GridView) findViewById(R.id.menu);
        gridAdapter = new ImageAdapter(this, menuItems);
        menu.setAdapter(gridAdapter);
        menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                        Intent intent = new Intent(MainActivity.this, RecipeGroupActivity.class);
                        RecipeGroup recipeGroup = (RecipeGroup) gridAdapter.getItem(position);
                        intent.putExtra("groupID", recipeGroup.getId());
                        startActivityForResult(intent, 1);
                }

            });
    }

    private void _loadMenu() throws IOException {
        InputStream is = getResources().openRawResource(R.raw.recipe_groups);
        JsonParser jsonParser = new JsonParser();
        JsonElement recipeGroupsJson = (JsonElement) jsonParser.parse(
                new InputStreamReader(is, "UTF-8"));
        Type type = new TypeToken<ArrayList<RecipeGroup>>() {}.getType();
        this.menuItems = gson.fromJson(recipeGroupsJson, type);
    }
}
