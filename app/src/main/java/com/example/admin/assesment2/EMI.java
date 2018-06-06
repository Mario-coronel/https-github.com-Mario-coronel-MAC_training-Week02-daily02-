package com.example.admin.assesment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class EMI extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    EditText teLoan;
    EditText teInterest;
    TextView tvMonths;
    TextView tvTotal;
    TextView tvMontly;
    SeekBar sbMonths;
    double loan = 0, interest = 0, months = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emi);
        binds();
        tvMonths.setText("Months : " + (String.valueOf(sbMonths.getProgress())));
        sbMonths.setOnSeekBarChangeListener(this);
    }

    private double update(double loan, double interest, double months) {
        double result = 0;
        if (loan > 0 && interest > 0 && months > 0) {
            result = (loan * interest) * (((1 + (interest / 12)) * months) / (12 * ((1 + (interest / 12)) * months) - 1));
        }

        return result;
    }


    private void binds() {
        teLoan = findViewById(R.id.etLoan);
        teInterest = findViewById(R.id.etAnnualInt);
        tvMonths = findViewById(R.id.tvMonths);
        tvMontly = findViewById(R.id.tvMontly);
        tvTotal = findViewById(R.id.tvTotalDebt);
        sbMonths = findViewById(R.id.sbMonths);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

        months = seekBar.getProgress();
        tvMonths.setText("Months : " + String.valueOf(months));
        loan = Double.valueOf(String.valueOf(teLoan.getText()));
        interest = Double.valueOf(String.valueOf(teInterest.getText()));
        tvMontly.setText("Montly Payment : " + String.valueOf(update(loan, interest, months)));
        tvTotal.setText("Total Debt: " + String.valueOf(months * update(loan, interest, months)));

    }
}
