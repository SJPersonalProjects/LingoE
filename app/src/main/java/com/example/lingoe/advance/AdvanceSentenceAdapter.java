package com.example.lingoe.advance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lingoe.R;

import java.util.ArrayList;

public class AdvanceSentenceAdapter extends RecyclerView.Adapter<AdvanceSentenceAdapter.ViewHolder> {

    //Instance fields.
    private ArrayList<AdvanceSentence> mAdvanceSentenceList;

    //Constructor.
    public AdvanceSentenceAdapter(ArrayList<AdvanceSentence> mAdvanceSentenceList) {
        this.mAdvanceSentenceList = mAdvanceSentenceList;
    }

    public interface OnItemClickListener{
        public void onItemClick(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Instance fields.
        ImageView mIcon;
        TextView mPhrase, mPhrasePronunciation, mPhraseSentence;

        public ViewHolder(@NonNull View itemView, OnItemClickListener mOnItemClickListener) {
            super(itemView);
            mIcon = itemView.findViewById(R.id.icon_view);
            mPhrase = itemView.findViewById(R.id.english_phrase);
            mPhrasePronunciation = itemView.findViewById(R.id.english_phrase_pronunciation);
            mPhraseSentence = itemView.findViewById(R.id.english_phrase_sentence);

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.advance_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AdvanceSentence mAdvanceSentence = mAdvanceSentenceList.get(position);

        holder.mIcon.setImageResource(mAdvanceSentence.getIconResourceID());
        holder.mPhrase.setText(mAdvanceSentence.getPhrase());
        holder.mPhrasePronunciation.setText(mAdvanceSentence.getPhrasePronunciation());
        holder.mPhraseSentence.setText(mAdvanceSentence.getPhraseSentence());
    }

    @Override
    public int getItemCount() {
        return mAdvanceSentenceList.size();
    }
}
