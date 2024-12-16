package com.example.calc_lab3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText txtDisplay;
    private double firstValue = 0;
    private double secondValue = 0;
    private String operator = "";
    private boolean isNewOp = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtDisplay = findViewById(R.id.txtDisplay);
        setNumberButtonListeners();
        setOperatorButtonListeners();
    }

    private void setNumberButtonListeners() {
        int[] numberButtons = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewOp) {
                    txtDisplay.setText("");
                    isNewOp = false;
                }
                Button b = (Button) v;
                txtDisplay.append(b.getText().toString());
            }
        };

        for (int id : numberButtons) {
            findViewById(id).setOnClickListener(listener);
        }

        // Добавляем слушатель для кнопки точки
        findViewById(R.id.btnDot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = txtDisplay.getText().toString();
                if (isNewOp) {
                    txtDisplay.setText("0.");
                    isNewOp = false;
                } else if (!currentText.contains(".")) {
                    txtDisplay.append(".");
                }
            }
        });
    }

    private void setOperatorButtonListeners() {
        findViewById(R.id.btnAdd).setOnClickListener(opListener);
        findViewById(R.id.btnSubtract).setOnClickListener(opListener);
        findViewById(R.id.btnMultiply).setOnClickListener(opListener);
        findViewById(R.id.btnDivide).setOnClickListener(opListener);
        findViewById(R.id.btnSqrt).setOnClickListener(sqrtListener);
        findViewById(R.id.btnEqual).setOnClickListener(equalListener);
        findViewById(R.id.btnClear).setOnClickListener(clearListener);
        findViewById(R.id.btnBack).setOnClickListener(backListener);
        findViewById(R.id.btnSignChange).setOnClickListener(signChangeListener);
    }

    // Operator listener
    private View.OnClickListener opListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button b = (Button) v;
            firstValue = Double.parseDouble(txtDisplay.getText().toString());
            operator = b.getText().toString();
            isNewOp = true;
        }
    };

    // Equals listener
    private View.OnClickListener equalListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            secondValue = Double.parseDouble(txtDisplay.getText().toString());
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstValue + secondValue;
                    break;
                case "-":
                    result = firstValue - secondValue;
                    break;
                case "*":
                    result = firstValue * secondValue;
                    break;
                case "/":
                    if (secondValue != 0) result = firstValue / secondValue;
                    break;
            }
            txtDisplay.setText(String.valueOf(result));
        }
    };

    // Square root listener
    private View.OnClickListener sqrtListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double value = Double.parseDouble(txtDisplay.getText().toString());
            txtDisplay.setText(String.valueOf(Math.sqrt(value)));
        }
    };

    // Clear button listener
    private View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            txtDisplay.setText("");
            firstValue = 0;
            secondValue = 0;
            isNewOp = true;
        }
    };

    // Back button listener
    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String current = txtDisplay.getText().toString();
            if (current.length() > 0) {
                txtDisplay.setText(current.substring(0, current.length() - 1));
            }
        }
    };

    // Sign change listener
    private View.OnClickListener signChangeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            double value = Double.parseDouble(txtDisplay.getText().toString());
            txtDisplay.setText(String.valueOf(value * -1));
        }
    };
}
