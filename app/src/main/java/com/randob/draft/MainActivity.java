package com.randob.draft;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private TextView textViewResult;
    ApiInterface apiInterface;
    RecyclerView recyclerView;
    List<Draft> drafts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        getInfo(this);

    }

    public void getInfo(Context ct){
        Call<List<Draft>> call = apiInterface.getDrafts();
        call.enqueue(new Callback<List<Draft>>() {
            @Override
            public void onResponse(Call<List<Draft>> call, Response<List<Draft>> response) {
                if(!response.isSuccessful()){
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

//    public void getInfo(View v){
//        Call<List<Draft>> call = apiInterface.getDrafts();
//        call.enqueue(new Callback<List<Draft>>() {
//            @Override
//            public void onResponse(Call<List<Draft>> call, Response<List<Draft>> response) {
//                if(!response.isSuccessful()){
//                    textViewResult.setText(response.code());
//                    return;
//                }
//
//                List<Draft> drafts = response.body();
//                for(Draft draft: drafts){
//                    String content = "";
//                    content += draft.getId() + "\n";
//                    content += draft.getTitle() + "\n";
//                    content += draft.getText() + "\n\n";
//
//                    textViewResult.append(content);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Draft>> call, Throwable t) {
//                textViewResult.setText(t.getMessage());
//            }
//        });
//    }

//    public void getOneDraft(View v){
//        Call<Draft> call = apiInterface.getDraftById(2);
//        call.enqueue(new Callback<Draft>() {
//            @Override
//            public void onResponse(Call<Draft> call, Response<Draft> response) {
//
//                textViewResult.setText(response.body().getTitle() + "\n" + response.body().getText());
//            }
//
//            @Override
//            public void onFailure(Call<Draft> call, Throwable t) {
//
//            }
//        });
//    }

//    public void createDraft(View v){
//        Draft draft = new Draft("This title is generated in app", "Hello world my name is Dinesh!");
//        Call<Draft> postCall = apiInterface.createDraft(draft);
//        postCall.enqueue(new Callback<Draft>() {
//            @Override
//            public void onResponse(Call<Draft> call, Response<Draft> response) {
//                Log.e("RESPONSE", response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<Draft> call, Throwable t) {
//
//            }
//        });
//    }
}