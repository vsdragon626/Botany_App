package com.pocketbotanist;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class Entrymap_1_3<GoogleMap> extends FragmentActivity implements LocationListener, LocationSource{

	private com.google.android.gms.maps.GoogleMap mMap;
	private OnLocationChangedListener mListener;
	private LocationManager locationManager;
	
	@SuppressLint("ServiceCast")
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entrymap_1_3);

		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		
		if(locationManager != null)
		{
			boolean gpsIsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
			boolean networkIsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
			
			if(gpsIsEnabled)
			{
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000L, 10F, this);
			}
			else if(networkIsEnabled)
			{
				locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000L, 10F, this);
			}
			else
			{
				AlertDialog alertDialog = new AlertDialog.Builder(this).create();
				alertDialog.setTitle("Unavailable");
				alertDialog.setMessage("Location services are unavailable. Please check your GPS settings and/or Network availability.");
				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(final DialogInterface dialog, final int which) {
						dialog.dismiss();
						
					}
				});
			}
			
			}
		else
		{
			AlertDialog alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Unavailable");
			alertDialog.setMessage("Location services are unavailable. Google is likely experiencing technical difficulties.");
			alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(final DialogInterface dialog, final int which) {
					dialog.dismiss();
					
				}
			});
		}
		
		setUpMap();
		
	}

	private void setUpMap() {
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.setMyLocationEnabled(true);
		
		final LatLng center = new LatLng(41.601111, -93.652222);
		
		CameraPosition cameraPosition = new CameraPosition.Builder()
	    .target(center)      // Sets the center of the map to Mountain View
	    .zoom(12)                   // Sets the zoom
	    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
	    .build();                   // Creates a CameraPosition from the builder
	mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar
		getMenuInflater().inflate(R.menu.entrymap_1_3, menu);
		return true;
	}

	@Override
	public void activate(OnLocationChangedListener listener) {
		mListener = listener;
	}

	@Override
	public void deactivate() {
		mListener = null;
	}

	@Override
	public void onLocationChanged(Location location) {
		if(mListener != null)
		{
			mListener.onLocationChanged(location);
			mMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
		}
	}
	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "provider disabled", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "provider enabled", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
	}
	
	
}

//Get Latitude & Longitude from Database
		/*
		TextView mLongitude;
		TextView mLatitude;
		mLongitude = (TextView) findViewById(R.id.longit);
		mLatitude = (TextView) findViewById(R.id.latit);
		double lat = Double.parseDouble(mLatitude.getText().toString());
		double lon = Double.parseDouble(mLongitude.getText().toString());
		
		//Get ID number from Database
		EditText mID = (EditText) findViewById(R.id.idEdit);
		String id = mID.getText().toString();
		
		//Get Species from Database
		EditText mSpecies = (EditText) findViewById(R.id.speciesEdit);
		String species = mSpecies.getText().toString();
		
		//Get Date from Database
		TextView mDate = (TextView) findViewById(R.id.dateTime);
		String date = mDate.getText().toString();
		
		((com.google.android.gms.maps.GoogleMap) mMap).addMarker(new MarkerOptions()
		        .position(new LatLng(42,93))
		        .title("Title")
		        .snippet("species" + " " + "date") 
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.pb_marker))
				);
		*/