package com.example.lingoe.interactivebooks;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.lingoe.R;

import java.util.ArrayList;

public class InteractiveBooksActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_books);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        ArrayList<InteractiveBooksCard> bookCardList = new ArrayList<>();
        bookCardList.add(new InteractiveBooksCard(R.drawable.a_christmas_tree, "A Christmas Tree", "A Christmas Tree"));
        bookCardList.add(new InteractiveBooksCard(R.drawable.the_childs_story, "The Child's Story", "A Child's Story"));
        bookCardList.add(new InteractiveBooksCard(R.drawable.going_into_society, "Going Into Society", "Going Into Society"));
        bookCardList.add(new InteractiveBooksCard(R.drawable.nobodys_story, "Nobody's Story", "Nobody's Story"));
        bookCardList.add(new InteractiveBooksCard(R.drawable.the_poor_relations, "The Poor Relations", "The Poor Relations Story"));
        bookCardList.add(new InteractiveBooksCard(R.drawable.the_schoolboys_story, "The Schoolboy's Story", "The Schoolboys Story"));
        bookCardList.add(new InteractiveBooksCard(R.drawable.the_signal_man, "The Signal Man", "The Signal Man"));

        InteractiveBookAdapter adapter = new InteractiveBookAdapter(this, bookCardList);
        GridView gridView = (GridView) findViewById(R.id.books_grid_view);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                InteractiveBooksCard currentBookCard = bookCardList.get(position);
                intent = new Intent(InteractiveBooksActivity.this, PDFBookActivity.class);
                intent.putExtra("PDFFile", currentBookCard.getPDFName());
                startActivity(intent);
                //Toast message to show the title of the selected PDF.
                Toast.makeText(InteractiveBooksActivity.this, currentBookCard.getBookName(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}