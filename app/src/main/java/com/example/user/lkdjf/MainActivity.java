package com.example.user.lkdjf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.bitstamp) {
            Intent intentfr = new Intent(this, Activity_Bitstamp.class);
            startActivity(intentfr);
        }
        if (view.getId() == R.id.bitfinex) {
            Intent intentB = new Intent(this, Activity_Bitfinex.class);
            startActivity(intentB);
        }
        if (view.getId() == R.id.cex) {
            Intent intentC = new Intent(this, Activity_CEX.class);
            startActivity(intentC);
        }
    }
}




