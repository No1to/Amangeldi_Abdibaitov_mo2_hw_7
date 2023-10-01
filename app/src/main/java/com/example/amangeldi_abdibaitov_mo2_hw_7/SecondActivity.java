package com.example.amangeldi_abdibaitov_mo2_hw_7;

import static com.example.amangeldi_abdibaitov_mo2_hw_7.MainActivity.KEY;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {
    private boolean isHeartFilled = false;
    private ImageButton heartButton;
    private Intent mainActivity;
    private String finish;

    public static String KEY2 = "Finish";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mainActivity = new Intent(SecondActivity.this, MainActivity.class);

        TextView textView = findViewById(R.id.result_text);

        String result = getIntent().getStringExtra(KEY);
        textView.setText(result);

        heartButton = findViewById(R.id.btn_heart);

        heartButton.setOnClickListener(v -> {
            if (isHeartFilled) {
                heartButton.setImageResource(R.drawable.ic_like);
            } else {
                heartButton.setImageResource(R.drawable.ic_like2);
            }
            isHeartFilled = !isHeartFilled;
        });
    }

    public void onDestroy(View view) {
        mainActivity.putExtra(KEY2, finish);
        finishAffinity();
    }
}