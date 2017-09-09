package com.baras.salisoulfood.models;


import java.util.ArrayList;


public class Recipe implements GridMenuItem {
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
