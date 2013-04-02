package com.cs.planttrack;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

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
	
	public void back(){
		finish();
	}

}
