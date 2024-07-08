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

public class MainActivity extends AppCompatActivity {

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

    public void Plus (View view){
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);
        Button plusButton = (Button) findViewById(R.id.plus);
        TextView resText = (TextView) findViewById(R.id.res);

        int A = Integer.parseInt((num1.getText().toString()));
        int B = Integer.parseInt((num2.getText().toString()));
        int res = Integer.parseInt((resText.getText().toString()));

        res = A + B;
        resText.setText(String.valueOf(res));

    }

    public void Minus (View view){
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);
        Button minusButton = (Button) findViewById(R.id.minus);
        TextView resText = (TextView) findViewById(R.id.res);

        int A = Integer.parseInt((num1.getText().toString()));
        int B = Integer.parseInt((num2.getText().toString()));
        int res = Integer.parseInt((resText.getText().toString()));

        res = A - B;
        resText.setText(String.valueOf(res));

    }

    public void Cross (View view){
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);
        Button crossButton = (Button) findViewById(R.id.cross);
        TextView resText = (TextView) findViewById(R.id.res);

        int A = Integer.parseInt((num1.getText().toString()));
        int B = Integer.parseInt((num2.getText().toString()));
        int res = Integer.parseInt((resText.getText().toString()));

        res = A * B;
        resText.setText(String.valueOf(res));

    }

    public void Divide (View view){
        EditText num1 = (EditText) findViewById(R.id.num1);
        EditText num2 = (EditText) findViewById(R.id.num2);
        Button divideButton = (Button) findViewById(R.id.divide);
        TextView resText = (TextView) findViewById(R.id.res);

        int A = Integer.parseInt((num1.getText().toString()));
        int B = Integer.parseInt((num2.getText().toString()));
        int res = Integer.parseInt((resText.getText().toString()));

        res = A / B;
        resText.setText(String.valueOf(res));

    }
}