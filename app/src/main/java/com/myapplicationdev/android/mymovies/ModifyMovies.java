package com.myapplicationdev.android.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ModifyMovies extends AppCompatActivity {

    EditText etTitle, etGenre, etYear;
    Spinner spinnerR;
    Button btnUpdate, btnDelete, btnCancel;
    Movies data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_movie);
        setTitle("My Movies ~ Modify Movies");

        etTitle = findViewById(R.id.editTextTitle);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);
        spinnerR = findViewById(R.id.spinnerR);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);

        Intent i = getIntent();
        data = (Movies) i.getSerializableExtra("data");

        etTitle.setText(data.getTitle());
        etGenre.setText(data.getGenre());
        etYear.setText(String.valueOf(data.getYear()));
        String rating = data.getRating();

        if(rating.equals("G")){
            spinnerR.setSelection(0);
        }else if(rating.equals("PG")){
            spinnerR.setSelection(1);
        }else if(rating.equals("PG13")){
            spinnerR.setSelection(2);
        }else if(rating.equals("NC16")){
            spinnerR.setSelection(3);
        }else if(rating.equals("M18")){
            spinnerR.setSelection(4);
        }else if(rating.equals("R21")){
            spinnerR.setSelection(5);
        }else{
            spinnerR.setSelection(5);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifyMovies.this);
                data.setTitle(etTitle.getText().toString());
                data.setGenre(etGenre.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                String ageLimit = "";
                if (spinnerR.getSelectedItemPosition() == 0) {
                    ageLimit = "G";
                } else if (spinnerR.getSelectedItemPosition() == 1) {
                    ageLimit = "PG";
                } else if (spinnerR.getSelectedItemPosition() == 2) {
                    ageLimit = "PG13";
                } else if (spinnerR.getSelectedItemPosition() == 3) {
                    ageLimit = "NC16";
                } else if (spinnerR.getSelectedItemPosition() == 4) {
                    ageLimit = "M18";
                } else if (spinnerR.getSelectedItemPosition() == 5) {
                    ageLimit = "R21";
                }
                data.setRating(ageLimit);
                dbh.updateMovies(data);
                dbh.close();
                finish();

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovies.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie " + etTitle.getText().toString());
                myBuilder.setCancelable(false);

                //Configure the 'Positive' button
                myBuilder.setPositiveButton("DELETE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper dbh = new DBHelper(ModifyMovies.this);
                        dbh.deleteMovies(data.getId());
                        finish();
                    }
                });
                myBuilder.setNeutralButton("CANCEL", null);
                AlertDialog myDialog = myBuilder.create();
                myDialog.show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}