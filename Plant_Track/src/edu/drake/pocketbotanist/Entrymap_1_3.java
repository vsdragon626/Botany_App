package edu.drake.pocketbotanist;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Entrymap_1_3<GoogleMap> extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entrymap_1_3);
	 
		com.google.android.gms.maps.GoogleMap mMap;
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		((com.google.android.gms.maps.GoogleMap) mMap).addMarker(new MarkerOptions()
		        .position(new LatLng(0, 0))
		        .title("Hello world"));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entrymap_1_3, menu);
		return true;
	}
}