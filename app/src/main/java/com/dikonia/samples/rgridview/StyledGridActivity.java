package com.dikonia.samples.rgridview;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.dikonia.samples.R;
import com.dikonia.samples.rgridview.retrofit.CallAPIs;
import com.dikonia.samples.rgridview.retrofit.ItemPojo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class StyledGridActivity extends AppCompatActivity {

    RecyclerView gridRecyclerView;
    CallAPIs ccWebServiceApi;
    StaggedAdapter adapter;
    Model model;
    private int count;
    ArrayList<Model> arrayList = new ArrayList<>();

    MyApplication getEnv() {
        return (MyApplication) StyledGridActivity.this.getApplication();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_styled_grid);

        ccWebServiceApi = getEnv().getRetrofit();

        gridRecyclerView = findViewById(R.id.gridRecyclerView);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        gridRecyclerView.setLayoutManager(staggeredGridLayoutManager);

        final Handler handler = new Handler();

        Call<ArrayList<ItemPojo>> callLoginApi = ccWebServiceApi.getImages();
        callLoginApi.enqueue(new Callback<ArrayList<ItemPojo>>() {
            @Override
            public void onResponse(Call<ArrayList<ItemPojo>> call, Response<ArrayList<ItemPojo>> response) {
                if (response.isSuccessful()) {
                    final ArrayList<ItemPojo> main = response.body();

                    adapter = new StaggedAdapter();
                    gridRecyclerView.setAdapter(adapter);

                    int n = main.size();
                    for (int i = 0; i < n; i++) {

                            String url = main.get(i).getUrl();
                            final String  tString = main.get(i).getTitle();
                            count = i;

                            Glide.with(StyledGridActivity.this)
                                    .load(url)
                                    .asBitmap()
                                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(final Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                            Log.e("Size ", "width :" + resource.getWidth() + " height :" + resource.getHeight());

                                            Thread t = new Thread(new Runnable()
                                            {
                                                @Override
                                                public void run()
                                                {

                                                    Model model = new Model();
                                                    model.bitmap = resource;
                                                    model.stitle = tString;

                                                    arrayList.add(model);

                                                    StyledGridActivity.this.runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            adapter.notifyDataSetChanged();
                                                        }
                                                    });

                                                }
                                            });
                                            t.start();
                                            try {
                                                Thread.sleep(100);
                                            }
                                            catch (Exception e){}

                                        }
                                    });



                    }

                } else {
                    Toast.makeText(StyledGridActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ItemPojo>> call, Throwable t) {
                Log.wtf("FAILURE :: _ ", t + "");
            }
        });

    }



    private class StaggedAdapter extends RecyclerView.Adapter<StaggedAdapter.MenuHolder> {


        @NonNull
        @Override
        public MenuHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view, parent, false);
            return new MenuHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MenuHolder holder, int position) {
            holder.thumbImageView.setImageBitmap(arrayList.get(position).bitmap);
            holder.thumbTV.setText(arrayList.get(position).stitle);
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class MenuHolder extends RecyclerView.ViewHolder {

            ImageView thumbImageView;
            TextView thumbTV;


            public MenuHolder(View itemView) {
                super(itemView);

                thumbImageView = itemView.findViewById(R.id.thumbImageView);
                thumbTV = itemView.findViewById(R.id.thumbTV);
            }
        }
    }

    class Model {
        String stitle;
        Bitmap bitmap;
    }
}
