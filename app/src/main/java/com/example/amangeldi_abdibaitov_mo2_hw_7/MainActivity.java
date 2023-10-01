package com.example.amangeldi_abdibaitov_mo2_hw_7;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Button nextButton;

    private StringBuilder input = new StringBuilder();
    private double num1 = Double.NaN;
    private Intent nextActivity;
    private char operator;

    public static final String KEY = "Amount";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nextActivity = new Intent(MainActivity.this, SecondActivity.class);

        textView = findViewById(R.id.text_view);
        nextButton = findViewById(R.id.Button_Next);
        nextButton.setVisibility(View.GONE);

        setupNumberButtons();
        setupOperationButtons();

        Button equalButton = findViewById(R.id.Button_Equal);
        equalButton.setOnClickListener(v -> {
            compute();
            operator = '=';
            updateTextView();

            // Сделайте кнопку "Next" видимой при нажатии на кнопку "Equal"
            nextButton.setVisibility(View.VISIBLE);
        });

        Button clearButton = findViewById(R.id.Button_All_Clean);
        clearButton.setOnClickListener(v -> {
            clear();
            updateTextView();
        });

        Button dotButton = findViewById(R.id.Button_Point);
        dotButton.setOnClickListener(v -> {
            if (input.indexOf(".") == -1) {
                input.append(".");
                updateTextView();
            }
        });

        Button percentButton = findViewById(R.id.Button_Percent);
        percentButton.setOnClickListener(v -> {
            if (!Double.isNaN(num1)) {
                compute();
            }
            operator = '%';
            num1 = Double.parseDouble(input.toString());
            input.setLength(0);
            updateTextView();
        });
    }

    public void onNextActivity(View view) {
        String result = textView.getText().toString();
        nextActivity.putExtra(KEY, result);
        startActivity(nextActivity);
    }

    private void setupNumberButtons() {
        int[] numberButtonIds = {
                R.id.Button_Number_Zero,
                R.id.Button_Number_One,
                R.id.Button_Number_Two,
                R.id.Button_Number_Three,
                R.id.Button_Number_Four,
                R.id.Button_Number_Five,
                R.id.Button_Number_Six,
                R.id.Button_Number_Seven,
                R.id.Button_Number_Eight,
                R.id.Button_Number_Nine
        };

        for (int id : numberButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(v -> {
                Button clickedButton = (Button) v;
                input.append(clickedButton.getText());
                updateTextView();
            });
        }
    }

    private void setupOperationButtons() {
        int[] operationButtonIds = {
                R.id.Button_Plus,
                R.id.Button_Minus,
                R.id.Button_Multiply,
                R.id.Button_Divide
        };

        for (int id : operationButtonIds) {
            Button button = findViewById(id);
            button.setOnClickListener(v -> {
                if (!Double.isNaN(num1)) {
                    compute();
                }
                Button clickedButton = (Button) v;
                operator = clickedButton.getText().charAt(0);
                num1 = Double.parseDouble(input.toString());
                input.setLength(0);
                updateTextView();
            });
        }
    }

    private void updateTextView() {
        textView.setText(input.toString());
    }

    private void compute() {
        if (!Double.isNaN(num1)) {
            double num2 = Double.parseDouble(input.toString());
            switch (operator) {
                case '+':
                    num1 += num2;
                    break;
                case '-':
                    num1 -= num2;
                    break;
                case 'x':
                    num1 *= num2;
                    break;
                case '/':
                    num1 /= num2;
                    break;
                case '%':
                    num1 = num1 * num2 / 100;
                    break;
                case '=':
                    break;
            }
            input = new StringBuilder(String.valueOf(num1));
        }
    }

    private void clear() {
        input.setLength(0);
        num1 = Double.NaN;
        operator = '\0';
    }
}
