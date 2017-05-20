package com.vlg.alex.weatherrestopen.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vlg.alex.weatherrestopen.Models.List;
import com.vlg.alex.weatherrestopen.R;
import com.vlg.alex.weatherrestopen.Holders.WeatherHolder;

/**
 * Created by Alex on 20.05.2017.
 */

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private java.util.List<List> items;

    private Context context;
    private int listType;

    public WeatherAdapter(java.util.List<List> items, Context context, int listType) {
        this.items = items;
        this.context = context;
        this.listType = listType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType ) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch(listType)
        {
            case 0:
                View v1 = inflater.inflate(R.layout.card_item, parent, false);
                viewHolder = new WeatherHolder(v1);
                break;
            default:
                View vDef = inflater.inflate(R.layout.card_item, parent, false);
                viewHolder = new WeatherHolder(vDef);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch(listType)
        {
            case 0:
                WeatherHolder vh1 = (WeatherHolder) holder;
                configureQuestionHolder(vh1, position);
                break;
            default:
                WeatherHolder vhDef = (WeatherHolder) holder;
                configureQuestionHolder(vhDef, position);
                break;
        }
    }

    private void configureQuestionHolder(WeatherHolder holder1, int position) {
        holder1.setHolder(items.get(position),context,listType);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}