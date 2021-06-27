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

import com.google.firebase.auth.FirebaseAuth;

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
            String curruserid=FirebaseAuth.getInstance().getCurrentUser().getUid();
            if(list.get(pos).likedList.contains(curruserid))
            {
                //remove previous like
                //and if the unliked list also contains the user remove it
                //update like count
                list.get(pos).remove_from_likedList(curruserid);
            }
            else
            {
                //add user to the liked list
                //update like count
                list.get(pos).Add_to_likedList(curruserid);
                list.get(pos).remove_from_unlikedList(curruserid);
            }
        }
        public void unliked()
        {
            int pos = this.getAbsoluteAdapterPosition();
            String curruserid=FirebaseAuth.getInstance().getCurrentUser().getUid();
            if(list.get(pos).unlikedList.contains(curruserid))
            {
                //remove previous unlike
                //and if the liked list also contains the user remove it
                //update unlike count
                list.get(pos).remove_from_unlikedList(curruserid);
            }
            else
            {
                //add user to the unliked list
                //update unlike count
                list.get(pos).Add_to_unlikedList(curruserid);
                list.get(pos).remove_from_likedList(curruserid);
            }
        }
    }
}
