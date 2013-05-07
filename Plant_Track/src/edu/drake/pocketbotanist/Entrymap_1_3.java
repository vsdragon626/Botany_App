package edu.drake.pocketbotanist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Entrymap_1_3<GoogleMap> extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entrymap_1_3);
	 
		final LatLng center = new LatLng(42, -93.61);
		
		com.google.android.gms.maps.GoogleMap mMap;
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		CameraPosition cameraPosition = new CameraPosition.Builder()
			.target(center)
			.zoom(10)
			.build();
		mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
		mMap.setMyLocationEnabled(true);
		((com.google.android.gms.maps.GoogleMap) mMap).addMarker(new MarkerOptions()
		        .position(new LatLng(42.030278, -93.5908330))
		        .title("PAPAVERACEAE")
		        .snippet("Sanguinaria canadensis L., May 7, 2013 1:24 AM, 42.030278, -93.5908330") 
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.pb_marker))
				);
		((com.google.android.gms.maps.GoogleMap) mMap).addMarker(new MarkerOptions()
        	.position(new LatLng(42.070556, -93.651111))
        	.title("CRASSULACEAE")
        	.snippet("Penthorum sedoides L., May 7, 2013 1:24 AM, 42.070556, -93.651111")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.pb_marker))
		);
		((com.google.android.gms.maps.GoogleMap) mMap).addMarker(new MarkerOptions()
        	.position(new LatLng(42.056389, -93.645))
        	.title("COMMELINACEAE")
        	.snippet("Tradescantia ohiensis Raf., May 7, 2013 1:24 AM, 42.056389, -93.645")
			.icon(BitmapDescriptorFactory.fromResource(R.drawable.pb_marker))
		);
		((com.google.android.gms.maps.GoogleMap) mMap).addMarker(new MarkerOptions()
        	.position(new LatLng(41.656497, -93.592873))
        	.title("ST2-225")
        	 .snippet("Taraxacum Ocinale, May 7, 2013 1:22 AM, 41.656497, -93.592873")
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