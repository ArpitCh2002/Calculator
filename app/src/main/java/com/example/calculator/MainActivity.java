package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView inputText, outputText;
    private String input, output, newOutput;

    private Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonAdd, buttonSub, buttonMultiply, buttonDivision,
            buttonPoint, buttonPower, buttonClear, buttonEqual, buttonPercent;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.input_text);
        outputText = findViewById(R.id.output_text);

        button0 = findViewById(R.id.btn_zero);
        button1 = findViewById(R.id.btn_one);
        button2 = findViewById(R.id.btn_two);
        button3 = findViewById(R.id.btn_three);
        button4 = findViewById(R.id.btn_four);
        button5 = findViewById(R.id.btn_five);
        button6 = findViewById(R.id.btn_six);
        button7 = findViewById(R.id.btn_seven);
        button8 = findViewById(R.id.btn_eight);
        button9 = findViewById(R.id.btn_nine);
        buttonAdd = findViewById(R.id.add_btn);
        buttonSub = findViewById(R.id.minus_btn);
        buttonMultiply = findViewById(R.id.multiply_btn);
        buttonDivision = findViewById(R.id.division_btn);
        buttonPoint = findViewById(R.id.point_btn);
        buttonPower = findViewById(R.id.power_btn);
        buttonClear = findViewById(R.id.clear_btn);
        buttonClear = findViewById(R.id.clear_btn);
        buttonEqual = findViewById(R.id.equal_btn);
        buttonPercent = findViewById(R.id.percent_btn);
    }

    public void onButtonClicked(View view) {
        Button button = (Button) view;
        String data = button.getText().toString();

        switch (data) {
            case "C":
                input = null;
                output = null;
                newOutput = null;
                outputText.setText("");
                break;

            case "^":
                solve();
                input += "^";
                break;

            case "*":
                solve();
                input += "*";
                break;

            case "=":
                solve();
                break;

            case "%":
                input += "%";
                double d = Double.parseDouble(inputText.getText().toString()) / 100;
                outputText.setText(String.valueOf(d));
                break;

            default:
                if (input == null) {
                    input = "";
                }

                if (data.equals("+") || data.equals("-") || data.equals("/")) {
                    solve();
                }
                input += data;
        }
        inputText.setText(input);
    }

    public void solve() {
        if (input.split("\\+").length == 2) {
            String numbers[] = input.split("\\+");
            try {
                double d = Double.parseDouble(numbers[0]) + Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d + "";
            } catch (Exception e) {
                outputText.setError(e.getMessage().toString());
            }
        }

        if (input.split("\\*").length == 2) {
            String numbers[] = input.split("\\*");
            try {
                double d = Double.parseDouble(numbers[0]) * Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d + "";
            } catch (Exception e) {
                outputText.setError(e.getMessage().toString());
            }
        }

        if (input.split("\\/").length == 2) {
            String numbers[] = input.split("\\/");
            try {
                double d = Double.parseDouble(numbers[0]) / Double.parseDouble(numbers[1]);
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d + "";
            } catch (Exception e) {
                outputText.setError(e.getMessage().toString());
            }
        }

        if (input.split("\\^").length == 2) {
            String numbers[] = input.split("\\^");
            try {
                double d = Math.pow(Double.parseDouble(numbers[0]) , Double.parseDouble(numbers[1]));
                output = Double.toString(d);
                newOutput = cutDecimal(output);
                outputText.setText(newOutput);
                input = d + "";
            } catch (Exception e) {
                outputText.setError(e.getMessage().toString());
            }
        }

        if (input.split("\\-").length == 2) {
            String numbers[] = input.split("\\-");
            try {
                if (Double.parseDouble(numbers[0]) < Double.parseDouble(numbers[1])) {
                    double d = Double.parseDouble(numbers[1]) - Double.parseDouble(numbers[0]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText("-" + newOutput);
                    input = d + "";
                }
                else {
                    double d = Double.parseDouble(numbers[0]) - Double.parseDouble(numbers[1]);
                    output = Double.toString(d);
                    newOutput = cutDecimal(output);
                    outputText.setText(newOutput);
                    input = d + "";
                }
            } catch (Exception e) {
                outputText.setError(e.getMessage().toString());
            }
        }
    }

    private String cutDecimal(String number) {
        String n[] = number.split("\\.");
        if (n.length > 1) {
            if (n[1].equals("0")) {
                number = n[0];
            }
        }
        return number;
    }
}
