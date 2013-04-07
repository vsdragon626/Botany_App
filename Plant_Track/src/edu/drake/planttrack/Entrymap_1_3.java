package edu.drake.planttrack;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import edu.drake.planttrack.R;

public class Entrymap_1_3 extends FragmentActivity {

	@Override  
    protected void onCreate(Bundle arg0) {  
         // TODO Auto-generated method stub  
         super.onCreate(arg0);  
         setContentView(R.layout.activity_entrymap_1_3);  
    }  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entrymap_1_3, menu);
		return true;
	}
	
	

	
	//code to add marker
	/*
	private GoogleMap mMap;
	mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	mMap.addMarker(new MarkerOptions()
	        .position(new LatLng(0, 0))
	        .title("Hello world"));
	        
	//info on marker
	static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
	Marker melbourne = mMap.addMarker(new MarkerOptions()
                          .position(MELBOURNE)
                          .title("Melbourne")
                          .snippet("Population: 4,137,400"));
    
    
*/
}