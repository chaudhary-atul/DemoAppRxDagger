package com.example.mildly.demoapprxdagger.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipe {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("image_url")
    @Expose
    private String image_url;

    @SerializedName("social_rank")
    @Expose
    private Float social_rank;

    @SerializedName("source_url")
    @Expose
    private String source_url;

    @SerializedName("recipe_id")
    @Expose
    private String recipe_id;

    @SerializedName("publisher")
    @Expose
    private String publisher;

    @SerializedName("ingredients")
    @Expose
    private List<String> ingredients;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Float getSocial_rank() {
        return social_rank;
    }

    public void setSocial_rank(Float social_rank) {
        this.social_rank = social_rank;
    }

    public String getSource_url() {
        return source_url;
    }

    public void setSource_url(String source_url) {
        this.source_url = source_url;
    }

    public String getRecipe_id() {
        return recipe_id;
    }

    public void setRecipe_id(String recipe_id) {
        this.recipe_id = recipe_id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

}
