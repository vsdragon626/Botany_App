package edu.drake.pocketbotanist;

import java.io.File;
import java.util.Calendar;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EntryScreen extends Activity implements MapDialogFragment.MapDialogListener, 
													 PhotoDialogFragment.PhotoDialogListener {
	EditText iD;
	String path;
	ImageView pic;
	String filename;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_screen);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		    int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
			TextView title = (TextView)findViewById(titleId);
			title.setText(extras.getString("passer"));
		}
		
		final Button pButton = (Button) findViewById(R.id.picButton);
		pButton.setOnClickListener(new View.OnClickListener() {      
			@Override
			public void onClick(View v) {
				PhotoDialogFragment photoDialog = new PhotoDialogFragment();
				photoDialog.show(getFragmentManager(), "Photo Dialog");	
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

		Calendar c = Calendar.getInstance();
		String m = getMonth(c.get(Calendar.MONTH));
		date.setText(m + " " + String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + ", " + String.valueOf(c.get(Calendar.YEAR)));

		final TextView time = (TextView) findViewById(R.id.dateTime2);
		time.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new TimePickerFragment((TextView) time);
				newFragment.show(getFragmentManager(), "timePicker");
			}
		});

		time.setText(String.valueOf(c.get(Calendar.HOUR)) + " : " + String.valueOf(c.get(Calendar.MINUTE)) + " ");

		pic = (ImageView) findViewById(R.id.image_preview);
		pic.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v){
				PhotoDialogFragment photoDialog = new PhotoDialogFragment();
				photoDialog.show(getFragmentManager(), "Photo Dialog");
			}
		});

		final TextView lati = (TextView) findViewById(R.id.lat);
		lati.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MapDialogFragment mapDialog = new MapDialogFragment();
				mapDialog.show(getFragmentManager(), "Map Dialog");
			}
		});

		lati.setText("Click to Edit");

		final TextView longi = (TextView) findViewById(R.id.lon);
		longi.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MapDialogFragment mapDialog = new MapDialogFragment();
				mapDialog.show(getFragmentManager(), "Map Dialog");
			}
		});
		
		final Button cancel = (Button) findViewById(R.id.cancelButton);
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		longi.setText("Click to Edit");
		
		updatePref();

	}
	
	@Override
	public void onRestart(){
		super.onRestart();
		finish();
		startActivity(getIntent());
	}

	public void updatePref(){
		SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		if(sharedPrefs.getBoolean("SwitchPref1", false)){
			TextView tv9 = (TextView) findViewById(R.id.textView9);
			tv9.setVisibility(0);
			tv9.setText(sharedPrefs.getString("editTextPref1", "Nothing"));
			RelativeLayout erl1 = (RelativeLayout) findViewById(R.id.extraRealative1);
			erl1.setVisibility(0);
			MultiAutoCompleteTextView btv3 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext3);
			btv3.setVisibility(0);
		}
		else{
			TextView tv9 = (TextView) findViewById(R.id.textView9);
			tv9.setVisibility(8);
			RelativeLayout erl1 = (RelativeLayout) findViewById(R.id.extraRealative1);
			erl1.setVisibility(8);
			MultiAutoCompleteTextView btv3 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext3);
			btv3.setVisibility(8);
		}
		if(sharedPrefs.getBoolean("SwitchPref2", false)){
			TextView tv10 = (TextView) findViewById(R.id.textView10);
			tv10.setVisibility(0);
			tv10.setText(sharedPrefs.getString("editTextPref2", "Nothing"));
			RelativeLayout erl2 = (RelativeLayout) findViewById(R.id.extraRealative2);
			erl2.setVisibility(0);
			MultiAutoCompleteTextView btv4 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext4);
			btv4.setVisibility(0);
		}
		else{
			TextView tv10 = (TextView) findViewById(R.id.textView10);
			tv10.setVisibility(8);
			RelativeLayout erl2 = (RelativeLayout) findViewById(R.id.extraRealative2);
			erl2.setVisibility(8);
			MultiAutoCompleteTextView btv4 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext4);
			btv4.setVisibility(8);
		}
		if(sharedPrefs.getBoolean("SwitchPref3", false)){
			TextView tv11 = (TextView) findViewById(R.id.textView11);
			tv11.setVisibility(0);
			tv11.setText(sharedPrefs.getString("editTextPref3", "Nothing"));
			RelativeLayout erl3 = (RelativeLayout) findViewById(R.id.extraRealative3);
			erl3.setVisibility(0);
			MultiAutoCompleteTextView btv5 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext5);
			btv5.setVisibility(0);
		}
		else{
			TextView tv11 = (TextView) findViewById(R.id.textView11);
			tv11.setVisibility(8);
			RelativeLayout erl3 = (RelativeLayout) findViewById(R.id.extraRealative3);
			erl3.setVisibility(8);
			MultiAutoCompleteTextView btv5 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext5);
			btv5.setVisibility(8);
		}
		if(sharedPrefs.getBoolean("SwitchPref4", false)){
			TextView tv12 = (TextView) findViewById(R.id.textView12);
			tv12.setVisibility(0);
			tv12.setText(sharedPrefs.getString("editTextPref4", "Nothing"));
			RelativeLayout erl4 = (RelativeLayout) findViewById(R.id.extraRealative4);
			erl4.setVisibility(0);
			MultiAutoCompleteTextView btv6 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext6);
			btv6.setVisibility(0);
		}
		else{
			TextView tv12 = (TextView) findViewById(R.id.textView12);
			tv12.setVisibility(8);
			RelativeLayout erl4 = (RelativeLayout) findViewById(R.id.extraRealative4);
			erl4.setVisibility(8);
			MultiAutoCompleteTextView btv6 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext6);
			btv6.setVisibility(8);
		}
		if(sharedPrefs.getBoolean("SwitchPref5", false)){
			TextView tv13 = (TextView) findViewById(R.id.textView13);
			tv13.setVisibility(0);
			tv13.setText(sharedPrefs.getString("editTextPref5", "Nothing"));
			RelativeLayout erl5 = (RelativeLayout) findViewById(R.id.extraRealative5);
			erl5.setVisibility(0);
			MultiAutoCompleteTextView btv7 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext7);
			btv7.setVisibility(0);
		}
		else{
			TextView tv13 = (TextView) findViewById(R.id.textView13);
			tv13.setVisibility(8);
			RelativeLayout erl5 = (RelativeLayout) findViewById(R.id.extraRealative5);
			erl5.setVisibility(8);
			MultiAutoCompleteTextView btv7 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext7);
			btv7.setVisibility(8);
		}
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

	@Override
	public void onTakePicture(DialogFragment dialog) {
		iD = (EditText) findViewById(R.id.idEdit);
		if(!iD.getText().equals("")||iD.getText()!=null){
			File imageDirectory = new File(Environment.getExternalStorageDirectory().toString()+"Pocket Botanist/"+iD.getText()+"/");
			if (!imageDirectory.exists()){
				imageDirectory.mkdir();
				path = imageDirectory.getAbsolutePath();
			}
		}
		else{
			String date;
			Calendar c = Calendar.getInstance();
			String m = getMonth(c.get(Calendar.MONTH));
			date = m + " " + String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + ", " + String.valueOf(c.get(Calendar.YEAR));
			File imageDirectory = new File(Environment.getExternalStorageDirectory().toString()+"Pocket Botanist/"+"New Entry "+date+"/");
			File[] contents = imageDirectory.listFiles();
			int i = 1;
			while(imageDirectory.exists() && contents == null){
				imageDirectory = new File(Environment.getExternalStorageDirectory().toString()+"Pocket Botanist/"+"New Entry "+date+"("+String.valueOf(i)+")"+"/");
				i++;
			}
		}
		//TODO Test on actual device
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		filename = String.valueOf(System.currentTimeMillis()) + ".jpg";
		File file = new File(Environment.getExternalStorageDirectory(), path + filename);
		
		Uri outputFileUri = Uri.fromFile(file); 
		takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri); 
		 
		startActivityForResult(takePictureIntent, 1);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		//TODO test on actual device
		if(resultCode == 1){
			pic.setImageURI(data.getData());

//			BitmapFactory.Options options = new BitmapFactory.Options();
//			options.inSampleSize = 4;
//			Bitmap bitmap = BitmapFactory.decodeFile(filename, options);
//			pic.setImageBitmap(bitmap);
		}
		
	}

	@Override
	public void onChoosePicture(DialogFragment dialog) {
		//TODO implement advanced gallery code
		Intent intent = new Intent();
		intent.setType("image/*");
		intent.setAction(Intent.ACTION_GET_CONTENT);//
		startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);
	}
	
	public String getMonth(int month){
		String m = "";
		switch (month+1) {
		case 1:  
			m = "January";
			break;
		case 2:  
			m = "February";
			break;
		case 3:  
			m = "March";
			break;
		case 4:  
			m = "April";
			break;
		case 5:  
			m = "May";
			break;
		case 6:  
			m = "June";
			break;
		case 7:  
			m = "July";
			break;
		case 8:  
			m = "August";
			break;
		case 9:  
			m = "September";
			break;
		case 10: 
			m = "October";
			break;
		case 11: 
			m = "November";
			break;
		case 12: 
			m = "December";
			break;
		default: 
			m = "Invalid month";
			break;
		}
		return m;
	}
}