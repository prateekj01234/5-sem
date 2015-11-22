package com.example.parveenjain.trainservice;

import android.content.Context;
import android.content.Intent;
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


import com.example.parveenjain.trainservice.model.stationModel;
import com.example.parveenjain.trainservice.model.trainCodeModel;
import com.example.parveenjain.trainservice.parser.stationParser;
import com.example.parveenjain.trainservice.parser.trainCodeParser;
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
import java.util.ArrayList;
import java.util.List;

public class StationsMap extends AppCompatActivity {

    ProgressBar pb;

    String scode,dcode;
    String uri;
    String api="iolce7946";
    EditText editS= (EditText) findViewById(R.id.editSource);
    String source = editS.getText().toString();
    EditText editD= (EditText) findViewById(R.id.editDestination);
    String destination = editD.getText().toString();

    String uriscode ="http://api.railwayapi.com/name_to_code/station/"+source+"/apikey/"+api+"/";
    String uridcode ="http://api.railwayapi.com/name_to_code/station/"+destination+"/apikey/"+api+"/";



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
            requestData(uriscode,1);

            for(trainCodeModel trainCodeModel : trainCcodeList) {

                if(trainCodeModel.getFullname()==source.toUpperCase()) {
                    scode=trainCodeModel.getCode();
                    break;
                }
            }

            requestData(uridcode,1);

            for(trainCodeModel trainCodeModel : trainCcodeList) {
                if(trainCodeModel.getFullname()==destination.toUpperCase()) {
                    dcode=trainCodeModel.getCode();
                    break;
                }
            }
            uri = "http://api.railwayapi.com/between/source/"+scode+"/dest/"+dcode+"/apikey/"+api+"/";

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

    List<trainCodeModel> trainCcodeList;
    //List<stationModel> stationList;
    ArrayList<stationModel> stationList = new ArrayList<>();


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



    private void requestData(String uri,int tag) {
        StationCode task = new StationCode();
        task.execute(uri);
    }
    private void requestData(String uri) {
        Train task = new Train();
        task.execute(uri);
    }

    private class StationCode extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
           // super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... param) {

            String content = Httpmanager.getData(param[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            trainCcodeList = trainCodeParser.parseFeed(s);
           // updateDisplay(s);
            pb.setVisibility(View.INVISIBLE);
          //  super.onPostExecute(s);
        }
    }

    private class Train extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            pb.setVisibility(View.VISIBLE);
            //super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... param) {

            String content = Httpmanager.getData(param[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            stationList = stationParser.parseFeed(s);
            // updateDisplay(s);
            Intent intent = new Intent(getApplicationContext(),Trains.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("trainRecord",stationList);
            intent.putExtras(bundle);
            startActivity(intent);
            pb.setVisibility(View.INVISIBLE);
           // super.onPostExecute(s);
        }
    }

}
