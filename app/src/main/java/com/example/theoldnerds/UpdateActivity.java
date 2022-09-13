package com.example.theoldnerds;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class UpdateActivity extends AppCompatActivity {

    EditText name_input, description_input, date_input, category_input;
    Button btnUpdate;
    RadioGroup radioUpdate;
    RadioButton radioButton;
    TextView CatUpdate;
    String comicCat, id, name, description, date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name_input = findViewById(R.id.etComicNameUpadte);
        description_input = findViewById(R.id.etComicDescriptionUpdate);
        date_input = findViewById(R.id.etComicDateOfAcqUpdate);
        btnUpdate = findViewById(R.id.btnUpdateItem);

        getAndsetIntentData();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabase myDB = new MyDatabase(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                description = description_input.getText().toString().trim();
                date = date_input.getText().toString();

                myDB.updateData(id, name, description, date);
            }
        });
    }

    void getAndsetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("description") && getIntent().hasExtra("date")) {
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            description = getIntent().getStringExtra("description");
            date = getIntent().getStringExtra("date");
            //set
            name_input.setText(name);
            description_input.setText(description);
            date_input.setText(date);
        } else {
            Toast.makeText(this, "No data. ", Toast.LENGTH_SHORT).show();
        }
    }
}