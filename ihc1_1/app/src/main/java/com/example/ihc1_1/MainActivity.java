package com.example.ihc1_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textResult = findViewById(R.id.textView_result);
        EditText textNum1 = findViewById(R.id.editTextNumber_num1);
        EditText textNum2 = findViewById(R.id.editTextNumber_num2);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            String num1String = textNum1.getText().toString();
            String num2String = textNum2.getText().toString();

            if(!num1String.equals("") && !num2String.equals("")) {
                double sum = Double.parseDouble(num1String) + Double.parseDouble(num2String);
                textResult.setText(Double.toString(sum));
            }
        });
    }
}