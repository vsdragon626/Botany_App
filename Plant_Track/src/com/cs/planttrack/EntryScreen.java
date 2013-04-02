package com.cs.planttrack;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class EntryScreen extends Activity implements MapDialogFragment.MapDialogListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_screen);
		final Button button2 = (Button) findViewById(R.id.button2);
		button2.setOnClickListener(new View.OnClickListener() {      
		    @Override
		    public void onClick(View v) {
		    	Intent intent = new Intent();
		    	intent.setType("image/*");
		    	intent.setAction(Intent.ACTION_GET_CONTENT);//
		    	startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
		    }
		});
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
		Intent intent = new Intent(EntryScreen.this, Location_2_3.class);
		startActivity(intent);
	}
	
	@Override
	public void onManual(DialogFragment dialog){
		Intent intent = new Intent(EntryScreen.this, ManualLocationActivity.class);
		startActivity(intent);
	}
}
