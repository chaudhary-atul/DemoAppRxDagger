package com.example.mildly.demoapprxdagger.ui.main.recipedetail;

import com.example.mildly.demoapprxdagger.data.DataManager;
import com.example.mildly.demoapprxdagger.data.pojo.Recipe;
import com.example.mildly.demoapprxdagger.data.pojo.Recipes;
import com.example.mildly.demoapprxdagger.data.pojo.SingleRecipe;
import com.example.mildly.demoapprxdagger.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RecipeDetailPresenter<V extends RecipeDetailMvpView> extends BasePresenter<V> implements RecipeDetailMvpPresenter<V> {

    @Inject
    public RecipeDetailPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager, compositeDisposable);
    }

    @Override
    public void fetchRecipeDetail(String recipeId) {
        getCompositeDisposable().add(getDataManager()
                .getRecipe(recipeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<SingleRecipe>() {
                    @Override
                    public void accept(SingleRecipe recipe) throws Exception {
                        if (recipe != null) {
                            getMvpView().showDataInView(recipe.getRecipe());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        handleApiError(throwable);
                        getMvpView().showError("Something went wrong");
                    }
                }));
    }
}
