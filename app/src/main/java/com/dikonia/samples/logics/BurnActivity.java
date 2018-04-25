package com.dikonia.samples.logics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.dikonia.samples.R;

public class BurnActivity extends AppCompatActivity {
    private TextView mValueTextView;

    /*
    *           1
    *       1   2   1
    *   1   2   3   2   1
    *   1   2   3   2   1
    *       1   2   1
    *           1           */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burn);
        mValueTextView = findViewById(R.id.textView2);

        // number of spaces
        int k = 2*3 - 2;

        for (int i = 1; i <= 3; i++) {

            // inner loop to handle number spaces
            // values changing acc. to requirement
            for (int j=0; j<k; j++)
            {
                // printing spaces
                System.out.print("\t");
            }

            // decrementing k after each loop
            k = k - 1;
            for (int j = 1; j <= i; j++) {
                System.out.print(j);
                System.out.print("\t");
            }

            for (int j = i-1; j > 0; j--) {
                System.out.print(j);
                System.out.print("\t");
            }

            System.out.println();
        }

        int kk = 2*2 - 2;
        for (int i = 3; i >= 1; i--) {

            // inner loop to handle number spaces
            // values changing acc. to requirement
            for (int j=kk; j>0; j--)
            {
                // printing spaces
                System.out.print("\t");
            }

            // incrementing k after each loop
            kk = kk + 1;

            for (int j = 1; j <= i; j++) {
                System.out.print(j);
                System.out.print("\t");
            }

            for (int j = i-1; j > 0; j--) {
                System.out.print(j);
                System.out.print("\t");
            }

            System.out.println();

        }
    }

    /*
                          output
     				        1
              			1	2	1
             		1	2	3	2	1
              		1	2	3	2	1
              			1	2	1
              				1
*/
}
