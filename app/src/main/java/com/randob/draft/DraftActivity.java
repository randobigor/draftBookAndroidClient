package com.randob.draft;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class DraftActivity extends AppCompatActivity {

    EditText title, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);

        title = findViewById(R.id.editTitle);
        text = findViewById(R.id.editText);

        getData();
    }

    public void getData(){
        title.setText(getIntent().getStringExtra("title"));
        text.setText(getIntent().getStringExtra("text"));
    }
}