package edu.drake.pocketbotanist;

import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import edu.drake.pocketbotanist.database.EntryTable;
import edu.drake.pocketbotanist.contentprovider.MyEntryContentProvider;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;

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
		*/
		((com.google.android.gms.maps.GoogleMap) mMap).addMarker(new MarkerOptions()
		        .position(new LatLng(42,93))
		        .title("Title")
		        .snippet("species" + " " + "date") 
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.pb_marker))
				);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entrymap_1_3, menu);
		return true;
	}
	
/*
	private void fillData() {

		// Fields from the database (projection)
		String[] from = new String[] { EntryTable.COLUMN_CUSTOMID, EntryTable.COLUMN_SPECIES , EntryTable.COLUMN_TIME, EntryTable.COLUMN_PHOTO};
		// Fields on the UI to which we map
		int[] to = new int[] { R.id.customidlabel, R.id.namelabel, R.id.timelabel, R.id.imageView };

		getLoaderManager().initLoader(0, null, this);
		Adapter adapter = new SimpleCursorAdapter(this, R.layout.list_row, null, from,
				to, 0);

		setListAdapter(adapter);
	}*/
	
}