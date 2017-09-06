package com.baras.salisoulfood.models;


import com.google.gson.Gson;
import com.google.gson.InstanceCreator;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;


public class Recipe implements MenuItem {
    String id;
    String name;
    String drawableName;
    String groupID;
    ArrayList<String> ingredients;
    String preperation;
    String serving;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDrawableName() {
        return drawableName;
    }

    public String getGroupID() {
        return groupID;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public String getPreperation() {
        return preperation;
    }

    public String getServing() {
        return serving;
    }
}
