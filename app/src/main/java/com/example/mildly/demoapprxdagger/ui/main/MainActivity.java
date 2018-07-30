package com.example.mildly.demoapprxdagger.ui.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mildly.demoapprxdagger.R;
import com.example.mildly.demoapprxdagger.data.pojo.Recipe;
import com.example.mildly.demoapprxdagger.ui.base.BaseActivity;
import com.example.mildly.demoapprxdagger.ui.main.recipedetail.RecipeDetailFragment;
import com.example.mildly.demoapprxdagger.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity implements MainMvpView, HasSupportFragmentInjector, RecipesAdapter.ItemInteractionListner {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    MainMvpPresenter<MainMvpView> presenter;

    @Inject
    LinearLayoutManager linearLayoutManager;

    @Inject
    RecipesAdapter recipesAdapter;

    @Inject
    CustomTabsIntent.Builder customTabIntent;

    @BindView(R.id.rl_mainLayout)
    RelativeLayout rlMainLayout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    private boolean isLoading;
    private int page = 1;

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
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(false);
        //  recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recipesAdapter.setMyListner(this);
        recyclerView.setAdapter(recipesAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition();

                if (visibleItemCount + pastVisibleItems >= totalItemCount && !isLoading) {
                    if (CommonUtils.isNetworkConnected(MainActivity.this)) {
                        recipesAdapter.addItemForLoading();
                        presenter.fetchRecipes();
                        isLoading = true;
                    } else {
                        CommonUtils.showInternetConnectivitySnackBar(rlMainLayout, MainActivity.this);
                    }
                }
            }
        });
        if (CommonUtils.isNetworkConnected(this)) {
            // recipesAdapter.addItemForLoading();
            presenter.onViewPrepared();
        } else {
            CommonUtils.showInternetConnectivitySnackBar(rlMainLayout, this);
        }
    }

    @Override
    public void updateRecipeList(List<Recipe> recipeList) {
        if (recipesAdapter.getItemCount() > 0)
            recipesAdapter.removeLoadingView();

        if (recipesAdapter.getItemCount() == 0) {
            recyclerView.scheduleLayoutAnimation();
            recipesAdapter.setDataInAdapter(recipeList);
        } else {
            recipesAdapter.updateDataInAdapter(recipeList);
        }
        isLoading = false;
    }

    @Override
    public void showErrorInRecyclerView() {
        showError("Some Error occur");
    }

    @Override
    protected void onDestroy() {
        presenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onVisitSiteClicked(String sourceUrl) {
        customTabIntent.setStartAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
                .setExitAnimations(this, android.R.anim.fade_in, android.R.anim.fade_out)
                .build().launchUrl(this, Uri.parse(sourceUrl));
    }

    @Override
    public void onRecipeClicked(String recipe_id) {
        recyclerView.setVisibility(View.GONE);
        frameLayout.setVisibility(View.VISIBLE);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout, RecipeDetailFragment.newInstance(recipe_id)).addToBackStack(null)
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().beginTransaction().remove(new RecipeDetailFragment()).commit();
            getSupportFragmentManager().popBackStackImmediate();
            frameLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
}
