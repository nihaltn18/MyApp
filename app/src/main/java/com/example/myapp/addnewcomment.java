package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnewcomment);
        editText = findViewById(R.id.commentContent);
        button = findViewById(R.id.submitComment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcomment();
            }
        });
        messageId = getIntent().getStringExtra("messageid");
    }
    public void addcomment()
    {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("global").child(messageId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String commentval = editText.getText().toString();
                message message1 = snapshot.getValue(message.class);
                message1.addComment(false, FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),message1.getTo_name(),FirebaseAuth.getInstance().getCurrentUser().getUid(),message1.getTo_id(),commentval);
                reference.setValue(message1);
                Toast.makeText(addnewcomment.this,"successfully commented",Toast.LENGTH_LONG).show();
                startActivity(new Intent(addnewcomment.this,Comments_Page.class));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}