package edu.drake.planttrack;

import com.cs.planttrack.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

public class ManualLocationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manual_location);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manual_location, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.menu_settings:
	        settCall();
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	public void settCall(){
		Intent sett = new Intent(getBaseContext(),SettingsActivity.class);
        startActivity(sett);
	}
	
	public void back(){
		finish();
	}

}
