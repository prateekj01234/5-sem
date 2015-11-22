package com.example.parveenjain.trainservice;

import android.graphics.Typeface;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.parveenjain.trainservice.model.stationModel;

import java.util.ArrayList;

public class Trains extends AppCompatActivity {

    TextView output;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trains);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();
        ArrayList<stationModel> arrayList = bundle.getParcelableArrayList("trainRecord");

        TableLayout table = new TableLayout(this);


        table.setStretchAllColumns(true);
        table.setShrinkAllColumns(true);

        TableRow rowTitle = new TableRow(this);
        rowTitle.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView title1 = new TextView(this);
        TextView title2 = new TextView(this);
        TextView title3 = new TextView(this);
        TextView title4 = new TextView(this);
        TextView title5 = new TextView(this);
        TextView title6 = new TextView(this);
        TextView title7 = new TextView(this);

        title1.setText("S.No.");
        title1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title1.setTypeface(Typeface.SERIF, Typeface.BOLD);

        title2.setText("Departure Time");
        title2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title2.setTypeface(Typeface.SERIF, Typeface.BOLD);

        title3.setText("To");
        title3.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title3.setTypeface(Typeface.SERIF, Typeface.BOLD);

        title4.setText("Arrival Time");
        title4.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title4.setTypeface(Typeface.SERIF, Typeface.BOLD);

        title5.setText("Train No.");
        title5.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title5.setTypeface(Typeface.SERIF, Typeface.BOLD);

        title6.setText("Name");
        title6.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title6.setTypeface(Typeface.SERIF, Typeface.BOLD);

        title7.setText("From");
        title7.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
        title7.setTypeface(Typeface.SERIF, Typeface.BOLD);

        TableRow.LayoutParams params = new TableRow.LayoutParams();
        params.span = 6;

        rowTitle.addView(title1,params);
        rowTitle.addView(title2,params);
        rowTitle.addView(title3,params);
        rowTitle.addView(title4,params);
        rowTitle.addView(title5,params);
        rowTitle.addView(title6,params);
        rowTitle.addView(title7,params);

        table.addView(rowTitle);

        for(stationModel q : arrayList) {
            TableRow row = new TableRow(this);

            TextView no = new TextView(this);
            TextView src_departure_time = new TextView(this);
            TextView to = new TextView(this);
            TextView dest_arrival_time = new TextView(this);
            TextView number = new TextView(this);
            TextView name = new TextView(this);
            TextView from = new TextView(this);

            no.setText(q.getNo());
            src_departure_time.setText(q.getSrc_departure_time());
            to.setText(q.getTo());
            dest_arrival_time.setText(q.getDest_arrival_time());
            number.setText(q.getNumber());
            name.setText(q.getName());
            from.setText(q.getFrom());

            row.addView(no);
            row.addView(src_departure_time);
            row.addView(to);
            row.addView(dest_arrival_time);
            row.addView(number);
            row.addView(name);
            row.addView(from);

            table.addView(row);

        }

        setContentView(table);


        output=(TextView) findViewById(R.id.trainoutputbox);
        output.setMovementMethod(new ScrollingMovementMethod());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    //protected void updateDisplay(String message){
    //    output.append(message + "\n");
    //}


}
