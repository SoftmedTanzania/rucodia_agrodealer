package com.softmed.rucodia.Dom.responces;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class CategoriesResponse implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("uuid")
    private String uuid;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("subcategories")
    List<SubCategoriesResponse> subCategoriesResponses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SubCategoriesResponse> getSubCategoriesResponses() {
        return subCategoriesResponses;
    }

    public void setSubCategoriesResponses(List<SubCategoriesResponse> subCategoriesResponses) {
        this.subCategoriesResponses = subCategoriesResponses;
    }
}
