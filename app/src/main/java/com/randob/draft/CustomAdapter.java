package com.randob.draft;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

    List<Draft> drafts;
    Context context;

    public CustomAdapter(Context ct, List<Draft> drafts){
        this.context = ct;
        this.drafts = drafts;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        holder.tvTitle.setText(drafts.get(position).getTitle());
        holder.tvText.setText(drafts.get(position).getText());

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DraftActivity.class);
                intent.putExtra("title", drafts.get(position).getTitle());
                intent.putExtra("text", drafts.get(position).getText());
                intent.putExtra("id", Integer.toString(drafts.get(position).getId()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drafts.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvText;
        CardView card;
        ConstraintLayout constraintLayout;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvText = itemView.findViewById(R.id.tvText);
            card = itemView.findViewById(R.id.cardView);
            constraintLayout = itemView.findViewById(R.id.customRow);
        }
    }
}
