package com.example.myapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {
    List<Comments> list;
    Context context;
    private String decs;

    public CommentAdapter(List<Comments> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_comment,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comments comments = list.get(position);
        holder.details.setText(comments.getFrom());
        holder.content.setText(comments.getCommentContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView details;
        TextView content;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            details = itemView.findViewById(R.id.commentname);
            content = itemView.findViewById(R.id.commentdetail);
        }
    }
}
