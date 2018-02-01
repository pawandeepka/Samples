package com.dikonia.samples.interfaceeg;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.dikonia.samples.R;

/**
 * Created by emp on 1/30/2018.
 */

public class InterfaceExampleActivity extends AppCompatActivity implements CheckPrint{

    boolean click = true;
    CheckPrint print = null;
    TextView textViewHEY;
    Button buttonClickMe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaceexample);

        print = new InterfaceExampleActivity();
        print.printMeLog("OnCreate");

        buttonClickMe = findViewById(R.id.buttonClickMe);
        textViewHEY = findViewById(R.id.textViewHEY);
    }

    @Override
    public void printMeLog(String value) {
        Log.e("~~~~~~~~~~~~~>> " , value);
        System.out.println("~~~~~~~~~~~~~>> " + value);
    }


    public void funClickMe(View view) {

        if (click) {
            startAnim();
            click = false;
        }
        else {
            stopAnim();
            click = true;
        }

    }

    public void startAnim() {
        final ScaleAnimation animation = new ScaleAnimation(0, 70, 0, 70, Animation.RELATIVE_TO_SELF,
                (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        animation.setFillAfter(true);
        animation.setDuration(1000);

        buttonClickMe.postDelayed(new Runnable() {
            @Override
            public void run() {
                buttonClickMe.setVisibility(View.VISIBLE);
                textViewHEY.startAnimation(animation);
                textViewHEY.setVisibility(View.VISIBLE);
            }
        }, 10);


    }

    public void stopAnim() {
        final ScaleAnimation animation = new ScaleAnimation(70, 1, 70, 1, Animation.RELATIVE_TO_SELF,
                (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        animation.setFillAfter(false);
        animation.setDuration(1000);

        textViewHEY.postDelayed(new Runnable() {
            @Override
            public void run() {
                textViewHEY.startAnimation(animation);

            }
        }, 10);
    }
}
