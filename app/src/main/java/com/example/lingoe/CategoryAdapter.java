package com.example.lingoe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    //Instance field.
    public ArrayList<CategoryCard> categoryCardItems;

    //interface variable.
    private OnItemClickListener mListener;

    /**
     * Item click listener for the card on the RecyclerView.
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //Instance fields.
        public ImageView mImageView;
        public TextView mTitle, mDescription;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTitle = itemView.findViewById(R.id.title);
            mDescription = itemView.findViewById(R.id.description);

            //Listener to click the items in the recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public CategoryAdapter(ArrayList<CategoryCard> categoryCardItems) {
        this.categoryCardItems = categoryCardItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_card, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryCard currentCategoryCard = categoryCardItems.get(position);

        holder.mImageView.setImageResource(currentCategoryCard.getImageResourceID());
        holder.mTitle.setText(currentCategoryCard.getTitle());
        holder.mDescription.setText(currentCategoryCard.getDescription());
    }

    @Override
    public int getItemCount() {
        return categoryCardItems.size();
    }
}
