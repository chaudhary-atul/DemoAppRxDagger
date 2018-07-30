package com.example.mildly.demoapprxdagger.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mildly.demoapprxdagger.GlideApp;
import com.example.mildly.demoapprxdagger.R;
import com.example.mildly.demoapprxdagger.data.pojo.Recipe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class RecipesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final int TYPE_LOADING_MORE = 1;
    public static final int TYPE_RECIPE = -1;
    private List<Recipe> recipeList;
    private ItemInteractionListner listner;

    public RecipesAdapter(ArrayList<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    public void setDataInAdapter(List<Recipe> recipeList) {
        this.recipeList.addAll(recipeList);
        notifyDataSetChanged();
    }

    public void updateDataInAdapter(List<Recipe> recipeList) {
        this.recipeList.addAll(recipeList);
        notifyDataSetChanged();
    }

    public void setMyListner(ItemInteractionListner listner) {
        this.listner = listner;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE_RECIPE:
                return createRecipeViewHolder(viewGroup);
            case TYPE_LOADING_MORE:
                return createLoadingMoreViewHolder(viewGroup);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_RECIPE:
                ((RecipeViewHolder) viewHolder).onBind(position);
                break;
            case TYPE_LOADING_MORE:
                ((LoadingMoreViewHolder) viewHolder).onBind(position);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return recipeList == null ? 0 : recipeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return recipeList.get(position) == null ? TYPE_LOADING_MORE : TYPE_RECIPE;
    }

    private RecyclerView.ViewHolder createRecipeViewHolder(ViewGroup parent) {
        final RecipeViewHolder recipeViewHolder = new RecipeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item, parent, false));

        recipeViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipe recipe=getItem(recipeViewHolder.getAdapterPosition());
                listner.onRecipeClicked(recipe.getRecipe_id());
            }
        });

        recipeViewHolder.socialUrlBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Recipe recipe = getItem(recipeViewHolder.getAdapterPosition());
                if (recipe != null) {
                    listner.onVisitSiteClicked(recipe.getSource_url());
                }
            }
        });
        return recipeViewHolder;
    }


    private RecyclerView.ViewHolder createLoadingMoreViewHolder(ViewGroup parent) {
        final LoadingMoreViewHolder loadingMoreViewHolder = new LoadingMoreViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recipes_list_view_footer, parent, false));
        return loadingMoreViewHolder;
    }

    public void addItemForLoading() {
        recipeList.add(null);
        notifyItemInserted(recipeList.size() - 1);
    }

    public void removeLoadingView() {
        recipeList.remove(recipeList.size() - 1);
        notifyItemRemoved(recipeList.size() - 1);
    }

    public Recipe getItem(int position) {
        if (position != RecyclerView.NO_POSITION)
            return recipeList.get(position);
        else
            return null;
    }


    public class LoadingMoreViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.loading)
        LinearLayout loading;
       /* @BindView(R.id.retry_button)
        AppCompatButton retryButton;*/

        public LoadingMoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(int position) {
            itemView.setLayoutParams(
                    new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            );

        }
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_recipeTitle)
        TextView recipeTitle;

        @BindView(R.id.iv_recipeImg)
        AppCompatImageView recipeImageView;

        @BindView(R.id.tv_socialRank)
        TextView socialRank;

        @BindView(R.id.tv_sourceUrl)
        TextView socialUrlBtn;


        public RecipeViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void onBind(final int position) {
            final Recipe recipe = recipeList.get(position);

           // Log.d("SourceUrl", recipe.getRecipe_id());

            if (recipe.getTitle() != null)
                recipeTitle.setText(recipe.getTitle());

            if (recipe.getSocial_rank() != null)
                socialRank.setText(String.valueOf(recipe.getSocial_rank()));

            if (recipe.getSource_url() != null)
                socialUrlBtn.setVisibility(View.VISIBLE);

            if (recipe.getImage_url() != null) {
                GlideApp.with(itemView.getContext())
                        .load(recipe.getImage_url())
                        .transition(withCrossFade())
                        .centerCrop()
                        .into(recipeImageView);
            }

        }
    }

    public interface ItemInteractionListner {
        void onVisitSiteClicked(String sourceUrl);

        void onRecipeClicked(String recipe_id);
    }

}
