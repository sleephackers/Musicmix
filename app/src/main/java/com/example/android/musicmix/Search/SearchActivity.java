package com.example.android.musicmix.Search;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.musicmix.R;

public class SearchActivity extends AppCompatActivity {
    EditText editText;
    Button title;
    Button artist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        editText = (EditText) findViewById(R.id.searchString);
        title = (Button) findViewById(R.id.searchTitle);
        artist = (Button) findViewById(R.id.searchArtist);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText() == null)
                    Toast.makeText(SearchActivity.this, "Enter Text",
                            Toast.LENGTH_SHORT).show();
                else {

                    Intent intent = new Intent(SearchActivity.this, SearchDisplay.class);
                    Bundle extras = new Bundle();
                    extras.putString("search", editText.getText().toString());
                    extras.putInt("do", 1);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });
        artist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText() == null)
                    Toast.makeText(SearchActivity.this, "Enter Text",
                            Toast.LENGTH_SHORT).show();
                else {

                    Intent intent = new Intent(SearchActivity.this, SearchDisplay.class);
                    Bundle extras = new Bundle();
                    extras.putString("search", editText.getText().toString());
                    extras.putInt("do", 2);
                    intent.putExtras(extras);
                    startActivity(intent);
                }
            }
        });
    }
}
