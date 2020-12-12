package com.randob.draft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        getInfo(this);

        addButton = findViewById(R.id.addButtonActivity);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddDraftActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getInfo(this);
    }

    public void goToAddDraftActivity() {
        Intent intent = new Intent(this, DraftAddActivity.class);
        startActivity(intent);
    }

    public void getInfo(Context ct) {
        Call<List<Draft>> call = apiInterface.getDrafts();
        call.enqueue(new Callback<List<Draft>>() {
            @Override
            public void onResponse(Call<List<Draft>> call, Response<List<Draft>> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                recyclerView = findViewById(R.id.recyclerView);

                CustomAdapter customAdapter = new CustomAdapter(ct, response.body());
                recyclerView.setAdapter(customAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(ct));
            }

            @Override
            public void onFailure(Call<List<Draft>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}