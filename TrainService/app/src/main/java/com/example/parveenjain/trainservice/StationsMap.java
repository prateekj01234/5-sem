package com.example.parveenjain.trainservice;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.support.v4.app.FragmentActivity;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.example.parveenjain.trainservice.model.trainCodeModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.List;

public class StationsMap extends AppCompatActivity {

    ProgressBar pb;

    String api="iolce7946";
    EditText editS= (EditText) findViewById(R.id.editSource);
    String source = editS.getText().toString();
    EditText editD= (EditText) findViewById(R.id.editDestination);
    String destination = editD.getText().toString();

    String uriscode ="http://api.railwayapi.com/name_to_code/station/"+source+"/apikey/"+api+"/";
    String uridcode ="http://api.railwayapi.com/name_to_code/station/"+destination+"/apikey/"+api+"/";

    String uri = "http://api.railwayapi.com/between/source/<station code>/dest/<station code>/apikey/"+api+"/";

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    public void submit(View view) {
        if (isOnline()) {
            requestData(uriscode);
            requestData(uridcode);
            requestData(uri);
        } else {
            Toast.makeText(this,"Network isn't available",Toast.LENGTH_LONG).show();
        }
    }
    /* if (isOnline()) {
        requestData();
        } else {
            Toast.makeText(this,"Network isn't available",Toast.LENGTH_LONG).show();
           }
        */

    List<trainCodeModel> traiCcodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stations_map);
        GoogleMap googleMap;
        googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }



private void requestData(String uri) {
     MyTask task = new MyTask();
     task.execute(uri);
}

    private class MyTask extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... param) {
            BufferedReader reader=null;
            try {
                URL url = new URL(param[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();

                StringBuilder sb = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line;
                while((line = reader.readLine()) != null) {
                    sb.append(line+"\n");
                }
                return sb.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                if(reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }

        }

        @Override
        protected void onPostExecute(String s) {
           // updateDisplay(s);
            pb.setVisibility(View.INVISIBLE);
            super.onPostExecute(s);
        }
    }

}
