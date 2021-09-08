package com.example.ihc1_3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    TextView textViewMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewMessage = findViewById(R.id.textViewMessage);

        String message = getIntent().getStringExtra("message");
        textViewMessage.setText(message);
    }
}