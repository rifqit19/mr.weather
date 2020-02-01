package com.rifqi3g.cuaca;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

public class detailNextDay extends AppCompatActivity {

    TextView weatherStatD,maxD,theD,minD,windSpeedD,airPressD,humidityD,tanggalD,tempD;
    ImageView imgWeatherD;
    public locationObj locationObj = new locationObj();
    ImageButton back;
    LinearLayout linBackD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_next_day);

        weatherStatD = findViewById(R.id.weather_statD);
        maxD = findViewById(R.id.maxTempD);
        theD = findViewById(R.id.theTempD);
        minD = findViewById(R.id.minTempD);
        windSpeedD = findViewById(R.id.windSpeedD);
        airPressD = findViewById(R.id.airPressureD);
        humidityD = findViewById(R.id.huidityD);
        linBackD = findViewById(R.id.linBackD);
        tanggalD = findViewById(R.id.tanggalD);
        imgWeatherD = findViewById(R.id.image_weatherD);
        tempD = findViewById(R.id.temperatureD);
        back = findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        String locloc = getIntent().getStringExtra("xxxxxx");
        locationObj = new Gson().fromJson(locloc, locationObj.class);

        String wther = locationObj.getWeatherStateName();
        weatherStatD.setText(wther);
        maxD.setText(locationObj.getMaxTemp().toString());
        theD.setText(locationObj.getTheTemp().toString());
        minD.setText(locationObj.getMinTemp().toString());
        windSpeedD.setText(locationObj.getWindSpeed().toString());
        airPressD.setText(locationObj.getAirPressure().toString());
        humidityD.setText(locationObj.getHumidity().toString());
        tanggalD.setText(locationObj.getApplicableDate());
        tempD.setText(locationObj.getTheTemp().toString());

        if (wther.isEmpty()) {
            imgWeatherD.setImageResource(R.drawable.logoo);
            linBackD.setBackgroundResource(R.drawable.heavy_rain_bb);
        }else if (wther.equals("Snow")){
            imgWeatherD.setImageResource(R.drawable.snow);
            linBackD.setBackgroundResource(R.drawable.snow_b);
        }else if (wther.equals("Hail")){
            imgWeatherD.setImageResource(R.drawable.hail);
            linBackD.setBackgroundResource(R.drawable.hail_b);
        }else if (wther.equals("Sleet")){
            imgWeatherD.setImageResource(R.drawable.sleet);
            linBackD.setBackgroundResource(R.drawable.sleet_b);
        }else if (wther.equals("Thunderstorm")){
            imgWeatherD.setImageResource(R.drawable.tunderstrom);
            linBackD.setBackgroundResource(R.drawable.thunderstrom_b);
        }else if (wther.equals("Heavy Rain")){
            imgWeatherD.setImageResource(R.drawable.heavy_rain);
            linBackD.setBackgroundResource(R.drawable.heavy_rain_bb);
        }else if (wther.equals("Light Rain")){
            imgWeatherD.setImageResource(R.drawable.light_rain);
            linBackD.setBackgroundResource(R.drawable.light_rain_b);
        }else if (wther.equals("Showers")){
            imgWeatherD.setImageResource(R.drawable.shower);
            linBackD.setBackgroundResource(R.drawable.shower_b);
        }else if (wther.equals("Heavy Cloud")){
            imgWeatherD.setImageResource(R.drawable.heave_cloud);
            linBackD.setBackgroundResource(R.drawable.heavy_cloud_b);
        }else if (wther.equals("Light Cloud")){
            imgWeatherD.setImageResource(R.drawable.light_cloud);
            linBackD.setBackgroundResource(R.drawable.light_cloud_b);
        }else if (wther.equals("Clear")){
            imgWeatherD.setImageResource(R.drawable.clear);
            linBackD.setBackgroundResource(R.drawable.clear_b);
        }



    }
}
