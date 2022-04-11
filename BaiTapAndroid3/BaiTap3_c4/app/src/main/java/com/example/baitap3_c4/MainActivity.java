package com.example.baitap3_c4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText txtRoot;
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnPlus, btnMinus, btnTimes, btnDivi, btnClear, btnResult;
    private String operator, valueA, valueB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }

    private void addControls() {
        txtRoot = findViewById(R.id.txtRoot);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btn0 = findViewById(R.id.btn0);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnTimes = findViewById(R.id.btnTimes);
        btnDivi = findViewById(R.id.btnDivi);
        btnClear = findViewById(R.id.btnClear);
        btnResult = findViewById(R.id.btnResult);
    }

    private void addEvents() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnMinus.setOnClickListener(this);
        btnTimes.setOnClickListener(this);
        btnDivi.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        btnResult.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Button btn = (Button) view;
        switch (btn.getId()) {
            case R.id.btn1:
                setValue("1");
                break;
            case R.id.btn2:
                setValue("2");
                break;
            case R.id.btn3:
                setValue("3");
                break;
            case R.id.btn4:
                setValue("4");
                break;
            case R.id.btn5:
                setValue("5");
                break;
            case R.id.btn6:
                setValue("6");
                break;
            case R.id.btn7:
                setValue("7");
                break;
            case R.id.btn8:
                setValue("8");
                break;
            case R.id.btn9:
                setValue("9");
                break;
            case R.id.btn0:
                setValue("0");
                break;
            case R.id.btnPlus:
                operator = "plus";
                valueA = txtRoot.getText().toString();
                setValue("+");
                break;
            case R.id.btnMinus:
                operator = "minus";
                valueA = txtRoot.getText().toString();
                setValue("-");
                break;
            case R.id.btnTimes:
                operator = "times";
                valueA = txtRoot.getText().toString();
                setValue("×");
                break;
            case R.id.btnDivi:
                operator = "divide";
                valueA = txtRoot.getText().toString();
                setValue("÷");
                break;
            case R.id.btnClear:
                txtRoot.setText("0");
                break;
            case R.id.btnResult:
                doProcess();
                break;
        }
    }

    private void setValue(String value) {
        String last = txtRoot.getText().toString();
        if (!last.equals("0")) {
            last += value;
            value = last;
        }
        txtRoot.setText(value);
    }

    private void doProcess() {
        String value = txtRoot.getText().toString();
        valueB = value.substring(valueA.length() + 1);

        double a = Double.parseDouble(valueA), b = Double.parseDouble(valueB);
        String result = "";

        switch (operator) {
            case "plus":
                int plus = (int) (a + b);
                result = plus + "";
                break;
            case "minus":
                int minus = (int) (a - b);
                result = minus + "";
                break;
            case "times":
                int times = (int) (a * b);
                result = times + "";
                break;
            case "divide":
                double divide = a / b;
                result = divide + "";
                break;
        }

        txtRoot.setText(result);
    }

}