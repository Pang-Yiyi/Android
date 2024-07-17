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
    private double TWDTotal;
    private int count;

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
                        //寫另一個Activity回傳後，獲取回傳的資料之後的做法
                        if (result.getData() != null && result.getResultCode() == Activity.RESULT_OK) {
                            TWDTotal += result.getData().getDoubleExtra("TWDResult", -1);
                            if(TWDTotal<0){
                                TWDTotal -= result.getData().getDoubleExtra("TWDResult", -1);
                                count += 1;
                            }else{
                                count = 0;
                            }
                            updateUI();
                        }
                    }
                }
        );
    }

    public void Go2TWDAccount(View view) {
        Intent intent = new Intent(this, TWDAccountActivity.class);

        intentActivityResultLauncher.launch(intent);
    }

    public void updateUI() {
        TextView NTD_result = (TextView) findViewById(R.id.NTDResult);

        NTD_result.setText(String.valueOf(TWDTotal));

        TextView TextResult = (TextView) findViewById(R.id.Result);
        if(count != 0){
            TextResult.setText("餘額不足，交易失敗");
            TextResult.setTextColor(Color.parseColor("#CC0000"));
        }else{
            TextResult.setText("交易成功");
            TextResult.setTextColor(Color.parseColor("#00CC00"));
        }

    }

}