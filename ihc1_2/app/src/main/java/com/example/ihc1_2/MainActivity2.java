package com.example.ihc1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textView_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView_message = findViewById(R.id.textViewMessage);

        String message = getIntent().getStringExtra("message");
        textView_message.setText(message);
    }
}