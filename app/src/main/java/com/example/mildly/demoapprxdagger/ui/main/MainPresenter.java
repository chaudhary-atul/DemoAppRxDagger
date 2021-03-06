package com.example.mildly.demoapprxdagger.ui.main;

import com.example.mildly.demoapprxdagger.data.DataManager;
import com.example.mildly.demoapprxdagger.data.pojo.Recipes;
import com.example.mildly.demoapprxdagger.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {
    private int page=1;

    @Inject
    public MainPresenter(DataManager dataManager, CompositeDisposable compositeDisposable) {
        super(dataManager,compositeDisposable);
    }

    @Override
    public void onViewPrepared() {
        fetchRecipes();
    }

    @Override
    public void fetchRecipes() {
        getCompositeDisposable().add(getDataManager()
                .getRecipes(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Recipes>() {
                    @Override
                    public void accept(Recipes recipes) throws Exception {
                        if (recipes != null) {
                            getMvpView().updateRecipeList(recipes.getRecipes());
                            ++page;
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        handleApiError(throwable);
                        getMvpView().showErrorInRecyclerView();
                    }
                }));

    }

   /* @Override
    public void getRecipeFromRecipeId(String recipe_id) {

    }*/


}
