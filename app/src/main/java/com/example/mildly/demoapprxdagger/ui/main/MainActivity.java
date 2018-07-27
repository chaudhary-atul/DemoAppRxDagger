package com.example.mildly.demoapprxdagger.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.mildly.demoapprxdagger.R;
import com.example.mildly.demoapprxdagger.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    @BindView(R.id.tvDemo)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.onAttach(this);
        init();
    }

    private void init() {
        presenter.run();
    }

    @Override
    public void updateTextByName() {
        Log.d("Inside Dialog","YES");
       textView.setText("Welcome Atul !!!");
    }
}
