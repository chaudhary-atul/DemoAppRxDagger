package com.example.mildly.demoapprxdagger.ui.main.recipedetail;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mildly.demoapprxdagger.GlideApp;
import com.example.mildly.demoapprxdagger.R;
import com.example.mildly.demoapprxdagger.data.pojo.Recipe;
import com.example.mildly.demoapprxdagger.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class RecipeDetailFragment extends BaseFragment implements RecipeDetailMvpView {

    private static final String PARAM = "recipeId";

    @Inject
    RecipeDetailMvpPresenter<RecipeDetailMvpView> presenter;

    @BindView(R.id.iv_RecipeImgFull)
    AppCompatImageView recipeImg;

    @BindView(R.id.tv_recipeTitleFull)
    TextView recipeTitle;

    @BindView(R.id.tv_publisherName)
    TextView publisherName;

    @BindView(R.id.ll_ingredients)
    LinearLayout ingredientsMainView;

    private String recipeId;

    public static RecipeDetailFragment newInstance(String recipeId) {
        RecipeDetailFragment fragment = new RecipeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PARAM, recipeId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            recipeId = getArguments().getString(PARAM);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        presenter.onAttach(this);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    protected void setUp(View view) {
        showLoading();
        presenter.fetchRecipeDetail(recipeId);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.onDetach();

    }

    @Override
    public void showDataInView(Recipe recipe) {
        hideLoading();
        if (recipe.getTitle() != null)
            recipeTitle.setText(recipe.getTitle());

        if (recipe.getPublisher() != null)
            publisherName.setText(recipe.getPublisher());

        if (recipe.getImage_url() != null) {
            GlideApp.with(this)
                    .load(recipe.getImage_url())
                    .transition(withCrossFade())
                    .centerCrop()
                    .into(recipeImg);
        }

        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
            for (int i = 0; i < recipe.getIngredients().size(); i++) {
                TextView tv = new TextView(getActivity());
                tv.setText(recipe.getIngredients().get(i));
                tv.setId(i);
                tv.setPadding(0, 0, 0, 8);
                ingredientsMainView.addView(tv);
            }
        }
    }
}
