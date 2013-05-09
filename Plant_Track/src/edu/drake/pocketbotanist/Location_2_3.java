package edu.drake.pocketbotanist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class Location_2_3<GoogleMap> extends Activity implements OnMapClickListener{

	com.google.android.gms.maps.GoogleMap mMap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_2_3);
	 
		final LatLng center = new LatLng(42, -93.61);
		
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		CameraPosition cameraPosition = new CameraPosition.Builder()
			.target(center)
			.zoom(10)
			.build();
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		mMap.setMyLocationEnabled(true);
		mMap.setOnMapClickListener(this);
	}
	
	public void onMapClick(LatLng point) {

        mMap.animateCamera(CameraUpdateFactory.newLatLng(point));
                
        ((com.google.android.gms.maps.GoogleMap) mMap).addMarker(new MarkerOptions()
        .position(point)
        .title("New Point")
        .snippet("Species Name., May 7, 2013 1:24 AM " + point) 
		.icon(BitmapDescriptorFactory.fromResource(R.drawable.pb_marker))
		);

    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entrymap_1_3, menu);
		return true;
	}
}
