package com.example.mat07.swim_lab1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String SAVED_INSTANCE_MASS_STRING = "mass instance";
    public static final String SAVED_INSTANCE_HEIGHT_STRING = "height instance";
    public static final String SAVED_INSTANCE_METRIC_BOOL = "uk metric bool";
    public static final String MASS_PREFS_TEXT = "mass";
    public static final String HEIGHT_PREFS_TEXT = "height";
    public static final String METRIC_PREFS_BOOL_TEXT = "uk metrics";
    public static final String BMI_INTENT_EXTRA = "bmi_value_intent_package";

    private EditText etxtMass;
    private EditText etxtHeight;
    private TextView massText;
    private TextView heightText;
    private Button btnBMI;
    private Switch ukMetricSwitch;
    private BMI bmi;
    private boolean isUKmetric;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtMass = findViewById(R.id.etxtMass);
        etxtHeight = findViewById(R.id.etxtHeight);
        massText = findViewById(R.id.mass_txt);
        heightText = findViewById(R.id.height_txt);

        btnBMI = findViewById(R.id.btnBMI);
        ukMetricSwitch = findViewById(R.id.uk_metric_switch);

        btnBMI.setOnClickListener(onClickBMIlistener);
        sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);

        ukMetricSwitch = findViewById(R.id.uk_metric_switch);

        ukMetricSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isUKmetric = isChecked;
                updateBMImetric();
            }
        });

        if(savedInstanceState!=null) {
            ukMetricSwitch.setChecked(savedInstanceState.getBoolean(SAVED_INSTANCE_METRIC_BOOL));
            updateBMImetric();
            etxtMass.setText(savedInstanceState.getString(SAVED_INSTANCE_MASS_STRING));
            etxtHeight.setText(savedInstanceState.getString(SAVED_INSTANCE_HEIGHT_STRING));
        } else{
            loadStateFromSharedPreferences();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about_me_item:
                Intent intent = new Intent(this, OMnie.class);
                startActivity(intent);
                return true;
            case R.id.save_bmi_item:
                saveStateToSharedPreferences();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString(SAVED_INSTANCE_MASS_STRING, etxtMass.getText().toString());
        outState.putString(SAVED_INSTANCE_HEIGHT_STRING, etxtHeight.getText().toString());
        outState.putBoolean(SAVED_INSTANCE_METRIC_BOOL, ukMetricSwitch.isChecked());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private void updateBMImetric(){
        if(isUKmetric) {
            if(bmi==null || !(bmi instanceof BMICale)) {
                etxtMass.setText("");
                etxtHeight.setText("");
            }
            bmi = new BMICale();
            massText.setText(getString(R.string.mass_text_UK));
            heightText.setText(getString(R.string.height_text_UK));
        } else {
            if(bmi==null || !(bmi instanceof BMISI)) {
                etxtMass.setText("");
                etxtHeight.setText("");
            }
            bmi = new BMISI();
            massText.setText(getString(R.string.mass_text));
            heightText.setText(getString(R.string.height_text));
        }
    }

    private void saveStateToSharedPreferences(){
        if(sharedPreferences!=null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(MASS_PREFS_TEXT, etxtMass.getText().toString());
            editor.putString(HEIGHT_PREFS_TEXT, etxtHeight.getText().toString());
            editor.putBoolean(METRIC_PREFS_BOOL_TEXT, bmi instanceof BMICale);
            editor.apply();
            Toast toast = Toast.makeText(getApplication().getApplicationContext(), R.string.save_toast_text, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM,0, 0);
            toast.show();
        }
    }

    private void loadStateFromSharedPreferences(){
        if(sharedPreferences!=null) {
            isUKmetric = sharedPreferences.getBoolean(METRIC_PREFS_BOOL_TEXT, false);
            updateBMImetric();
            ukMetricSwitch.setChecked(isUKmetric);
            etxtMass.setText(sharedPreferences.getString(MASS_PREFS_TEXT, ""));
            etxtHeight.setText(sharedPreferences.getString(HEIGHT_PREFS_TEXT, ""));
        }
    }

    private View.OnClickListener onClickBMIlistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String massTxt = etxtMass.getText().toString();
            String heightTxt = etxtHeight.getText().toString();
            if(!massTxt.equals("") && !heightTxt.equals("")) {
                bmi.setMass(Double.parseDouble(massTxt));

                if(bmi instanceof BMISI)
                    bmi.setHeight(Double.parseDouble(heightTxt) / 100f);
                else
                    bmi.setHeight(Double.parseDouble(heightTxt));

                try {
                    Intent intent = new Intent(getApplicationContext(), PokazBMI.class);
                    intent.putExtra(MainActivity.BMI_INTENT_EXTRA, bmi.getBMI());
                    startActivity(intent);
                } catch(IllegalArgumentException e) {
                    Toast toast = Toast.makeText(getApplication().getApplicationContext(), R.string.wrong_values_toast, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 0);
                    toast.show();
                }
            }
            else{
                Toast toast = Toast.makeText(getApplication().getApplicationContext(), R.string.empty_fields_toast, Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM,0, 0);
                toast.show();
            }
        }
    };


}
