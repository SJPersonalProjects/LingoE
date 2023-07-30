package com.example.lingoe.beginners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lingoe.R;

import java.util.ArrayList;

public class BeginnersAlphabetAdapter extends ArrayAdapter<BeginnersAlphabets> {

    public BeginnersAlphabetAdapter(@NonNull Context context, ArrayList<BeginnersAlphabets> alphabetsList) {
        super(context, 0, alphabetsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View gridItemView = convertView;
        if (gridItemView == null) {
            gridItemView = LayoutInflater.from(getContext()).inflate(R.layout.beginners_card, parent, false);
        }

        //Getting position of each item for selection.
        BeginnersAlphabets currentBeginnersCard = getItem(position);

        TextView alphabet = gridItemView.findViewById(R.id.alphabet);
        alphabet.setText(currentBeginnersCard.getAlphabet());

        TextView pronunciation = gridItemView.findViewById(R.id.alphabet_pronunciation);
        pronunciation.setText(currentBeginnersCard.getPronunciation());
        return gridItemView;
    }
}
