package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Comments_Page extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_page);
        String messageId = getIntent().getStringExtra("messageId");
        recyclerView = findViewById(R.id.recyclercomment);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("global").child(messageId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                message message1 = snapshot.getValue(message.class);
                List<Comments> commentsList = message1.getCommentList();
                CommentAdapter adapter = new CommentAdapter(commentsList,Comments_Page.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        imageButton = findViewById(R.id.addComment);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comments_Page.this,addnewcomment.class);
                intent.putExtra("messageid",messageId);
                startActivity(intent);
            }
        });
    }
}