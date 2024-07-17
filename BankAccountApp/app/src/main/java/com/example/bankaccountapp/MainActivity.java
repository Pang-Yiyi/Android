package com.example.bankaccountapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> intentActivityResultLauncher;
    private double TWDTotal = 0;
    private double JPYTotal = 0;
    private double USDTotal = 0;
    private int count = 0;

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

        intentActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getData() != null && result.getResultCode() == Activity.RESULT_OK) {
                            handleActivityResult(result.getData());
                        }
                    }
                }
        );
    }

    private void handleActivityResult(Intent data) {
        double twdResult = data.getDoubleExtra("TWDResult", 0);
        if (twdResult != 0) {
            if (TWDTotal + twdResult < 0) {
                count += 1;
            } else {
                TWDTotal += twdResult;
                count = 0;
            }
        }

        double j2tExchangeResult = data.getDoubleExtra("J2T_ExchangeResult", 0);
        double t2jExchangeResult = data.getDoubleExtra("T2J_ExchangeResult", 0);

        if (j2tExchangeResult != 0) {
            double j2t_jpyAmount = data.getDoubleExtra("J2T_JPY", 0);
            if (JPYTotal - j2t_jpyAmount < 0) {
                count += 1;
            } else {
                JPYTotal -= j2t_jpyAmount;
                TWDTotal += j2tExchangeResult;
                count = 0;
            }
        } else if (t2jExchangeResult != 0) {
            double t2j_twdAmount = data.getDoubleExtra("T2J_TWD", 0);
            if (TWDTotal - t2j_twdAmount < 0) {
                count += 1;
            } else {
                TWDTotal -= t2j_twdAmount;
                JPYTotal += t2jExchangeResult;
                count = 0;
            }
        }

        double u2tExchangeResult = data.getDoubleExtra("U2T_ExchangeResult", 0);
        double t2uExchangeResult = data.getDoubleExtra("T2U_ExchangeResult", 0);

        if (u2tExchangeResult != 0) {
            double u2t_usdAmount = data.getDoubleExtra("U2T_USD", 0);
            if (USDTotal - u2t_usdAmount < 0) {
                count += 1;
            } else {
                USDTotal -= u2t_usdAmount;
                TWDTotal += u2tExchangeResult;
                count = 0;
            }
        } else if (t2uExchangeResult != 0) {
            double t2u_twdAmount = data.getDoubleExtra("T2U_TWD", 0);
            if (TWDTotal - t2u_twdAmount < 0) {
                count += 1;
            } else {
                TWDTotal -= t2u_twdAmount;
                USDTotal += t2uExchangeResult;
                count = 0;
            }
        }

        updateUI();
    }

    public void Go2TWDAccount(View view) {
        Intent intent = new Intent(this, TWDAccountActivity.class);
        intentActivityResultLauncher.launch(intent);
    }

    public void Go2JPYExchange(View view) {
        Intent intent = new Intent(this, JPYCurrencyExchangeActivity.class);
        intentActivityResultLauncher.launch(intent);
    }

    public void Go2USDExchange(View view) {
        Intent intent = new Intent(this, CurrencyExchangeActivity.class);
        intentActivityResultLauncher.launch(intent);
    }

    public void updateUI() {
        TextView NTD_result = findViewById(R.id.NTDResult);
        NTD_result.setText(String.valueOf(TWDTotal));

        TextView JPY_result = findViewById(R.id.JPYResult);
        JPY_result.setText(String.valueOf(JPYTotal));

        TextView USD_result = findViewById(R.id.USDResult);
        USD_result.setText(String.valueOf(USDTotal));

        TextView TextResult = findViewById(R.id.Result);
        if (count != 0) {
            TextResult.setText("餘額不足，交易失敗");
            TextResult.setTextColor(Color.parseColor("#CC0000"));
        } else {
            TextResult.setText("交易成功");
            TextResult.setTextColor(Color.parseColor("#00CC00"));
        }
    }
}
