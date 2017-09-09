package com.baras.salisoulfood.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.baras.salisoulfood.R;
import com.baras.salisoulfood.models.Recipe;
import com.baras.salisoulfood.adapters.ImageAdapter;
import com.baras.salisoulfood.tools.Tools;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class RecipeGroupActivity extends BaseActivity{
    GridView menu;
    ImageAdapter gridAdapter;
    ArrayList<Recipe> menuItems = new ArrayList();

    protected void setActivityContentView(){
        setContentView(R.layout.activity_recipe_group_menu);
    }

    protected void initializeActivity() {
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
        this._loadWindow();
    }

    public void _loadWindow() {
        this.menuItems.clear();
        Bundle extras = getIntent().getExtras();
        final String groupID = extras.getString("groupID");
        for(Recipe recipe: Tools.getRecipes(getApplicationContext())){
            if(recipe.getGroupID().equals(groupID)){
                this.menuItems.add(recipe);
            }
        }
        gridAdapter.notifyDataSetChanged();
    }

}

