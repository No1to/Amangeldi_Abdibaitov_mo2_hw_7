package com.example.amangeldi_abdibaitov_mo2_hw_7;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private StringBuilder input = new StringBuilder();
    private double num1 = Double.NaN;
    private char operator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);


        setupNumberButtons();
        setupOperationButtons();


        Button equalButton = findViewById(R.id.Button_Equal);
        equalButton.setOnClickListener(v -> {
            compute();
            operator = '=';
            updateTextView();
        });


        Button clearButton = findViewById(R.id.Button_All_Clean);
        clearButton.setOnClickListener(v -> {
            clear();
            updateTextView();
        });


        Button multiplyButton = findViewById(R.id.Button_Multiply);
        multiplyButton.setOnClickListener(v -> {
            if (!Double.isNaN(num1)) {
                compute();
            }
            operator = 'x';
            num1 = Double.parseDouble(input.toString());
            input.setLength(0);
            updateTextView();
        });

        Button minusButton = findViewById(R.id.Button_Minus);
        minusButton.setOnClickListener(v -> {
            if (!Double.isNaN(num1)) {
                compute();
            }
            operator = '-';
            num1 = Double.parseDouble(input.toString());
            input.setLength(0);
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
                R.id.Button_Number_Eeight,
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