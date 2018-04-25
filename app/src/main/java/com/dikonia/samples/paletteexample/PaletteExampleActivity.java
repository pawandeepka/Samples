package com.dikonia.samples.paletteexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
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
    Toolbar myToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paletteexample);

        main_Activity_mLL = findViewById(R.id.main_Activity_mLL);
        dummy_mTextView = findViewById(R.id.dummy_mTextView);
        myToolBar = findViewById(R.id.myToolBar);
        setSupportActionBar(myToolBar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Bitmap myBitmap = decodeResource(getResources(),R.drawable.sunset);

        if (myBitmap != null && !myBitmap.isRecycled()) {
            Palette palette = Palette.from(myBitmap).generate();

            final Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
            final Palette.Swatch vibrantLightSwatch = palette.getLightVibrantSwatch();
            final Palette.Swatch vibrantDarkSwatch = palette.getDarkVibrantSwatch();
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

//                            main_Activity_mLL.setBackgroundColor(textSwatch.getRgb());
                            myToolBar.setBackgroundColor(vibrantSwatch.getRgb());
                            dummy_mTextView.setTextColor(mutedLightSwatch.getRgb());
                            dummy_mTextView.setBackgroundColor(mutedLightSwatch.getBodyTextColor());

                            myToolBar.setTitleTextColor(vibrantDarkSwatch.getRgb());
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                                getWindow().setStatusBarColor(vibrantDarkSwatch.getRgb());
                            }
                        }
                    });
        }

        RecyclerView colortest_mRecyclerView = (RecyclerView) findViewById(R.id.colortest_mRecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(PaletteExampleActivity.this);
        colortest_mRecyclerView.setLayoutManager(llm);
        Log.e("Color Array", "~~~~~~~~~~~~> " + colorList);
        colortest_mRecyclerView.setAdapter(new ColorAdapter(colorList));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
