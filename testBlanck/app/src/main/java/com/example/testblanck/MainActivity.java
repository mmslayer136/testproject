package com.example.testblanck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


import android.os.AsyncTask;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    GPSTracker gps;

    private static final String TAG = HttpHandler.class.getSimpleName();

    private String search;

    private String key = "61f65b37afd4806851872c9fe3f4b7e2";
    private String city;
    private String country;
    private String temperature;
    private String feels_like;
    private String pressure;
    private String humidity;
    private String wind_speed;
    private String timezone;
    private String sunrise;
    private String sunset;
    private String clouds;
    private String description;
    private String lon;
    private String lat;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)
                    != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},REQUEST_CODE_PERMISSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void locationOnClick(View view)
    {
        EditText edittext = (EditText) findViewById(R.id.search);
        search = edittext.getText().toString();

        if(isNumeric(search)){
            search = "zip=" + search;
        }
        else{
            search = "q=" + search;
        }
        new GetJsonData().execute();
    }

    public void gpsOnClick(View view)
    {
        gps = new GPSTracker(MainActivity.this);
        if(gps.canGetLocation()){
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            search = "lat=" + latitude + "&lon=" + longitude;

            new GetJsonData().execute();
        }else{

            gps.showSettingsAlert();
        }
    }


    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public String toF(String temp)
    {
        double num = Double.parseDouble(temp);
        num = ((num - 273.15)*1.8)+32;
        num = Math.round(num);

        return String.valueOf(num) + "\u00B0 F";
    }


    private class GetJsonData extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            String url = "https://api.openweathermap.org/data/2.5/weather?" + search + "&appid=" + key;

            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if(jsonStr != null){
                try{

                    JSONObject json = new JSONObject(jsonStr);
                    city = json.getString("name");
                    timezone = json.getString("timezone");

                    JSONObject sys = json.getJSONObject("sys");
                    country = sys.getString("country");
                    sunrise = sys.getString("sunrise");
                    sunset = sys.getString("sunset");

                    JSONObject main = json.getJSONObject("main");
                    temperature = toF(main.getString("temp"));
                    feels_like = toF(main.getString("feels_like"));
                    pressure = main.getString("pressure") + " mPa";
                    humidity = main.getString("humidity") + "%";

                    JSONObject wind = json.getJSONObject("wind");
                    wind_speed = wind.getString("speed") + " m/s";

                    JSONArray weather = json.getJSONArray("weather");
                    JSONObject w = weather.getJSONObject(0);
                    clouds = w.getString("main");
                    description = w.getString("description");

                    JSONObject coord = json.getJSONObject("coord");
                    lon = coord.getString("lon");
                    lat = coord.getString("lat");

                }
                catch(final JSONException e){

                }
            }


            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            String temp;
            TextView textView = findViewById(R.id.row1);
            temp = "Temperature                 ";
            textView.setText(temp);

            textView = findViewById(R.id.row2);
            temp = "Pressure";
            textView.setText(temp);

            textView = findViewById(R.id.row3);
            temp = "Humidity";
            textView.setText(temp);

            textView = findViewById(R.id.row4);
            temp = "Feels like";
            textView.setText(temp);

            textView = findViewById(R.id.row5);
            temp = "Sunrise";
            textView.setText(temp);

            textView = findViewById(R.id.row6);
            temp = "Sunset";
            textView.setText(temp);

            textView = findViewById(R.id.row7);
            temp = "Wind Speed";
            textView.setText(temp);

            textView = findViewById(R.id.row8);
            temp = "Timezone";
            textView.setText(temp);

            textView = findViewById(R.id.row9);
            temp = "Description";
            textView.setText(temp);



            textView = findViewById(R.id.place);
            temp = city + ", " + country;
            textView.setText(temp);

            textView = findViewById(R.id.display1);
            textView.setText(clouds);

            textView = findViewById(R.id.temperature);
            textView.setText(temperature);

            textView = findViewById(R.id.pressure);
            textView.setText(pressure);

            textView = findViewById(R.id.humidity);
            textView.setText(humidity);

            textView = findViewById(R.id.feelslike);
            textView.setText(feels_like);

            textView = findViewById(R.id.sunrise);
            textView.setText(sunrise);

            textView = findViewById(R.id.sunset);
            textView.setText(sunset);

            textView = findViewById(R.id.windspeed);
            textView.setText(wind_speed);

            textView = findViewById(R.id.timezone);
            textView.setText(timezone);

            textView = findViewById(R.id.description);
            textView.setText(description);

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;

                    double lati = Double.parseDouble(lat);
                    double longi = Double.parseDouble(lon);

                    LatLng TutorialsPoint = new LatLng(lati, longi);
                    mMap.addMarker(new MarkerOptions().position(TutorialsPoint).title("Your Location"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));

                }
            });

        }


    }
}
