package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class addnewcomment extends AppCompatActivity {

    EditText editText;
    Button button;
    String messageId;
    message message1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewcomment);
        editText = findViewById(R.id.commentContent);
        button = findViewById(R.id.submitComment);
        messageId = getIntent().getStringExtra("messageId");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals(""))
                    Toast.makeText(addnewcomment.this,"please enter fome comment...",Toast.LENGTH_LONG).show();
                else
                    addcomment();
            }
        });
    }

    private void addcomment() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("global").child(messageId);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                message1 = snapshot.getValue(message.class);
                message1.addComment(FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),editText.getText().toString());
                reference.setValue(message1);
                Toast.makeText(addnewcomment.this,"successfully commented",Toast.LENGTH_LONG).show();
                startActivity(new Intent(addnewcomment.this,global.class));
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }

}