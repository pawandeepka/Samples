package com.dikonia.samples.paletteexample;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dikonia.samples.R;

import java.util.ArrayList;

import static android.graphics.BitmapFactory.decodeResource;

public class PaletteExampleActivity extends AppCompatActivity {

    ArrayList<Palette.Swatch> colorList = new ArrayList<Palette.Swatch>();
    LinearLayout main_Activity_mLL;
    TextView dummy_mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paletteexample);

        main_Activity_mLL = findViewById(R.id.main_Activity_mLL);
        dummy_mTextView = findViewById(R.id.dummy_mTextView);
        Bitmap myBitmap = decodeResource(getResources(), R.drawable.teddy);
        if (myBitmap != null && !myBitmap.isRecycled()) {
            Palette palette = Palette.from(myBitmap).generate();

            Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
            Palette.Swatch vibrantLightSwatch = palette.getLightVibrantSwatch();
            Palette.Swatch vibrantDarkSwatch = palette.getDarkVibrantSwatch();
            Palette.Swatch mutedSwatch = palette.getMutedSwatch();
            final Palette.Swatch mutedLightSwatch = palette.getLightMutedSwatch();
            final Palette.Swatch mutedDarkSwatch = palette.getDarkMutedSwatch();

            colorList.add(vibrantSwatch);
            colorList.add(vibrantLightSwatch);
            colorList.add(vibrantDarkSwatch);
            colorList.add(mutedSwatch);
            colorList.add(mutedLightSwatch);
            colorList.add(mutedDarkSwatch);

            Palette.from(myBitmap)
                    .generate(new Palette.PaletteAsyncListener() {
                        @Override
                        public void onGenerated(Palette palette) {
                            Palette.Swatch textSwatch = palette.getVibrantSwatch();
                            if (textSwatch == null) {
                                Toast.makeText(PaletteExampleActivity.this, "Null swatch :(", Toast.LENGTH_SHORT).show();
                                return;
                            }

                            main_Activity_mLL.setBackgroundColor(textSwatch.getRgb());
                            dummy_mTextView.setTextColor(mutedLightSwatch.getRgb());
                            dummy_mTextView.setBackgroundColor(mutedLightSwatch.getBodyTextColor());
                        }
                    });
        }

        RecyclerView colortest_mRecyclerView = (RecyclerView) findViewById(R.id.colortest_mRecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(PaletteExampleActivity.this);
        colortest_mRecyclerView.setLayoutManager(llm);
        Log.e("Color Array", "~~~~~~~~~~~~> " + colorList);
        colortest_mRecyclerView.setAdapter(new ColorAdapter(colorList));
    }
}
