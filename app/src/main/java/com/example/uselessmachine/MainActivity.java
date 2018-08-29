package com.example.uselessmachine;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.uselessmachine.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonSelfDestruct;
    private Switch switchUseless;

    public static final String TAG = MainActivity.class.getSimpleName(); //will give the appropriate name
    //TAG variable allows for easy login
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
    }

    private void setListeners() {
        //TODO self destruct button
        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean checked) {
                if(checked){
                    StartSwitchOffTimer();
                    Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void StartSwitchOffTimer() {
        CountDownTimer countDownTimer = new CountDownTimer(5000, 100) {
            @Override
            public void onTick(long millisUntilFinished) {
                if(!switchUseless.isChecked()){
                    //Log.d(TAG, "onTick: canceling "); // located in the log Cat
                    cancel();
                }

            }

            @Override
            public void onFinish() {
                switchUseless.setChecked(false);
                Log.d(TAG, "onFinish: switch set to false");
            }

        }.start();
    }

    private void wireWidgets() {
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
    }
}
