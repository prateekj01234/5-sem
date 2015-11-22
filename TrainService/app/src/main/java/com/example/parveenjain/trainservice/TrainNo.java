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
import android.widget.EditText;
import android.widget.Toast;

import com.example.parveenjain.trainservice.model.trainRouteModel;
import com.example.parveenjain.trainservice.parser.trainRouteParser;

import java.util.ArrayList;

public class TrainNo extends AppCompatActivity {


    EditText editText = (EditText) findViewById(R.id.editTrainNo);

    String trainNo = editText.getText().toString();
    String api = "iolce796";
    String uri = "http://api.railwayapi.com/route/train/"+trainNo+"/apikey/"+api+"/";

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

            requestData(uri);

        } else {
            Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();
        }
    }

    ArrayList<trainRouteModel> trainRouteList = new ArrayList<>();

    private void requestData(String uri) {
        Route task = new Route();
        task.execute(uri);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_no);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private class Route extends AsyncTask<String,String,String> {
        @Override
        protected  void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... param) {
            String content = Httpmanager.getData(param[0]);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            trainRouteList = trainRouteParser.parseFeed(s);
            Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("trainRoute",trainRouteList);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

}
