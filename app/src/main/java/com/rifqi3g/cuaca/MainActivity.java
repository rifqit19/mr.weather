package com.rifqi3g.cuaca;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDoalog;
    String tittle,LattLong,locationType;
    Integer woeid;
    private String TAG = MainActivity.class.getSimpleName();
    TextView txtTittle,weatherStat,max,the,min,windSpeed,airPress,humidity,tanggal,temp;
    ImageView imgWeather;
    private adapterWeather adapter;
    private adapterHistory adapter2;
    private RecyclerView recyclerView,recyclerView1 ;
    private ArrayList<locationObj> locationObjs = new ArrayList<>();
//    ArrayList<searchObj> searchObjs = new ArrayList<>();
    private ArrayList<historObj> historObjs = new ArrayList<>();
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    LinearLayout linBack;
    String date,date1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar2 = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar2);
        ActionBar actionBar = getSupportActionBar();
        setSupportActionBar(toolbar2);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        date1 = dateFormat.format(calendar.getTime());

        txtTittle = findViewById(R.id.tittle);
        weatherStat = findViewById(R.id.weather_stat);
        max = findViewById(R.id.maxTemp);
        the = findViewById(R.id.theTemp);
        min = findViewById(R.id.minTemp);
        windSpeed = findViewById(R.id.windSpeed);
        airPress = findViewById(R.id.airPressure);
        humidity = findViewById(R.id.huidity);
        tanggal = findViewById(R.id.tanggal);
        imgWeather = findViewById(R.id.image_weather);
        linBack = findViewById(R.id.linearBackground);
        temp = findViewById(R.id.temperature);
        search("Jakarta");
        tanggal.setText(date1);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_bar, menu);
        MenuItem searchIem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchIem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextSubmit(String query) {
               search(query);
                searchView.clearFocus();
                return true;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }
    public void location(){

        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getLocation(woeid);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {

                        String respon = response.body().string();
                        Log.e(TAG, "Response " + respon);
                        JSONObject jsonObj = new JSONObject(respon);

                        JSONArray api = jsonObj.getJSONArray("consolidated_weather");

                        JSONObject c = api.getJSONObject(0);

                        String weatherStt = c.getString("weather_state_name");
                        String date = c.getString("applicable_date");
                        Double maxT = c.getDouble("max_temp");
                        Double theT = c.getDouble("the_temp");
                        Double minT = c.getDouble("min_temp");
                        Double windS = c.getDouble("wind_speed");
                        Double airP = c.getDouble("air_pressure");
                        Double hum = c.getDouble("humidity");

                        String maxx = Double.toString(maxT);
                        String thee = Double.toString(theT);
                        String minn = Double.toString(minT);
                        String wnd = Double.toString(windS);
                        String air = Double.toString(airP);
                        String humm = Double.toString(hum);

                        locationObj locationObj = new locationObj();
                        locationObj.setWeatherStateName(weatherStt);

                        weatherStat.setText(weatherStt);
                        if (weatherStt.isEmpty()){
                            imgWeather.setImageResource(R.drawable.logoo);
                            linBack.setBackgroundResource(R.drawable.heavy_rain_bb);
                        }else if (weatherStt.equals("Snow")){
                            imgWeather.setImageResource(R.drawable.snow);
                            linBack.setBackgroundResource(R.drawable.snow_b);
                        }else if (weatherStt.equals("Hail")){
                            imgWeather.setImageResource(R.drawable.hail);
                            linBack.setBackgroundResource(R.drawable.hail_b);
                        }else if (weatherStt.equals("Sleet")){
                            imgWeather.setImageResource(R.drawable.sleet);
                            linBack.setBackgroundResource(R.drawable.sleet_b);
                        }else if (weatherStt.equals("Thunderstorm")){
                            imgWeather.setImageResource(R.drawable.tunderstrom);
                            linBack.setBackgroundResource(R.drawable.thunderstrom_b);
                        }else if (weatherStt.equals("Heavy Rain")){
                            imgWeather.setImageResource(R.drawable.heavy_rain);
                            linBack.setBackgroundResource(R.drawable.heavy_rain_bb);
                        }else if (weatherStt.equals("Light Rain")){
                            imgWeather.setImageResource(R.drawable.light_rain);
                            linBack.setBackgroundResource(R.drawable.light_rain_b);
                        }else if (weatherStt.equals("Showers")){
                            imgWeather.setImageResource(R.drawable.shower);
                            linBack.setBackgroundResource(R.drawable.shower_b);
                        }else if (weatherStt.equals("Heavy Cloud")){
                            imgWeather.setImageResource(R.drawable.heave_cloud);
                            linBack.setBackgroundResource(R.drawable.heavy_cloud_b);
                        }else if (weatherStt.equals("Light Cloud")){
                            imgWeather.setImageResource(R.drawable.light_cloud);
                            linBack.setBackgroundResource(R.drawable.light_cloud_b);
                        }else if (weatherStt.equals("Clear")){
                            imgWeather.setImageResource(R.drawable.clear);
                            linBack.setBackgroundResource(R.drawable.clear_b);
                        }
                        max.setText(maxx);
                        the.setText(thee);
                        min.setText(minn);
                        windSpeed.setText(wnd);
                        airPress.setText(air);
                        humidity.setText(humm);
//                        tanggal.setText(date);
                        temp.setText(thee);

                    } catch (JSONException e){
                        Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void rcyclerLoc(){
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getLocation(woeid);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                progressDoalog.dismiss();
                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        Log.e(TAG, "Response " + respon);
                        JSONObject jsonObj = new JSONObject(respon);
                        locationObjs.clear();

                        JSONArray api = jsonObj.getJSONArray("consolidated_weather");

                        for (int i = 1; i < api.length(); i++){
                            JSONObject c = api.getJSONObject(i);

                            String weatherStt1 = c.getString("weather_state_name");
                            String date1 = c.getString("applicable_date");
                            Double maxT1 = c.getDouble("max_temp");
                            Double theT1 = c.getDouble("the_temp");
                            Double minT1 = c.getDouble("min_temp");
                            Double windS1 = c.getDouble("wind_speed");
                            Double airP1 = c.getDouble("air_pressure");
                            Double hum1 = c.getDouble("humidity");

                            locationObj opopo = new locationObj();
                            opopo.setWeatherStateName(weatherStt1);
                            opopo.setApplicableDate(date1);
                            opopo.setAirPressure(airP1);
                            opopo.setMaxTemp(maxT1);
                            opopo.setMinTemp(minT1);
                            opopo.setTheTemp(theT1);
                            opopo.setWindSpeed(windS1);
                            opopo.setHumidity(hum1);
                            locationObjs.add(opopo);
                        }
                        adapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Souldn't get json from server.");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void
                        run() {
                            Toast.makeText(getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateDataList() {
        recyclerView = findViewById(R.id.recNext);
        adapter = new adapterWeather(this,locationObjs);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }
    public void search(String search){
        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading...");
        progressDoalog.show();
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call =  service.getSearch(search);
        call.enqueue(new Callback<ResponseBody>()  {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    try {
                        String respon = response.body().string();
                        Log.e(TAG, "Response " + respon);

                        JSONArray api = new JSONArray(respon);

                        JSONObject c = api.getJSONObject(0);

                        tittle = c.getString("title");
                        locationType = c.getString("location_type");
                        woeid = c.getInt("woeid");
                        LattLong = c.getString("latt_long");

                        searchObj searchObj = new searchObj();
                        searchObj.setTitle(tittle);
                        searchObj.setLocationType(locationType);
                        searchObj.setWoeid(woeid);
                        searchObj.setLattLong(LattLong);
                        txtTittle.setText(tittle);
                        location();
                        generateDataList();
                        rcyclerLoc();
                        generateDataList2();
                        locationDay();

                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, "Error Connection...", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"Couldn't get json from server. Check LoCat for possible errors!",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Error connection..", Toast.LENGTH_SHORT).show();
            }
        });
            }
    public void locationDay() {
        Log.e(TAG,"jjj "+ tittle);
        Log.e(TAG, "jjj "+ locationType);
        Log.e(TAG,"jjj "+ woeid);
        Log.e(TAG, "jjj "+ LattLong);
        GetDataService service = RetrofitCilentInstance.getRetrofitInstance().create(GetDataService.class);
        Call<ResponseBody> call = service.getDate(woeid,date1);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    try {
                        String respon = response.body().string();
                        Log.e(TAG, "Response " + respon);

                        JSONArray api = new JSONArray(respon);

                        for (int i = 0; i < api.length(); i++) {
                            JSONObject o = api.getJSONObject(i);

                            String wtherr = o.getString("weather_state_name");
                            Integer id = o.getInt("id");
                            String abbr = o.getString("weather_state_abbr");
                            String compass = o.getString("wind_direction_compass");
                            String crete = o.getString("created");
                            String appDate = o.getString("applicable_date");
                            Double minTm = o.getDouble("min_temp");
                            Double theTm = o.getDouble("the_temp");
                            Double maxTm = o.getDouble("max_temp");
                            Integer wd = o.getInt("wind_direction");
                            Double aps = o.getDouble("air_pressure");
                            String humd = o.getString("humidity");
                            String vis = o.getString("visibility");
                            Integer pred = o.getInt("predictability");
                            Object oooo = humd;
                            Object oo = vis;

                            historObj k = new historObj();
                            k.setWeatherStateName(wtherr);
                            k.setId(id);
                            k.setWeatherStateAbbr(abbr);
                            k.setWindDirectionCompass(compass);
                            k.setCreated(crete);
                            k.setApplicableDate(appDate);
                            k.setMinTemp(minTm);
                            k.setTheTemp(theTm);
                            k.setMaxTemp(maxTm);
                            k.setWindDirection(wd);
                            k.setAirPressure(aps);
                            k.setHumidity(oooo);
                            k.setVisibility(oo);
                            k.setPredictability(pred);
                            historObjs.add(k);
                        }
                        adapter2.notifyDataSetChanged();

                    } catch (JSONException e) {
                        Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Couldn't get json from server. Check LoCat for possible errors!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateDataList2() {
        recyclerView1 = findViewById(R.id.recyclerHistory);
        adapter2 = new adapterHistory(MainActivity.this,historObjs);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView1.setLayoutManager(mLayoutManager);
        recyclerView1.setLayoutManager(mLayoutManager);
        recyclerView1.setAdapter(adapter2);
    }


}
