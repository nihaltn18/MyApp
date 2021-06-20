package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class detailed_message extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_message);
        Intent intent = getIntent();
        TextView dec=findViewById(R.id.textView);
        TextView msg=findViewById(R.id.textView2);
        dec.setText(intent.getStringExtra("details"));
        msg.setText(intent.getStringExtra("msg"));
    }
}