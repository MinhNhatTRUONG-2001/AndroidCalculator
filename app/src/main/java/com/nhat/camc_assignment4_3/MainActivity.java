package com.nhat.camc_assignment4_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //inputs[0]: first number; inputs[1]: operator; inputs[2]: second number
    private StringBuilder[] inputs = new StringBuilder[3];
    private int probe = 0;

    Button n0, n1, n2, n3, n4, n5, n6, n7, n8, n9;
    Button pm, dp, add, sub, mul, div, per, eql, c, ce;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < inputs.length; i++) {
            inputs[i] = new StringBuilder();
        }

        n0 = findViewById(R.id.n0_button);
        n1 = findViewById(R.id.n1_button);
        n2 = findViewById(R.id.n2_button);
        n3 = findViewById(R.id.n3_button);
        n4 = findViewById(R.id.n4_button);
        n5 = findViewById(R.id.n5_button);
        n6 = findViewById(R.id.n6_button);
        n7 = findViewById(R.id.n7_button);
        n8 = findViewById(R.id.n8_button);
        n9 = findViewById(R.id.n9_button);
        pm = findViewById(R.id.plus_minus_button);
        dp = findViewById(R.id.decimal_point_button);
        add = findViewById(R.id.add_button);
        sub = findViewById(R.id.subtract_button);
        mul = findViewById(R.id.multiply_button);
        div = findViewById(R.id.divide_button);
        per = findViewById(R.id.percent_button);
        eql = findViewById(R.id.equal_button);
        c = findViewById(R.id.clear_button);
        ce = findViewById(R.id.clear_all_button);

        display = findViewById(R.id.input_display);

        View.OnClickListener numberListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (probe == 1) {
                    display.setText("");
                    probe++;
                }
                Button clickedNumber = (Button) view;
                inputs[probe].append(clickedNumber.getText().toString());
                display.setText(inputs[probe]);
            }
        };
        n0.setOnClickListener(numberListener);
        n1.setOnClickListener(numberListener);
        n2.setOnClickListener(numberListener);
        n3.setOnClickListener(numberListener);
        n4.setOnClickListener(numberListener);
        n5.setOnClickListener(numberListener);
        n6.setOnClickListener(numberListener);
        n7.setOnClickListener(numberListener);
        n8.setOnClickListener(numberListener);
        n9.setOnClickListener(numberListener);

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (probe == 0 && inputs[probe].length() != 0) {
                    display.setText("");
                    probe++;
                    Button clickedOperator = (Button) view;
                    inputs[probe].replace(0, 1, clickedOperator.getText().toString());
                    display.setText(inputs[probe]);
                }
                else if (probe == 1) {
                    Button clickedOperator = (Button) view;
                    inputs[probe].replace(0, 1, clickedOperator.getText().toString());
                    display.setText(inputs[probe]);
                }
            }
        };
        add.setOnClickListener(operatorListener);
        sub.setOnClickListener(operatorListener);
        mul.setOnClickListener(operatorListener);
        div.setOnClickListener(operatorListener);
        per.setOnClickListener(operatorListener);

        View.OnClickListener plusMinusListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((probe == 0 || probe == 2) && inputs[probe].length() != 0) {
                    if (inputs[probe].charAt(0) == '-') {
                        inputs[probe].replace(0, 1, "");
                    }
                    else {
                        inputs[probe].insert(0, '-');
                    }
                    display.setText(inputs[probe]);
                }
            }
        };
        pm.setOnClickListener(plusMinusListener);

        View.OnClickListener decimalPointListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((probe == 0 || probe == 2) && inputs[probe].length() != 0) {
                    if (!inputs[probe].toString().contains(".")) {
                        inputs[probe].append('.');
                        display.setText(inputs[probe]);
                    }
                }
            }
        };
        dp.setOnClickListener(decimalPointListener);

        View.OnClickListener equalListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputs[0].length() != 0 && inputs[1].length() != 0 && inputs[2].length() != 0) {
                    Double num1 = Double.parseDouble(inputs[0].toString());
                    Double num2 = Double.parseDouble(inputs[2].toString());
                    double result;
                    switch (inputs[1].toString()) {
                        case "+":
                            result = num1 + num2;
                            break;
                        case "-":
                            result = num1 - num2;
                            break;
                        case "x":
                            result = num1 * num2;
                            break;
                        case "/":
                            result = num1 / num2;
                            break;
                        case "%":
                            result = (num1 / num2) * 100;
                            break;
                        default:
                            result = 0.0;
                    }
                    display.setText(String.format("%s", result));
                    for (StringBuilder input : inputs) {
                        input.delete(0, input.length());
                    }
                    probe = 0;
                }
            }
        };
        eql.setOnClickListener(equalListener);

        View.OnClickListener clearListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputs[probe].delete(0, inputs[probe].length());
                display.setText("");
            }
        };
        c.setOnClickListener(clearListener);

        View.OnClickListener clearAllListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (StringBuilder input : inputs) {
                    input.delete(0, input.length());
                }
                probe = 0;
                display.setText("");
            }
        };
        ce.setOnClickListener(clearAllListener);
    }
}