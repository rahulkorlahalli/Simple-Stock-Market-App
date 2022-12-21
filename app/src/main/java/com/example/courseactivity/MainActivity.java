package com.example.courseactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //Creating required Variables
    ImageButton TeslaBtn;
    ImageButton GoogleBtn;
    ImageButton AppleBtn;
    ImageButton TataBtn;
    ImageButton RelianceBtn;
    ImageButton ItcBtn;
    String apikey = "";
    ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Finding views & initializing variables
        TeslaBtn = (ImageButton) findViewById(R.id.TeslaBtn);
        GoogleBtn = (ImageButton) findViewById(R.id.GoogleBtn);
        AppleBtn = (ImageButton) findViewById(R.id.AppleBtn);
        TataBtn = (ImageButton) findViewById(R.id.TataBtn);
        RelianceBtn = (ImageButton) findViewById(R.id.RelianceBtn);
        ItcBtn = (ImageButton) findViewById(R.id.ItcBtn);
        progress = new ProgressDialog(MainActivity.this);

        //Extracting alpha vantage api-key from assets folder
        try {
            InputStream ip = getAssets().open("apikey.txt");
            int size = ip.available();
            byte[] buffer = new byte[size];
            ip.read(buffer);
            apikey = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TeslaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setTitle("Loading"); // setting title
                progress.setMessage("Please wait ..."); // creating message
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); // style of indicator
                progress.setIndeterminate(true);
                progress.show();
                String api ="https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=TSLA&outputsize=compactl&apikey=" + apikey;
                CompanyData c = new CompanyData("TESLA","TSLA","Tesla, Inc. is an American multinational automotive and clean energy company headquartered in Austin, Texas. Tesla designs and manufactures electric vehicles, battery energy storage from home to grid-scale, solar panels and solar roof tiles, and related products and services.","$");
                getData(api,c);
            }
        });

        GoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setTitle("Loading"); // setting title
                progress.setMessage("Please wait ..."); // creating message
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); // style of indicator
                progress.setIndeterminate(true);
                progress.show();
                String api ="https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=GOOG&outputsize=compactl&apikey=" + apikey;
                CompanyData c = new CompanyData("GOOGLE","GOOG","Google LLC is an American multinational technology company focusing on search engine technology, online advertising, cloud computing, computer software, quantum computing, e-commerce, artificial intelligence, and consumer electronics.","$");
                getData(api,c);
            }
        });

        AppleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setTitle("Loading"); // setting title
                progress.setMessage("Please wait ..."); // creating message
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); // style of indicator
                progress.setIndeterminate(true);
                progress.show();
                String api ="https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=AAPL&outputsize=compactl&apikey=" + apikey;
                CompanyData c = new CompanyData("APPLE","AAPL","Apple Inc. is an American multinational technology company headquartered in Cupertino, California, United States. Apple is the largest technology company by revenue (totaling US$365.8 billion in 2021) and, as of June 2022, is the world's biggest company by market capitalization, the fourth-largest personal computer vendor by unit sales and second-largest mobile phone manufacturer.","$");
                getData(api,c);
            }
        });

        TataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setTitle("Loading"); // setting title
                progress.setMessage("Please wait ..."); // creating message
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); // style of indicator
                progress.setIndeterminate(true);
                progress.show();
                String api ="https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=TATAMOTORS.BSE&outputsize=compactl&apikey=" + apikey;
                CompanyData c = new CompanyData("TATA","TATAMOTORS","The Tata Group is an Indian multinational conglomerate headquartered in Mumbai. Established in 1868, it is India's largest conglomerate, with products and services in over 150 countries, and operations in 100 countries across six continents.","₹");
                getData(api,c);
            }
        });

        RelianceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setTitle("Loading"); // setting title
                progress.setMessage("Please wait ..."); // creating message
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); // style of indicator
                progress.setIndeterminate(true);
                progress.show();
                String api ="https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=RELIANCE.BSE&outputsize=compactl&apikey=" + apikey;
                CompanyData c = new CompanyData("RELIANCE","RELIANCE","Reliance Industries Limited is an Indian multinational conglomerate company, headquartered in Mumbai. It has diverse businesses including energy, petrochemicals, natural gas, retail, telecommunications, mass media, and textiles.","₹");
                getData(api,c);
            }
        });

        ItcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setTitle("Loading"); // setting title
                progress.setMessage("Please wait ..."); // creating message
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER); // style of indicator
                progress.setIndeterminate(true);
                progress.show();
                String api ="https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=ITC.BSE&outputsize=compactl&apikey=" + apikey;
                CompanyData c = new CompanyData("ITC Ltd","ITC","ITC Limited is an Indian conglomerate company headquartered in Kolkata. ITC has a diversified presence across industries such as FMCG, hotels, software, packaging, paperboards, specialty papers and agribusiness. The company has 13 businesses in 5 segments. It exports its products in 90 countries.","₹");
                getData(api,c);
            }
        });
    }

    //Using Volley library to make API request
    private void getData(String api, CompanyData c){
        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject result = new JSONObject(response);
                            JSONObject obj = result.getJSONObject("Time Series (Daily)");
                            HashMap<String, Object> retMap = toMap(obj);

                            //Passing the response to second activity
                            Intent i = new Intent(MainActivity.this,SecondActivity.class);
                            i.putExtra("hashMap", retMap);
                            i.putExtra("CompanyData",c);
                            startActivity(i);
                            progress.dismiss();

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Log.e("api", "onErrorResponse: " + error.getLocalizedMessage());
                progress.dismiss();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("API request failed. Try again later!");
                builder.setTitle("Alert !");
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        queue.add(stringRequest);
    }

    //Method to convert API response (JSON object) to required format (Hashmap)
    public static HashMap<String, Object> toMap(JSONObject object) throws JSONException {
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);
            if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
        return (HashMap<String, Object>) map;
    }

}