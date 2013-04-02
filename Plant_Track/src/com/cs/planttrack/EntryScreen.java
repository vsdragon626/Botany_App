package com.cs.planttrack;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class EntryScreen extends Activity implements MapDialogFragment.MapDialogListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_entry_screen, menu);
		return true;
	}
	
	public void locClick(View v){
		MapDialogFragment mapDialog = new MapDialogFragment();
		mapDialog.show(getFragmentManager(), "Map Dialog");	 
		
	}
	
	@Override
	public void onDrop(DialogFragment dialog){
		
	}
	
	@Override
	public void onManual(DialogFragment dialog){
		Intent intent = new Intent(EntryScreen.this, ManualLocationActivity.class);
		startActivity(intent);
	}
}
