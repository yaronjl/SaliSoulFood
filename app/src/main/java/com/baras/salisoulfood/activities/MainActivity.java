package com.baras.salisoulfood.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.baras.salisoulfood.models.GridMenuItem;
import com.baras.salisoulfood.R;
import com.baras.salisoulfood.models.RecipeGroup;
import com.baras.salisoulfood.adapters.ImageAdapter;
import com.baras.salisoulfood.tools.Tools;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends  BaseActivity{
    GridView menu;
    ImageAdapter gridAdapter;
    ArrayList<GridMenuItem> menuItems = new ArrayList();

    protected void setActivityContentView(){
        setContentView(R.layout.activity_main);
    }

    protected void initializeActivity(){
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
        this._loadWindow();
    }


    public void _loadWindow() {
        this.menuItems.clear();
        this.menuItems.addAll(Tools.getRecipeGroupItems(getApplicationContext()));
        gridAdapter.notifyDataSetChanged();
    }


}
