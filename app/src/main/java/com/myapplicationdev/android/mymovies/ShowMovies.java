package com.myapplicationdev.android.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class ShowMovies extends AppCompatActivity {

    ToggleButton tbShow;
    ListView lvMovies;
    ArrayList<Movies> alMovies;
    CustomAdapter caMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);
        setTitle("My Movies ~ Show Movies");

        tbShow = findViewById(R.id.toggleButtonPG13);
        lvMovies = findViewById(R.id.lvMovies);

        alMovies = new ArrayList<Movies>();
        // ArrayAdapter aa = new ArrayAdapter<Movies>(this, android.R.layout.simple_list_item_1,alMovies);
        caMovies = new CustomAdapter(this, R.layout.row, alMovies);
        lvMovies.setAdapter(caMovies);

        tbShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String filterText = "PG";

                DBHelper dbh = new DBHelper(ShowMovies.this);
                alMovies.clear();

                if(tbShow.isChecked()){
                    alMovies.addAll(dbh.getAllMovies());
                }else{
                    alMovies.addAll(dbh.getRatingMovies(filterText));
                }
                caMovies.notifyDataSetChanged();
            }
        });

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Movies data = alMovies.get(position);
                Intent i = new Intent(ShowMovies.this,
                        ModifyMovies.class);
                i.putExtra("data", data);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(ShowMovies.this);
        alMovies.clear();
        alMovies.addAll(dbh.getAllMovies());
        caMovies.notifyDataSetChanged();
    }
}