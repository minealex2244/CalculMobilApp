package com.minealex2244.univtest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int pin = 0;

    public void pin_gresit() {
        Toast.makeText(getBaseContext(), "PIN gresit", Toast.LENGTH_SHORT).show();
        pin = 0;
    }

    public void pinFunc( View v ) {
        switch (v.getId()) {
            case (R.id.button1):
                pin = pin * 10 + 1;
                if (pin / 10000 != 0) pin_gresit();
                break;
            case (R.id.button9):
                pin = pin * 10 + 9;
                if (pin / 10000 != 0) pin_gresit();
                if (pin == 1999) {
                    Intent i = new Intent(getApplicationContext(), MainActivity2.class);
                    startActivity(i);
                }
                break;
            default:
                pin = pin * 10 + 2;
                if (pin / 10000 != 0 || (pin % 10000 >= 0 && pin % 1000 != pin % 10000 && pin / 10000 == 0))
                    pin_gresit();
                break;
        }
        Log.d("MainActivity", "DEBUG: " + pin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}