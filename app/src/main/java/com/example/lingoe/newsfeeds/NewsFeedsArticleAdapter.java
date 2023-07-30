package com.example.lingoe.newsfeeds;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lingoe.R;

import java.util.ArrayList;

public class NewsFeedsArticleAdapter extends RecyclerView.Adapter<NewsFeedsArticleAdapter.ViewHolder>{

    //Instance field.
    ArrayList<NewsfeedsArticles> mNewsFeedsArticlesList;

    //Constructor.
    public NewsFeedsArticleAdapter(ArrayList<NewsfeedsArticles> mNewsFeedsArticlesList) {
        this.mNewsFeedsArticlesList = mNewsFeedsArticlesList;
    }

    public interface OnItemClickListener {
        public void onItemClick(int position);
    }

    public OnItemClickListener mOnItemClickListener;

    //Method to set the source click listener to the destination click listener.
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        //Instance fields.
        ImageView mNewsFeedsArticlesCover;
        TextView mNewsFeedsArticlesTitle, mNewsFeedsArticlesDescription;

        public ViewHolder(@NonNull View itemView, OnItemClickListener mOnItemClickListener) {
            super(itemView);
            mNewsFeedsArticlesCover = itemView.findViewById(R.id.newsfeeds_article_cover);
            mNewsFeedsArticlesTitle = itemView.findViewById(R.id.newsfeeds_article_title);
            mNewsFeedsArticlesDescription = itemView.findViewById(R.id.newsfeeds_article_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            mOnItemClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_feeds_cards, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsfeedsArticles currentNewsFeedsArticle = mNewsFeedsArticlesList.get(position);

        holder.mNewsFeedsArticlesCover.setImageResource(currentNewsFeedsArticle.getArticleCoverResourceID());
        holder.mNewsFeedsArticlesTitle.setText(currentNewsFeedsArticle.getArticleTitle());
        holder.mNewsFeedsArticlesDescription.setText(currentNewsFeedsArticle.getArticleDescription());
    }

    @Override
    public int getItemCount() {
        return mNewsFeedsArticlesList.size();
    }
}
