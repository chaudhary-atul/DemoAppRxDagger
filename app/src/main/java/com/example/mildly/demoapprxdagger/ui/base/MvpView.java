package com.example.mildly.demoapprxdagger.ui.base;

public interface MvpView {

    void showLoading();

    void hideLoading();

    void showMessage(String message);

    void showError(String message);
}
