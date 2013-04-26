package edu.drake.pocketbotanist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

<<<<<<< HEAD
=======
import com.cs.pocketbotanist.R;
import com.google.android.gms.maps.CameraUpdateFactory;
<<<<<<< HEAD
>>>>>>> bdfaca179d552be04e7858257ff335377fe2cdf8
=======
>>>>>>> bdfaca179d552be04e7858257ff335377fe2cdf8
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Entrymap_1_3<GoogleMap> extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entrymap_1_3);
	 
		com.google.android.gms.maps.GoogleMap mMap;
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(null, 41, -93, 0));
		((com.google.android.gms.maps.GoogleMap) mMap).addMarker(new MarkerOptions()
		        .position(new LatLng(41.601378, -93.6518857))
		        .title("ST2-225")
				.snippet("41.601378, -93.6518857")
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