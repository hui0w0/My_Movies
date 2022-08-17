package com.myapplicationdev.android.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etGenre, etYear;
    Spinner spnRating;
    Button btnInsert, btnShow;
    ArrayList<Movies> alMovies;
    ArrayAdapter<Movies> aaMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Movies ~ Insert Movies");

        etTitle = findViewById(R.id.editTextTitle);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);
        spnRating = findViewById(R.id.spinnerR);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShow = findViewById(R.id.buttonShowList);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                String genre = etGenre.getText().toString();
                String rating = spnRating.getSelectedItem().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertMovie(title,genre,year,rating);
                Toast.makeText(MainActivity.this, "Inserted successfully", Toast.LENGTH_LONG).show();
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,
                        ShowMovies.class);
                startActivity(i);
            }
        });
    }
}