package com.example.myapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        holder.unLikeCount.setText(String.valueOf(message1.numberOfUnlikes()-1));
        holder.likeCount.setText(String.valueOf(message1.numberOfLikes()-1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView description;
        TextView messageContent;
        ImageButton like;
        ImageButton commentList;
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
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liked();
                }
            });
            unlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    unliked();
                }
            });
        }
        public void liked()
        {
            int pos = this.getAbsoluteAdapterPosition();
            String curruser = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String messageId = list.get(pos).getMessage_id();
            String toId = list.get(pos).getTo_id();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("global").child(messageId);
            message message1 = list.get(pos);
            if(message1.likedList.contains(curruser))
            {
                message1.remove_from_likedList(curruser);
            }
            else
            {
                message1.remove_from_unlikedList(curruser);
                message1.Add_to_likedList(curruser);
            }

            reference.setValue(message1);

        }
        public void unliked()
        {
            int pos = this.getAbsoluteAdapterPosition();
            String curruser = FirebaseAuth.getInstance().getCurrentUser().getUid();
            String messageId = list.get(pos).getMessage_id();
            String toId = list.get(pos).getTo_id();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("global").child(messageId);
            message message1 = list.get(pos);
            if(message1.unlikedList.contains(curruser))
            {
                message1.remove_from_unlikedList(curruser);
            }
            else
            {
                message1.remove_from_likedList(curruser);
                message1.Add_to_unlikedList(curruser);
            }
            reference.setValue(message1);
        }
    }
}
