package com.example.calculator_hw3;

import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import androidx.constraintlayout.widget.ConstraintLayout;


public class MainActivity extends AppCompatActivity {

    private String currentInput = "";
    private String lastOperator = "";
    private double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton[] numberButtons = {
                findViewById(R.id.button_0), findViewById(R.id.button_1), findViewById(R.id.button_2),
                findViewById(R.id.button_3), findViewById(R.id.button_4), findViewById(R.id.button_5),
                findViewById(R.id.button_6), findViewById(R.id.button_7), findViewById(R.id.button_8),
                findViewById(R.id.button_9)
        };

        for (MaterialButton button : numberButtons) {
            button.setOnClickListener(v -> {
                String number = button.getText().toString();
                currentInput += number;
                updateDisplay(currentInput);
            });
        }

        findViewById(R.id.button_dot).setOnClickListener(v -> {
            if (!currentInput.contains(".")) {
                currentInput += ".";
                updateDisplay(currentInput);
            }
        });


        findViewById(R.id.button_percent).setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                double number = Double.parseDouble(currentInput);
                number = number / 100;
                currentInput = String.valueOf(number);
                updateDisplay(currentInput);
            }
        });

        findViewById(R.id.button_plus).setOnClickListener(v -> applyOperation("+"));
        findViewById(R.id.button_minus).setOnClickListener(v -> applyOperation("-"));
        findViewById(R.id.button_multiply).setOnClickListener(v -> applyOperation("*"));
        findViewById(R.id.button_divide).setOnClickListener(v -> applyOperation("/"));
        findViewById(R.id.button_equals).setOnClickListener(v -> calculateResult());
        findViewById(R.id.button_clear).setOnClickListener(v -> clearAll());
    }


        private void appendNumber(String number) {
            currentInput += number;
            updateDisplay(currentInput);
        }

        private void applyOperation(String operator) {
            if (!currentInput.isEmpty()) {
                if (!lastOperator.isEmpty()) {
                    calculateResult();
                } else {
                    result = Double.parseDouble(currentInput);
                }
                lastOperator = operator;
                currentInput = "";
            }
        }


        private void calculateResult() {
            if (!currentInput.isEmpty() && !lastOperator.isEmpty()) {
                double currentNumber = Double.parseDouble(currentInput);
                if (lastOperator.equals("+")) {
                    result += currentNumber;
                } else if (lastOperator.equals("-")) {
                    result -= currentNumber;
                } else if (lastOperator.equals("*")) {
                    result *= currentNumber;
                } else if (lastOperator.equals("/")) {
                    if (currentNumber != 0) {
                        result /= currentNumber;
                    } else {
                        result = 0;
                    }
                }
                currentInput = String.valueOf(result);
                lastOperator = "";
                updateDisplay(currentInput);
            }
        }

        private void clearAll() {
            currentInput = "";
            lastOperator = "";
            result = 0.0;
            updateDisplay("0");
        }


        private void updateDisplay(String value) {
            TextView displayText = findViewById(R.id.display_text);
            displayText.setText(value);
        }
    }
