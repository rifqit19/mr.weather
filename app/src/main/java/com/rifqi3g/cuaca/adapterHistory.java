package com.rifqi3g.cuaca;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class adapterHistory extends RecyclerView.Adapter<adapterHistory.CustomViewHolder> {

    private ArrayList<historObj> historObjs;
    private Context context;

    public adapterHistory(Context context, ArrayList<historObj> datalist){
        this.context = context;
        this.historObjs = datalist;
    }
    class CustomViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView wett;
        ImageView imgHiss;



        CustomViewHolder(View itemView){
            super(itemView);
            mView = itemView;

            wett = mView.findViewById(R.id.weatherHistory);
            imgHiss = mView.findViewById(R.id.imgHistory);


        }
    }
    @Override
    public adapterHistory.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.lyt_history,parent, false);
        return new adapterHistory.CustomViewHolder(view);
    }
    @Override
    public void onBindViewHolder(adapterHistory.CustomViewHolder holder, final int position){
        String cccc = historObjs.get(position).getWeatherStateName();
        holder.wett.setText(historObjs.get(position).getWeatherStateName());
        if (cccc.isEmpty()) {
            holder.imgHiss.setImageResource(R.drawable.logoo);
        }else if (cccc.equals("Snow")){
            holder.imgHiss.setImageResource(R.drawable.snow);
        }else if (cccc.equals("Hail")){
            holder.imgHiss.setImageResource(R.drawable.hail);
        }else if (cccc.equals("Sleet")){
            holder.imgHiss.setImageResource(R.drawable.sleet);
        }else if (cccc.equals("Thunderstorm")){
            holder.imgHiss.setImageResource(R.drawable.tunderstrom);
        }else if (cccc.equals("Heavy Rain")){
            holder.imgHiss.setImageResource(R.drawable.heavy_rain);
        }else if (cccc.equals("Light Rain")){
            holder.imgHiss.setImageResource(R.drawable.light_rain);
        }else if (cccc.equals("Showers")){
            holder.imgHiss.setImageResource(R.drawable.shower);
        }else if (cccc.equals("Heavy Cloud")){
            holder.imgHiss.setImageResource(R.drawable.heave_cloud);
        }else if (cccc.equals("Light Cloud")){
            holder.imgHiss.setImageResource(R.drawable.light_cloud);
        }else if (cccc.equals("Clear")){
            holder.imgHiss.setImageResource(R.drawable.clear);
        }

    }
    @Override
    public int getItemCount(){
        return historObjs.size();
    }}