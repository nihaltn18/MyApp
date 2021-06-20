package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class sendmessage extends AppCompatActivity {

    private Button button;
    private String id;
    private String to_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmessage);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        Intent intent =getIntent();
        id = intent.getStringExtra("id");
        to_name = intent.getStringExtra("toname");
    }

    private void submit() {
        EditText editText = findViewById(R.id.editTextTextPersonName);
        Spinner privacy = findViewById(R.id.privacy);
        Spinner anonymous = findViewById(R.id.anonymousy);
        String message_ = editText.getText().toString();
        boolean is_private;
        boolean is_anonymous;
        if(privacy.getSelectedItem().toString().equals("PRIVATE"))
            is_private=true;
        else
            is_private=false;
        if(anonymous.getSelectedItem().toString().equals("ANONYMOUS"))
            is_anonymous=true;
        else
            is_anonymous=false;
        message message1 = new message(message_, FirebaseAuth.getInstance().getCurrentUser().getUid(),id,is_private,is_anonymous,FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),to_name);
        if(message1.isPrivate())
        {
            DatabaseReference database = FirebaseDatabase.getInstance().getReference(id);
            String id1 = database.push().getKey();
            database.child(id1).setValue(message1);
        }
        else
        {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("global");
            String id1 = databaseReference.push().getKey();
            databaseReference.child(id1).setValue(message1);
        }
        Toast.makeText(sendmessage.this,"successfully sent the message",Toast.LENGTH_LONG).show();
        startActivity(new Intent(sendmessage.this,homepage.class));
    }
}