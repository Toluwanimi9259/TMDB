package com.apps.themafia.tmdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    EditText title;
    EditText year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

         title = findViewById(R.id.titleTextview);
         year = findViewById(R.id.yearTextview);

//        title.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                String newTitle = title.getText().toString();
//                Intent newTitleIntent = new Intent(SearchActivity.this ,  MainActivity.class);
//                newTitleIntent.putExtra("Title" , newTitle);
//                startActivity(newTitleIntent);
//                return false;
//            }
//        });
//
//        year.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//                String newYear = year.getText().toString();
//                Intent newTitleIntent = new Intent(SearchActivity.this ,  MainActivity.class);
//                newTitleIntent.putExtra("Year" , newYear);
//                startActivity(newTitleIntent);
//                return false;
//            }
//        });
    }


    public void onSearch(View view){
        String Title = title.getText().toString();
        String Year = year.getText().toString();
        Intent newTitleIntent = new Intent(SearchActivity.this ,  MainActivity.class);
        newTitleIntent.putExtra("Title" , Title);
        newTitleIntent.putExtra("Year" , Year);
        startActivity(newTitleIntent);
    }
}
