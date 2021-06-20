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

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<userPerson> list;
    Context context;

    public Adapter(List<userPerson> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        userPerson person = list.get(position);
        holder.name.setText(person.getName());
        holder.phno.setText(person.getPhone_number());
        holder.email.setText(person.getEmail());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView email;
        TextView phno;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            phno = itemView.findViewById(R.id.phoneno);
        }

        @Override
        public void onClick(View v) {
            int position = this.getAbsoluteAdapterPosition();
            Toast.makeText(context,list.get(position).getEmail()+list.get(position).getName()+list.get(position).getPhone_number(),Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context,sendmessage.class);
            intent.putExtra("id",list.get(position).getUserId());
            intent.putExtra("toname",list.get(position).getName());
            context.startActivity(intent);
        }
    }
}
