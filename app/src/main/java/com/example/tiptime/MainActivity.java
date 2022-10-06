package com.example.tiptime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tiptime.databinding.ActivityMainBinding;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());

        binding.calculateButton.setOnClickListener(view -> calculateTip());

    }

    private void calculateTip() {
        String stringInTextField = binding.costOfService.getText().toString();
        double cost = Double.parseDouble(stringInTextField);


        int selectedId = binding.tipOptions.getCheckedRadioButtonId();
        double tipPercentage;

        if (selectedId == R.id.option_twenty_percent) {
            tipPercentage = 0.20;
        } else if (selectedId == R.id.option_eighteen_percent) {
            tipPercentage = 0.18;
        } else {
            tipPercentage = 0.15;
        }

        double tip = tipPercentage * cost;
        boolean roundUp = binding.roundUpSwitch.isChecked();
        if (roundUp) {
            tip = Math.ceil(tip);
        }

        displayTip(tip);

    }

    private void displayTip(Double tip) {
        String formattedTip = NumberFormat.getCurrencyInstance().format(tip);
        binding.tipResult.setText(getString(R.string.tip_amount, formattedTip));
    }

}