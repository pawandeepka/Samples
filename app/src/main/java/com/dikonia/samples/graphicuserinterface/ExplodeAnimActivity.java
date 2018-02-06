package com.dikonia.samples.graphicuserinterface;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dikonia.samples.R;
import com.dikonia.samples.interfaceeg.InterfaceExampleActivity;

import java.util.ArrayList;
@SuppressLint("NewApi")
public class ExplodeAnimActivity extends AppCompatActivity {

    ArrayList<String> numList = new ArrayList<String>();
    RecyclerView explodelist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explodeanim);

        for (int i = 1; i <= 50; i++) {
            numList.add("item " + i);
        }

        explodelist = findViewById(R.id.explodeRecyclerView);
        LinearLayoutManager linearLayoutManager = new GridLayoutManager(this, 3);
        explodelist.setLayoutManager(linearLayoutManager);
        explodelist.setAdapter(new ExplodeAdapter());

        explodelist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

            }
        });
    }

    class ExplodeAdapter extends RecyclerView.Adapter<ExplodeAdapter.MenuHolder> {

        @Override
        public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_explode, parent, false);
            return new MenuHolder(view);
        }

        @Override
        public void onBindViewHolder(MenuHolder holder, final int position) {
            holder.tvItem.setText(numList.get(position));
            holder.tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String value = numList.get(position);
                    final Rect viewRect = new Rect();
                    v.getGlobalVisibleRect(viewRect);

                    // create Explode transition with epicenter
                    Explode explode = new Explode();
                    explode.setEpicenterCallback(new Transition.EpicenterCallback() {
                        @Override
                        public Rect onGetEpicenter(Transition transition) {
                            return viewRect;
                        }
                    });
                    explode.setDuration(500);
                    TransitionManager.beginDelayedTransition(explodelist, explode);

                    // remove all views from Recycler View
                    explodelist.setAdapter(null);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(ExplodeAnimActivity.this, InterfaceExampleActivity.class).putExtra("position",value));
                        }
                    },1000);
                }
            });

        }

        @Override
        public int getItemCount() {
            return numList.size();
        }

        public class MenuHolder extends RecyclerView.ViewHolder {

            TextView tvItem;

            public MenuHolder(View itemView) {
                super(itemView);
                tvItem = itemView.findViewById(R.id.tvItem);
            }
        }
    }


}
