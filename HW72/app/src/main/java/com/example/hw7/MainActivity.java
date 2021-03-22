package com.example.hw7;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button gobutton;
    Button gpsButton;
    EditText query;
    RequestQueue queue;
    private Object JsonObjectRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gobutton = findViewById(R.id.goButton);
        gpsButton = findViewById(R.id.locButton);
        query = findViewById(R.id.query);


    }

    public void onGoClick(View v){
        String q = query.getText().toString();
        sendRequest(q);

    }

    public void onGPSClick(View v){

    }

    public void sendRequest(String q){
        if(queue == null){
            Volley.newRequestQueue(this);
        }
        String url = (String)getString(R.string.WEATHER_URL);
        String key = (String)getString(R.string.API_KEY);
        url = url.replace("QUERY_REPLACE",q);
        url = url.replace("API_KEY_REPLACE",key);

        System.out.println("Using URL" + url);

        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("FUCK");
            }
        });

        queue.add((Request<Object>) JsonObjectRequest);

    }

}
