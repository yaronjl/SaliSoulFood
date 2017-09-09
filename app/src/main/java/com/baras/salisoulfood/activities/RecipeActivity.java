package com.baras.salisoulfood.activities;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baras.salisoulfood.R;
import com.baras.salisoulfood.models.Recipe;
import com.baras.salisoulfood.tools.Tools;
import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class RecipeActivity extends BaseActivity{
    TextView nameView;
    TextView preperationView;
    TextView ingredientsView;
    TextView servingView;
    ImageView imageView;

    protected void setActivityContentView(){
        setContentView(R.layout.activity_recipe);
    }

    protected void initializeActivity() {
        nameView = (TextView)findViewById(R.id.textView);
        ingredientsView = (TextView)findViewById(R.id.ingredients);
        preperationView = (TextView)findViewById(R.id.preperation);
        servingView = (TextView)findViewById(R.id.serving);
        imageView = (ImageView)findViewById(R.id.imageView2);
        this.gson = new Gson();
        this._loadWindow();
    }

    public void _loadWindow() {
        Bundle extras = getIntent().getExtras();
        final String recipeID = extras.getString("recipeID");
        for (Recipe recipe : Tools.getRecipes(getApplicationContext())) {
            if (recipe.getId().equals(recipeID)) {
                Integer imageID = getResources().getIdentifier(recipe.getDrawableName(), "drawable", getPackageName());
                this.imageView.setImageResource(imageID);
                this.nameView.setText(recipe.getName());
                this.ingredientsView.setText(StringUtils.join(recipe.getIngredients(), "\n"));
                this.preperationView.setText(recipe.getPreperation());
                this.servingView.setText(recipe.getServing());
            }
        }
    }
}
