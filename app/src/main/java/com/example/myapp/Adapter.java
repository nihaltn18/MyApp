package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {

    private List<userPerson> list;
    private List<userPerson> AllList;
    Context context;

    public Adapter(List<userPerson> list,Context context) {
        this.list = list;
        this.context=context;
        AllList = new ArrayList<>(list);
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

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<userPerson> filtered = new ArrayList<>();
            if(constraint.toString().isEmpty())
            {
                filtered.addAll(AllList);
            }
            else
            {
                for (userPerson person : AllList)
                {
                    if(person.getName().toLowerCase().contains(constraint.toString().toLowerCase()))
                    {
                        filtered.add(person);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values=filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((Collection<? extends userPerson>) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name;
        TextView email;
        TextView phno;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            phno = itemView.findViewById(R.id.phoneno);
            name.setOnClickListener(this);
            phno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    save_number(v);
                }
            });
        }
        public void save_number(View v)
        {
            int pos = this.getAbsoluteAdapterPosition();
            userPerson person = list.get(pos);
            final Intent intent= new Intent(ContactsContract.Intents.SHOW_OR_CREATE_CONTACT, Uri.parse("tel:"+person.getPhone_number()));
            intent.putExtra(ContactsContract.Intents.EXTRA_FORCE_CREATE,true);
            context.startActivity(intent);
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
