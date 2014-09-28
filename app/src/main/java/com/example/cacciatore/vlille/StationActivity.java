package com.example.cacciatore.vlille;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import stations.Station;


public class StationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);

        Station station = (Station) getIntent().getSerializableExtra("station");
        TextView textName = (TextView) findViewById(R.id.stationName);
        textName.setText("Station : "+station.getName());
        TextView textAddress = (TextView) findViewById(R.id.stationAddress);
        textAddress.setText("Adresse : "+station.getAddress());
        TextView textNbBikes = (TextView) findViewById(R.id.stationNbBikes);
        textNbBikes.setText("Velos : "+station.getNbBikes());
        TextView textNbAttachs = (TextView) findViewById(R.id.stationNbAttachs);
        textNbAttachs.setText("Places : "+station.getNbAttachs());

        TextView textLat = (TextView) findViewById(R.id.stationLat);
        textLat.setText("Latitude : "+station.getLat());
        TextView textLng = (TextView) findViewById(R.id.stationLng);
        textLng.setText("Longitude : "+station.getLng());

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.station, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
