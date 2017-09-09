package com.baras.salisoulfood.tools;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.MenuItem;

import com.baras.salisoulfood.R;
import com.baras.salisoulfood.activities.BaseActivity;
import com.baras.salisoulfood.models.GridMenuItem;
import com.baras.salisoulfood.models.Recipe;
import com.baras.salisoulfood.models.RecipeGroup;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Tools {

    public static void setString(Context context, String name, String value){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(name, value);
        editor.commit();
    }

    public static String getString(Context context, String name){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(name, null);
    }

    public static ArrayList<GridMenuItem> getRecipeGroupItems(Context context){
        Gson gson = new Gson();
        JsonObject recipesJson = getLanguageSpecificResourceJson(context);
        Type type = new TypeToken<ArrayList<RecipeGroup>>() {}.getType();
        return (ArrayList<GridMenuItem>) gson.fromJson(recipesJson.get("groups"), type);
    }
    public static ArrayList<Recipe> getRecipes(Context context) {
        Gson gson = new Gson();
        JsonObject recipesJson = getLanguageSpecificResourceJson(context);
        Type type = new TypeToken<ArrayList<Recipe>>() {
        }.getType();
        return gson.fromJson(recipesJson.get("recipes"), type);
    }

    private static JsonObject getLanguageSpecificResourceJson(Context context){
        String language = Tools.getString(context.getApplicationContext(), "language");
        if(language == null){
            language = "hewbrew";
        }
        InputStream is;

        if(language.equals("romanian"))
            is = context.getResources().openRawResource(R.raw.recipes);
        else
            is = context.getResources().openRawResource(R.raw.recipes_hebrew);
        JsonParser jsonParser = new JsonParser();
        JsonElement recipeGroupsJson = null;
        try {
            recipeGroupsJson = (JsonElement) jsonParser.parse(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return recipeGroupsJson.getAsJsonObject();
    }

    public static Boolean loadWindowAccordingToLanguageButtonPress(BaseActivity baseActivity, Context context,MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_hebrew:
                Tools.setString(context, "language", "hebrew");
                baseActivity._loadWindow();
                return true;

            case R.id.action_romanian:
                Tools.setString(context, "language", "romanian");
                baseActivity._loadWindow();
                return true;
            default:
                return false;
        }
    }
}
