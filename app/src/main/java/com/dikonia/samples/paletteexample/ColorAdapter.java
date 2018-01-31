package com.dikonia.samples.paletteexample;

import android.support.v7.graphics.Palette;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dikonia.samples.R;

import java.util.ArrayList;

/**
 * Created by emp on 1/30/2018.
 */

class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MenuHolder> {

    ArrayList<Palette.Swatch> colorList;

    public ColorAdapter(ArrayList<Palette.Swatch> colorList) {
        this.colorList=colorList;
    }

    @Override
    public MenuHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_listcolor,parent,false);
        return new MenuHolder(view);
    }

    @Override
    public void onBindViewHolder(MenuHolder holder, int position) {
        if (colorList.get(position) != null) {
            holder.color_mTextView.setText(String.valueOf(colorList.get(position).getRgb()));

            holder.color_mTextView.setBackgroundColor(colorList.get(position).getRgb());
        }
    }

    @Override
    public int getItemCount() {
        return colorList.size();
    }

    public class MenuHolder extends RecyclerView.ViewHolder {
        TextView color_mTextView;
        public MenuHolder(View itemView) {
            super(itemView);

            color_mTextView = (TextView)itemView.findViewById(R.id.color_mTextView);
        }
    }
}
