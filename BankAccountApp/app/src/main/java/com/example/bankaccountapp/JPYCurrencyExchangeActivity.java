package com.example.bankaccountapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class JPYCurrencyExchangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jpycurrency_exchange);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void JPYExchange(View view) {
        EditText money = findViewById(R.id.money);
        EditText JPYRate = findViewById(R.id.JPYRate);

        double rate = Double.parseDouble(JPYRate.getText().toString());
        double J2T_Exchange = 0;
        double T2J_Exchange = 0;

        double JPY = 0;
        double TWD = 0;

        if (view.getId() == R.id.J2T) {
            JPY = Double.parseDouble(money.getText().toString());
            J2T_Exchange = JPY * rate;
        } else if (view.getId() == R.id.T2J) {
            TWD = Double.parseDouble(money.getText().toString());
            T2J_Exchange = TWD / rate;
        }

        Intent intent = new Intent();
        intent.putExtra("J2T_JPY", JPY);
        intent.putExtra("T2J_TWD", TWD);
        intent.putExtra("J2T_ExchangeResult", J2T_Exchange);
        intent.putExtra("T2J_ExchangeResult", T2J_Exchange);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
