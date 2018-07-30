package com.example.mildly.demoapprxdagger.ui.main;

import com.example.mildly.demoapprxdagger.data.pojo.Recipe;
import com.example.mildly.demoapprxdagger.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView{
    void updateRecipeList(List<Recipe> recipes);

    void showErrorInRecyclerView();
}
