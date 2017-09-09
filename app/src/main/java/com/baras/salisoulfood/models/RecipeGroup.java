package com.baras.salisoulfood.models;


public class RecipeGroup implements GridMenuItem {
    String id;
    String name;
    String drawableName;

    public RecipeGroup(String id, String name, String drawableName){

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDrawableName() {
        return drawableName;
    }
}
