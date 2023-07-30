package com.example.lingoe.interactivebooks;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lingoe.R;
import com.github.barteksc.pdfviewer.PDFView;

public class PDFBookActivity extends AppCompatActivity {

    //PDfView instance variable.
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfbook);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        pdfView = (PDFView) findViewById(R.id.pdf_view);

        Intent intent = getIntent();
        String pdfToOpen = intent.getStringExtra("PDFFile");
        actionBar.setTitle(pdfToOpen); //Setting title of the specific pdf that user would open.

        //Method to open a PDF file.
        openPDF(pdfToOpen);
    }

    private void openPDF(String findPDF) {
        switch(findPDF) {
            case "A Christmas Tree" :
                pdfView.fromAsset("a_christmas_tree.pdf").load();
                break;
            case "A Child's Story" :
                pdfView.fromAsset("a_childs_story.pdf").load();
                break;
            case "Going Into Society" :
                pdfView.fromAsset("going_into_society.pdf").load();
                break;
            case "Nobody's Story" :
                pdfView.fromAsset("nobodys_story.pdf").load();
                break;
            case "The Poor Relations Story" :
                pdfView.fromAsset("the_poor_relations_story.pdf").load();
                break;
            case "The Schoolboys Story" :
                pdfView.fromAsset("the_schoolboys_story.pdf").load();
                break;
            case "The Signal Man" :
                pdfView.fromAsset("the_signal_man.pdf").load();
                break;
        }
    }
}