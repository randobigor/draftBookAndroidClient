package com.randob.draft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DraftActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    EditText title, text;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft);
        title = findViewById(R.id.editTitle);
        text = findViewById(R.id.editText);

        setData();

        Button saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!title.getText().toString().isEmpty() && !text.getText().toString().isEmpty()){
                    putData(id, new Draft(title.getText().toString(), text.getText().toString()));
                }
            }
        });

        Button deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });
    }

    public void setData(){
        id = Integer.parseInt(getIntent().getStringExtra("id"));
        title.setText(getIntent().getStringExtra("title"));
        text.setText(getIntent().getStringExtra("text"));
    }

    private void putData(int id, Draft body){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.updateDraft(id, body).enqueue(new Callback<Draft>() {
            @Override
            public void onResponse(Call<Draft> call, Response<Draft> response) {
                if(response.isSuccessful()) {
                    Log.i("OK", "post submitted to API." + response.body().toString());
                    Toast.makeText(getApplicationContext(), "Draft successfully edited!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Draft> call, Throwable t) {
                Log.e("ERROR", "Unable to submit post to API.");
                Toast.makeText(getApplicationContext(), "Something gone wrong", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteData(){
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.deleteDraft(id).enqueue(new Callback<Draft>() {
            @Override
            public void onResponse(Call<Draft> call, Response<Draft> response) {
                if(response.isSuccessful()) {
                    Log.i("OK", "post submitted to API." + response.body().toString());
                    Toast.makeText(getApplicationContext(), "Draft successfully deleted!", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Draft> call, Throwable t) {
                Log.e("ERROR", "Unable to submit post to API.");
                Toast.makeText(getApplicationContext(), "Something gone wrong", Toast.LENGTH_LONG).show();
            }
        });
    }
}