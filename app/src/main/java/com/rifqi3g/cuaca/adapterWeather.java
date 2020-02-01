package com.rifqi3g.cuaca;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class adapterWeather extends RecyclerView.Adapter<adapterWeather.CustomViewHolder> {

    private ArrayList<locationObj> locationObjs;
    private Context context;

    public adapterWeather(Context context, ArrayList<locationObj> datalist){
        this.context = context;
        this.locationObjs = datalist;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;

        TextView taggal,cuaca;
        ImageView imgCuaca;
        LinearLayout linearLayout;

        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            taggal = mView.findViewById(R.id.nextDate);
            cuaca = mView.findViewById(R.id.nextWeather);
            imgCuaca = mView.findViewById(R.id.imgggg);
            linearLayout = mView.findViewById(R.id.linearWeather);

        }
    }
    @Override
    public adapterWeather.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lyt_weather,parent, false);
        return new adapterWeather.CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(adapterWeather.CustomViewHolder holder, final int position){
        holder.taggal.setText(locationObjs.get(position).getApplicableDate());
        holder.cuaca.setText((locationObjs.get(position).getWeatherStateName()));
        String ccccc = locationObjs.get(position).getWeatherStateName();
        if (ccccc.isEmpty()) {
            holder.imgCuaca.setImageResource(R.drawable.logoo);
        }else if (ccccc.equals("Snow")){
            holder.imgCuaca.setImageResource(R.drawable.snow);
        }else if (ccccc.equals("Hail")){
            holder.imgCuaca.setImageResource(R.drawable.hail);
        }else if (ccccc.equals("Sleet")){
            holder.imgCuaca.setImageResource(R.drawable.sleet);
        }else if (ccccc.equals("Thunderstorm")){
            holder.imgCuaca.setImageResource(R.drawable.tunderstrom);
        }else if (ccccc.equals("Heavy Rain")){
            holder.imgCuaca.setImageResource(R.drawable.heavy_rain);
        }else if (ccccc.equals("Light Rain")){
            holder.imgCuaca.setImageResource(R.drawable.light_rain);
        }else if (ccccc.equals("Showers")){
            holder.imgCuaca.setImageResource(R.drawable.shower);
        }else if (ccccc.equals("Heavy Cloud")){
            holder.imgCuaca.setImageResource(R.drawable.heave_cloud);
        }else if (ccccc.equals("Light Cloud")){
            holder.imgCuaca.setImageResource(R.drawable.light_cloud);
        }else if (ccccc.equals("Clear")){
            holder.imgCuaca.setImageResource(R.drawable.clear);
        }holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , detailNextDay.class);
                intent.putExtra("xxxxxx", new Gson().toJson(locationObjs.get(position)));
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount(){
        return locationObjs.size();
    }}