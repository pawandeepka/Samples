package com.dikonia.samples;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.dikonia.samples.interfaceeg.InterfaceExampleActivity;

public class SplashActivity extends AppCompatActivity {

    TextView textViewWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        textViewWelcome = findViewById(R.id.textViewWelcome);

        stopAnim();
    }

    public void stopAnim() {
        final ScaleAnimation animation = new ScaleAnimation(70, 1, 70, 1, Animation.RELATIVE_TO_SELF,
                (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        animation.setFillAfter(false);
        animation.setDuration(1500);

        textViewWelcome.postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewWelcome.startAnimation(animation);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(SplashActivity.this, InterfaceExampleActivity.class));
                        finish();
                    }
                },1500);

            }
        }, 100);
    }
}
