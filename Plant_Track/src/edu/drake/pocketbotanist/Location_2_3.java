package edu.drake.pocketbotanist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class Location_2_3<GoogleMap> extends Activity implements OnMarkerClickListener {

	@SuppressWarnings("null")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entrymap_1_3);
	 
		final LatLng center = new LatLng(42, -93.61);
		
		com.google.android.gms.maps.GoogleMap mMap = null;
		mMap.setOnMarkerClickListener(this);
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		CameraPosition cameraPosition = new CameraPosition.Builder()
			.target(center)
			.zoom(10)
			.build();
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		mMap.setMyLocationEnabled(true);
		
		/*public void onMapLongClick(LatLng point) {
			mMap.addMarker(new MarkerOptions()
			.position(point)
			.title("Selected Point")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.pb_marker)));
		}
		*/
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entrymap_1_3, menu);
		return true;
	}

	@Override
	public boolean onMarkerClick(Marker marker) {
		// TODO Auto-generated method stub
		return false;
	}
}