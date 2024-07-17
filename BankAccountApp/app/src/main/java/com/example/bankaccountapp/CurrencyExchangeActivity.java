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

public class CurrencyExchangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_currency_exchange);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void USDExchange(View view) {
        EditText money = findViewById(R.id.money);
        EditText USDRate = findViewById(R.id.USDRate);

        double rate = Double.parseDouble(USDRate.getText().toString());
        double U2T_Exchange = 0;
        double T2U_Exchange = 0;

        double USD = 0;
        double TWD = 0;

        if (view.getId() == R.id.U2T) {
            USD = Double.parseDouble(money.getText().toString());
            U2T_Exchange = USD * rate;
        } else if (view.getId() == R.id.T2U) {
            TWD = Double.parseDouble(money.getText().toString());
            T2U_Exchange = TWD / rate;
        }

        Intent intent = new Intent();
        intent.putExtra("U2T_USD", USD);
        intent.putExtra("T2U_TWD", TWD);
        intent.putExtra("U2T_ExchangeResult", U2T_Exchange);
        intent.putExtra("T2U_ExchangeResult", T2U_Exchange);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}