package com.example.bankaccountapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TWDAccountActivity extends AppCompatActivity {
    private double TWDTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_twdaccount);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void TWDAccount(View view){
        EditText money = (EditText)findViewById(R.id.money);

        if(view.getId() == R.id.deposit){
            double add = Double.parseDouble(money.getText().toString());
            TWDTotal += add;
        }else if(view.getId() == R.id.withdraw){
            double minus = Double.parseDouble(money.getText().toString());
            TWDTotal -= minus;
        }

        Intent intent = new Intent();
        intent.putExtra("TWDResult", TWDTotal);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}