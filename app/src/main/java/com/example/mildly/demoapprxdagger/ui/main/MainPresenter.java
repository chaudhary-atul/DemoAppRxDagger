package com.example.mildly.demoapprxdagger.ui.main;

import com.example.mildly.demoapprxdagger.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    @Inject
    public MainPresenter(CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
        //if(isViewAttached())
    }

    @Override
    public void run() {
        getMvpView().updateTextByName();
    }
}
