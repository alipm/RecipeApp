package com.test.aiup;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by suvarna on 06-06-2018.
 */

class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder>{

    private Context mContext;
    private List<Recipe> recipeList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, desc, writtenby, likes;
        public ImageView thumbnail;//, overflow;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            desc = (TextView) view.findViewById(R.id.decription);
            writtenby = (TextView) view.findViewById(R.id.swrittenby);
            likes = (TextView) view.findViewById(R.id.likes);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            //overflow = (ImageView) view.findViewById(R.id.overflow);
        }
    }

    public RecipeAdapter(Context mContext, List<Recipe> albumList) {
        this.mContext = mContext;
        this.recipeList = albumList;
    }

    @NonNull
    @Override
    public RecipeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeAdapter.MyViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.title.setText(recipe.getsTitle());
        holder.desc.setText(recipe.getsDescription());
        holder.writtenby.setText("Written by \n" + recipe.getsWrittenby()+ " from " +recipe.getsCity());
        holder.likes.setText(recipe.getiNumOfLikes() + " Likes");
        // loading album cover using Glide library
        Glide.with(mContext).load(recipe.getiThumbnail()).into(holder.thumbnail);

       /* holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }
}
