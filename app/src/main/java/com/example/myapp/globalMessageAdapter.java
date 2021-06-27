package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class globalMessageAdapter extends RecyclerView.Adapter<globalMessageAdapter.ViewHolder> {
    List<message> list;
    Context context;
    private String decs;
    globalMessageAdapter(List<message> list,Context context)
    {
        this.context=context;
        this.list=list;
    }
    @NonNull
    @Override
    public globalMessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.global_message_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull globalMessageAdapter.ViewHolder holder, int position) {
        message message1 = list.get(position);
        if (message1.isAnonymous())
            decs = "FROM --> ANONYMOUS ";
        else
            decs = "FROM --> " + message1.getFrom_name();
        decs = decs+" to --> "+message1.getTo_name();
        holder.description.setText(decs);
        holder.messageContent.setText(message1.getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView description;
        TextView messageContent;
        ImageButton like;
        ImageView commentList;
        ImageButton unlike;
        TextView likeCount;
        TextView unLikeCount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            description=itemView.findViewById(R.id.desc);
            messageContent = itemView.findViewById(R.id.messagecontent);
            like=itemView.findViewById(R.id.like);
            commentList=itemView.findViewById(R.id.comment);
            unlike=itemView.findViewById(R.id.unlike);
            likeCount = itemView.findViewById(R.id.likeCount);
            unLikeCount = itemView.findViewById(R.id.unlikeCount);
        }
    }
}
