package com.example.mildly.demoapprxdagger.ui.main.recipedetail;

import com.example.mildly.demoapprxdagger.data.pojo.Recipe;
import com.example.mildly.demoapprxdagger.ui.base.MvpView;

public interface RecipeDetailMvpView  extends MvpView{
    void showDataInView(Recipe recipes);
}
