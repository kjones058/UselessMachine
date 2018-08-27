package com.example.uselessmachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.uselessmachine.R;

public class MainActivity extends AppCompatActivity {
    private Button buttonSelfDestruct;
    private Switch switchUseless;

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
                    Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT);
                }
            }
        });
    }

    private void wireWidgets() {
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
    }
}
