package com.dikonia.samples.interfaceeg;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dikonia.samples.R;

/**
 * Created by emp on 1/30/2018.
 */

public class InterfaceExampleActivity extends AppCompatActivity implements CheckPrint {

    boolean click = true;
    CheckPrint print = null;
    TextView textViewHEY;
    Button buttonClickMe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaceexample);

        print = new InterfaceExampleActivity();

        buttonClickMe = findViewById(R.id.buttonClickMe);
        textViewHEY = findViewById(R.id.textViewHEY);

        String comes = getIntent().getStringExtra("position");
        print.printMeLog("OnCreate" + comes);

        final ViewGroup transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
        final TextView text = (TextView) transitionsContainer.findViewById(R.id.text);
        final Button button = (Button) transitionsContainer.findViewById(R.id.button);

        text.setText(comes);
        button.setOnClickListener(new View.OnClickListener() {

            boolean visible;

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionsContainer);
                visible = !visible;
                text.setVisibility(visible ? View.VISIBLE : View.GONE);
            }

        });
    }

    @Override
    public void printMeLog(String value) {
        Log.e("~~~~~~~~~~~~~>> ", value);
        System.out.println("~~~~~~~~~~~~~>> " + value);
    }

    boolean visible;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void funClickMe(View view) {

        final ViewGroup parentView = (ViewGroup) findViewById(R.id.parentView);
        TransitionManager.beginDelayedTransition(parentView);
        visible = !visible;
        textViewHEY.setVisibility(visible ? View.VISIBLE : View.GONE);

        /*if (click) {
            startAnim();
            click = false;
        }
        else {
            stopAnim();
            click = true;
        }*/

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
