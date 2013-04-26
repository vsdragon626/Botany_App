package edu.drake.pocketbotanist;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ManualLocationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_manual_location);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		Button cancelB = (Button) findViewById(R.id.cancelButton);
		cancelB.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				back();
			}
		});
		
		Button okB = (Button) findViewById(R.id.okButton);
		okB.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				back();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.manual_location, menu);
		return true;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
		this.finish();
	}

}
