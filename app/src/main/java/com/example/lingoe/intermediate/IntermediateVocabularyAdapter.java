package com.example.lingoe.intermediate;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lingoe.R;

import java.util.ArrayList;

public class IntermediateVocabularyAdapter extends RecyclerView.Adapter<IntermediateVocabularyAdapter.ViewHolder> {

    //Instance fields.
    private final ArrayList<IntermediateVocabulary> mIntermediateVocabularyCardList;

    //Interface listener.
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        //Only method for the items click...
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //Instance fields.
        public ImageView mVocabularyImage;
        public TextView mVocabulary, mVocabularyPronunciation, mVocabularyMeaning;

        public ViewHolder(@NonNull View itemView, OnItemClickListener mOnItemClickListener) {
            super(itemView);
            mVocabularyImage = (ImageView) itemView.findViewById(R.id.vocabulary_card_image_view);
            mVocabulary = (TextView) itemView.findViewById(R.id.vocabulary_word);
            mVocabularyPronunciation = (TextView) itemView.findViewById(R.id.vocabulary_pronunciation);
            mVocabularyMeaning = (TextView) itemView.findViewById(R.id.vocabulary_meaning);

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

    //Constructor.
    public IntermediateVocabularyAdapter(ArrayList<IntermediateVocabulary> mIntermediateVocabularyCardList) {
        this.mIntermediateVocabularyCardList = mIntermediateVocabularyCardList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.intermediate_card, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view, mOnItemClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IntermediateVocabulary currentIntermediateVocabulary = mIntermediateVocabularyCardList.get(position);

        holder.mVocabulary.setText(currentIntermediateVocabulary.getVocabulary());
        holder.mVocabularyPronunciation.setText(currentIntermediateVocabulary.getVocabularyPronunciation());
        holder.mVocabularyMeaning.setText(currentIntermediateVocabulary.getVocabularyMeaning());
        holder.mVocabularyImage.setImageResource(currentIntermediateVocabulary.getImageResourceID());
    }

    @Override
    public int getItemCount() {
        return mIntermediateVocabularyCardList.size();
    }

}
