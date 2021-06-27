package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.airbnb.lottie.L;
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
    LinearLayoutManager linearLayoutManager;
    String messageId;
    List<Comments> list;
    message message1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments_page);
        recyclerView = findViewById(R.id.recyclercomment);
        imageButton = findViewById(R.id.addComment);
        linearLayoutManager = new LinearLayoutManager(Comments_Page.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        list =new  ArrayList<>();
        messageId = getIntent().getStringExtra("messageId");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("global").child(messageId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                message1=snapshot.getValue(message.class);
                list = message1.getCommentList();
                CommentAdapter adapter = new CommentAdapter(list,Comments_Page.this);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Comments_Page.this,addnewcomment.class);
                intent.putExtra("messageId",messageId);
                startActivity(intent);
            }
        });
    }
}