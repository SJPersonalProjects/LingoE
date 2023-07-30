package com.example.lingoe.interactivestories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lingoe.R;

import java.util.ArrayList;

public class InteractiveStoryAdapter extends RecyclerView.Adapter<InteractiveStoryAdapter.ViewHolder> {

    //Instance field.
    private final ArrayList<InteractiveStory> mInteractiveStoryList;
    private final Context context;

    //Constructor.
    public InteractiveStoryAdapter(Context context, ArrayList<InteractiveStory> mInteractiveStoryList) {
        this.context = context;
        this.mInteractiveStoryList = mInteractiveStoryList;
    }

    public OnItemClickListener mOnItemClickListener;

    //OnItemClickListener for the recyclerview.
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.interactive_stories_card, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view, mOnItemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InteractiveStory mInteractiveCurrentStory = mInteractiveStoryList.get(position);

        holder.mInteractiveStoryCover.setImageResource(mInteractiveCurrentStory.getInteractiveStoryCoverID());
        holder.mInteractiveStoryName.setText(mInteractiveCurrentStory.getInteractiveStoryName());
        holder.mInteractiveStoryWriter.setText(mInteractiveCurrentStory.getInteractiveStoryWriter());
    }

    @Override
    public int getItemCount() {
        return mInteractiveStoryList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        //Instance fields.
        public final ImageView mInteractiveStoryCover;
        public final TextView mInteractiveStoryName, mInteractiveStoryWriter;

        public ViewHolder(@NonNull View itemView, OnItemClickListener mOnItemClickListener) {
            super(itemView);
            mInteractiveStoryCover = (ImageView) itemView.findViewById(R.id.interactive_stories_cover);
            mInteractiveStoryName = (TextView) itemView.findViewById(R.id.interactive_story_name);
            mInteractiveStoryWriter = (TextView) itemView.findViewById(R.id.interactive_story_writer);

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
}
