package com.example.mat07.swim_lab1;

/**
 * Created by mat07 on 22.03.2018.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Locale;


public class PokazBMI extends AppCompatActivity {

    public static final double fstRange = 16;
    public static final double sndRange = 17;
    public static final double thirdRange = 18.5;
    public static final double fourthRange = 25;
    public static final double fifthRange = 30;
    public static final double sixthRange = 35;
    public static final double seventhRange = 40;
    public static final String bmiFormatString = "%.2f";

    double bmi_val;
    TextView txtBMI;
    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokazbmi);
        if(getSupportActionBar()!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        bmi_val = getIntent().getDoubleExtra(MainActivity.BMI_INTENT_EXTRA, 0);
        txtBMI = findViewById(R.id.bmi_text);
        txtInfo = findViewById(R.id.info_text);
        txtBMI.setText(String.format(Locale.getDefault(), bmiFormatString, bmi_val));
        setBMItext(bmi_val, txtInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setBMItext(double value, TextView text) {
        if (value < fstRange) {
            text.setTextColor(getResources().getColor(R.color.fstRange));
            text.setText(R.string.fstBMIrange);
        } else if (value < sndRange) {
            text.setTextColor(getResources().getColor(R.color.sndRange));
            text.setText(R.string.sndBMIrange);
        } else if (value < thirdRange) {
            text.setTextColor(getResources().getColor(R.color.thirdRange));
            text.setText(R.string.thirdBMIrange);
        } else if (value < fourthRange) {
            text.setTextColor(getResources().getColor(R.color.fourthRange));
            text.setText(R.string.fourthBMIrange);
        } else if (value < fifthRange) {
            text.setTextColor(getResources().getColor(R.color.fifthRange));
            text.setText(R.string.fifthBMIrange);
        } else if (value < sixthRange) {
            text.setTextColor(getResources().getColor(R.color.sixthRange));
            text.setText(R.string.sixthBMIrange);
        } else if (value < seventhRange) {
            text.setTextColor(getResources().getColor(R.color.seventhRange));
            text.setText(R.string.seventhBMIrange);
        } else {
            text.setTextColor(getResources().getColor(R.color.eighthRange));
            text.setText(R.string.eighthBMIrange);
        }
    }

}
