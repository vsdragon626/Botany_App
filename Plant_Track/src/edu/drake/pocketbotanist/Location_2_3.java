package edu.drake.pocketbotanist;

import edu.drake.pocketbotanist.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Location_2_3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_2_3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.location_2_3, menu);
		return true;
	}
	
	
	
	//code to change the default marker
	/*
	static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
	Marker melbourne = mMap.addMarker(new MarkerOptions()
	                          .position(MELBOURNE)
	                          .title("Melbourne")
	                          .snippet("Population: 4,137,400")
	                          .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
	                          */
	
	//GoogleMap.setOnMarkerClickListener(OnMarkerClickListener)
	//public abstract boolean onMarkerClick (Marker marker)
}
