package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class messageAdapter extends RecyclerView.Adapter<messageAdapter.ViewHolder> {
    List<message> list;
    Context context;
    private String decs;

    public messageAdapter(List<message> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public messageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlemessage,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull messageAdapter.ViewHolder holder, int position) {
        message message1 = list.get(position);
            if (message1.isAnonymous())
                decs = "FROM --> ANONYMOUS ";
            else
                decs = "FROM --> " + message1.getFrom_name();
            decs = decs+" to --> "+message1.getTo_name();
            holder.details.setText(decs);
            holder.msgcontent.setText(message1.getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView details;
        TextView msgcontent;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            details = itemView.findViewById(R.id.details);
            msgcontent = itemView.findViewById(R.id.msgcontent);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,detailed_message.class);
            intent.putExtra("details",details.getText().toString());
            intent.putExtra("msg",list.get(this.getAbsoluteAdapterPosition()).getMessage());
            context.startActivity(intent);
        }
    }
}
