package com.example.uselessmachine;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.uselessmachine.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonSelfDestruct;
    private Switch switchUseless;
    private ConstraintLayout layout;

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
        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelfDestructSequence();
            }
        });

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

    private void startSelfDestructSequence() {
        //Disable the button
        buttonSelfDestruct.setEnabled(false); //once you click on the button you cant go back
        //Ten second count down timer that updates display every 10 sec
        new CountDownTimer(10000, 1000) {
            @Override
            //Want the button to show countdown
            public void onTick(long millisUntilFinished) {
                millisUntilFinished = millisUntilFinished/1000;
                buttonSelfDestruct.setText("Self Destruct in..." + millisUntilFinished) ;
                //when the count down gets to 3 seconds
                //the background will be red on sec 3
                //then white on sec 2
                //then red again on 1 and 0
                //NEED TO FIX MATH
                if (millisUntilFinished/2 != 0 && millisUntilFinished <= 3){
                    layout.setBackgroundColor(Color.rgb(255,0,0) ) ;
                }
                else{
                    layout.setBackgroundColor(Color.rgb(255, 255, 255));
                }
            }

            @Override
            public void onFinish() {
                finish();
            }
        };
        //At the end were going to close activity
        //call the finish method
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
        layout = findViewById(R.id.constraint_layout);
    }
}
