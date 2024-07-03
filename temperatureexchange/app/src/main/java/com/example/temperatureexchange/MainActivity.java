package com.example.temperatureexchange;

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
    public void C2F(View view){
        TextView exchangeText = (TextView)findViewById(R.id.result);
        Button C2FButton = (Button)findViewById(R.id.C2F);
        EditText temText = (EditText)findViewById(R.id.temperature);

        int temperature = Integer.parseInt(temText.getText().toString());
        int result = Integer.parseInt(exchangeText.getText().toString());

        result = temperature * 9/5 + 32;
        exchangeText.setText(String.valueOf(result));


    }

    public void F2C(View view){
        TextView exchangeText = (TextView)findViewById(R.id.result);
        Button F2CButton = (Button)findViewById(R.id.F2C);
        EditText temText = (EditText)findViewById(R.id.temperature);

        int temperature = Integer.parseInt(temText.getText().toString());
        int result = Integer.parseInt(exchangeText.getText().toString());

        result = (temperature - 32) * 5/9;
        exchangeText.setText(String.valueOf(result));


    }
}