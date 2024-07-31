package com.example.eventdemo;

import android.graphics.Color;
import android.os.Binder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View

        .OnClickListener, View.OnLongClickListener, View.OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.tv_text), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button b = (Button) findViewById(R.id.button);
        b.setOnClickListener(this);
        b.setOnLongClickListener(this);

        ConstraintLayout main = (ConstraintLayout) findViewById(R.id.main);
        main.setOnTouchListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView tv_text = (TextView) findViewById(R.id.tv_text);
        TextView tv_size = (TextView) findViewById(R.id.tv_size);

        float currentSizeSp = tv_text.getTextSize() / getResources().getDisplayMetrics().scaledDensity;
        tv_text.setTextSize(currentSizeSp + 5);
        tv_size.setText(String.valueOf(currentSizeSp + 5));
    }

    @Override
    public boolean onLongClick(View view) {
        TextView tv_text = (TextView) findViewById(R.id.tv_text);
        TextView tv_size = (TextView) findViewById(R.id.tv_size);

        float currentSizeSp = tv_text.getTextSize() / getResources().getDisplayMetrics().scaledDensity;
        tv_text.setTextSize(currentSizeSp - 5);
        tv_size.setText(String.valueOf(currentSizeSp - 5));
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        TextView tv_action = (TextView) findViewById(R.id.action);
        TextView tv_position = (TextView) findViewById(R.id.position);

        int act = motionEvent.getAction();
        switch (act){
            case MotionEvent.ACTION_DOWN:
                tv_action.setText("ACTION_DOWN");
                tv_action.setTextColor(Color.parseColor("#992233"));
                break;
            case MotionEvent.ACTION_MOVE:
                tv_action.setText("ACTION_MOVE");
                tv_action.setTextColor(Color.parseColor("#229933"));
                break;
            case MotionEvent.ACTION_UP:
                tv_action.setText("ACTION_UP");
                tv_action.setTextColor(Color.parseColor("#223399"));
                break;
        }

        tv_position.setText("X:" + motionEvent.getX() + "\n" + "Y:" + motionEvent.getY());
        return true;
    }
}