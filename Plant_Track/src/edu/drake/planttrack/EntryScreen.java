package edu.drake.planttrack;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cs.planttrack.R;

public class EntryScreen extends Activity implements MapDialogFragment.MapDialogListener {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_screen);
		
		final Button pButton = (Button) findViewById(R.id.picButton);
		pButton.setOnClickListener(new View.OnClickListener() {      
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.setAction(Intent.ACTION_GET_CONTENT);//
				startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
			}
		});

		final Button lButton = (Button) findViewById(R.id.locButton);
		lButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MapDialogFragment mapDialog = new MapDialogFragment();
				mapDialog.show(getFragmentManager(), "Map Dialog");	
			}
		});
		
		final TextView date = (TextView) findViewById(R.id.dateTime);
		date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment((TextView) date);
				newFragment.show(getFragmentManager(), "datePicker");
			}
		});

		final TextView time = (TextView) findViewById(R.id.dateTime2);
		time.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new TimePickerFragment((TextView) time);
				newFragment.show(getFragmentManager(), "timePicker");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_entry_screen, menu);
		return true;
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