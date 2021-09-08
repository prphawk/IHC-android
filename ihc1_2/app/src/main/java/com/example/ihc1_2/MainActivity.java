package com.example.ihc1_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(this::beginSecondActivity);

    }

    public void beginSecondActivity(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        EditText editTextMessage = findViewById(R.id.editTextMessage);
        intent.putExtra("message", editTextMessage.getText().toString());
        startActivity(intent);
    }
}