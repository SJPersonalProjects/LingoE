package com.example.lingoe.interactivebooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lingoe.R;

import java.util.ArrayList;

public class InteractiveBookAdapter extends ArrayAdapter<InteractiveBooksCard> {

    public InteractiveBookAdapter(@NonNull Context context, ArrayList<InteractiveBooksCard> bookCardList) {
        super(context, 0, bookCardList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridView = convertView;
        if(gridView == null) {
            gridView = LayoutInflater.from(getContext()).inflate(R.layout.interactive_books_card, parent, false);
        }

        //Getting position for each item in the list.
        InteractiveBooksCard currentBookCard = getItem(position);

        ImageView bookCover = gridView.findViewById(R.id.interactive_book_cover);
        bookCover.setImageResource(currentBookCard.getBookImageResourceID());

        TextView bookName = gridView.findViewById(R.id.interactive_book_name);
        bookName.setText(currentBookCard.getBookName());

        return gridView;
    }
}
