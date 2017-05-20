package com.vlg.alex.weatherrestopen.Holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.vlg.alex.weatherrestopen.Models.List;
import com.vlg.alex.weatherrestopen.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alex on 20.05.2017.
 */

public class WeatherHolder extends RecyclerView.ViewHolder {

    Context context;

    @BindView(R.id.iv_weather_icon)
    public ImageView weatherIcon;
    @BindView(R.id.tv_temp_morning)
    public TextView monTemp;
    @BindView(R.id.tv_temp_day)
    public TextView dayTemp;
    @BindView(R.id.tv_temp_evening)
    public TextView eveTemp;
    @BindView(R.id.tv_temp_night)
    public TextView ngTemp;

    public WeatherHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);

    }

    public void setHolder( List item, final Context context, final int listType){
        this.context = context;
        Glide.with(context).load("http://openweathermap.org/img/w/"+item.getWeather().get(0).getIcon()+".png").asBitmap().into(weatherIcon);
        monTemp.setText(String.valueOf(item.getTemp().getMorn()));
        dayTemp.setText(String.valueOf(item.getTemp().getDay()));
        eveTemp.setText(String.valueOf(item.getTemp().getEve()));
        ngTemp.setText(String.valueOf(item.getTemp().getNight()));

    }




}
