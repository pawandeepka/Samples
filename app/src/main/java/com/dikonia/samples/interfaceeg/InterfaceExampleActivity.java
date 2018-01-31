package com.dikonia.samples.interfaceeg;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dikonia.samples.R;

/**
 * Created by emp on 1/30/2018.
 */

public class InterfaceExampleActivity extends AppCompatActivity implements CheckPrint{


    CheckPrint print = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interfaceexample);

        print = new InterfaceExampleActivity();
        print.printMeLog("OnCreate");
    }

    @Override
    public void printMeLog(String value) {
        Log.e("~~~~~~~~~~~~~>> " , value);
        System.out.println("~~~~~~~~~~~~~>> " + value);
    }
}
