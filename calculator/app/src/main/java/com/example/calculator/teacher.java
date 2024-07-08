package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class teacher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void Cross (View view){
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);
        Button crossButton = (Button) findViewById(R.id.cross);
        TextView resText = (TextView) findViewById(R.id.res);

        double result = Double.parseDouble(num1.getText().toString())*Double.parseDouble(num2.getText().toString());
        resText.setText(String.valueOf(result));

    }

    public void Divide (View view){
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);
        TextView resText = (TextView) findViewById(R.id.res);

        double n1 = Double.parseDouble(num1.getText().toString());
        double n2 = Double.parseDouble(num2.getText().toString());

        if (n2!=0){
            double result = n1 / n2;
            resText.setText(String.valueOf(result));
        }else{
            resText.setText("num2 can't be 0. Please input other number!");
        }
    }
}
