package com.randob.draft;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DraftAddActivity extends AppCompatActivity {
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draft_add);

        final EditText title = findViewById(R.id.addTitle);
        final EditText text = findViewById(R.id.addText);
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!title.getText().toString().isEmpty() && !text.getText().toString().isEmpty()) {
                    sendPost(title.getText().toString(), text.getText().toString());
                }
            }
        });
    }

    public void sendPost(String title, String text) {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiInterface.createDraft(new Draft(title, text)).enqueue(new Callback<Draft>() {
            @Override
            public void onResponse(Call<Draft> call, Response<Draft> response) {
                if(response.isSuccessful()) {
                    Log.i("OK", "post submitted to API." + response.body().toString());
                    Toast.makeText(getApplicationContext(), "Draft successfully created!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Draft> call, Throwable t) {
                Log.e("ERROR", "Unable to submit post to API.");
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG).show();
            }
        });
    }
}