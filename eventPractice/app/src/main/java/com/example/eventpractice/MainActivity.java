package com.example.eventpractice;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View

        .OnClickListener{

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

        Button d = (Button) findViewById(R.id.increase);
        Button i = (Button) findViewById(R.id.decrease);
        d.setOnClickListener(this);
        i.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView tv_text = (TextView) findViewById(R.id.tv_text);
        TextView tv_size = (TextView) findViewById(R.id.tv_size);

        float currentSizeSp = tv_text.getTextSize() / getResources().getDisplayMetrics().scaledDensity;
        if(view.getId() == R.id.increase){
            tv_text.setTextSize(currentSizeSp + 5);
            tv_size.setText(String.valueOf(currentSizeSp + 5));
        }else if((view.getId() == R.id.decrease)){
            tv_text.setTextSize(currentSizeSp - 5);
            tv_size.setText(String.valueOf(currentSizeSp - 5));
        }

    }
}