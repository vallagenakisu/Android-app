package com.example.mainprojectpersonallifetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class splashactivity extends AppCompatActivity {
    private TextView textView;
    private LottieAnimationView lottie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashactivity);

        textView = findViewById(R.id.title);
        lottie = findViewById(R.id.splashview);
       // textView.animate().translationY(-1600).setDuration(1000).setStartDelay(0);
        lottie.animate().setDuration(1000).setStartDelay(2000);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(splashactivity.this, MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }
}
