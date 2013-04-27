package edu.drake.pocketbotanist;

import java.io.File;
import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
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
import android.widget.Toast;

public class EntryScreen extends Activity implements MapDialogFragment.MapDialogListener, 
													 PhotoDialogFragment.PhotoDialogListener{
	private int numTimes;
	private ImageView pic;
	private String filename,foldername;
	private File[] allFiles;
	private String path = Environment.getExternalStorageDirectory().toString()+"/Pocket Botanist/";
	private TextView lati;
	private TextView longi;
	private TextView date;
	private TextView time;
	private EditText idnum,species,common;
	private MultiAutoCompleteTextView plantNotes, habitatNotes, extraNotes1, extraNotes2, extraNotes3, extraNotes4, extraNotes5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_screen);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			ActionBar actionBar = getActionBar();
			actionBar.setTitle(extras.getString("passer"));
			fillData(extras.getString("passer"));
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

		date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment((TextView) date);
				newFragment.show(getFragmentManager(), "datePicker");
			}
		});

		time.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new TimePickerFragment((TextView) time);
				newFragment.show(getFragmentManager(), "timePicker");
			}
		});

		pic.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v){
				PhotoDialogFragment photoDialog = new PhotoDialogFragment();
				photoDialog.show(getFragmentManager(), "Photo Dialog");
			}
		});

		lati.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MapDialogFragment mapDialog = new MapDialogFragment();
				mapDialog.show(getFragmentManager(), "Map Dialog");
			}
		});

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

		updatePref();

	}
	
	public void fillData(String id){
		//TODO function that will fill the data if there is an entry in the database
		if(id.equals("ST2-225")){
			idnum = (EditText) findViewById(R.id.idEdit);
			idnum.setText(id);
			species = (EditText) findViewById(R.id.speciesEdit);
			species.setText("Taraxacum Ocinale");
			common = (EditText) findViewById(R.id.commonEdit);
			common.setText("Dandelion");
			Calendar c = Calendar.getInstance();
			String m = getMonth(c.get(Calendar.MONTH));
			date = (TextView) findViewById(R.id.dateTime);
			date.setText(m + " " + String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + ", " + String.valueOf(c.get(Calendar.YEAR)));
			time = (TextView) findViewById(R.id.dateTime2);
			time.setText(String.valueOf(c.get(Calendar.HOUR)) + " : " + String.valueOf(c.get(Calendar.MINUTE)) + " ");
			pic = (ImageView) findViewById(R.id.image_preview);
			pic.setImageResource(R.drawable.plant1);
			lati = (TextView) findViewById(R.id.lat);
			lati.setText("41.656497");
			longi = (TextView) findViewById(R.id.lon);
			longi.setText("-93.592873");
			plantNotes = (MultiAutoCompleteTextView) findViewById(R.id.pNotes);
			plantNotes.setText("This particular plant had a nearly flawless flower, with what looked like a heathly green stem.");
			habitatNotes = (MultiAutoCompleteTextView) findViewById(R.id.hNotes);
			habitatNotes.setText("The habitat surrounding this plant was about standard for what you would exect");
			extraNotes1 = (MultiAutoCompleteTextView) findViewById(R.id.extra1);
			extraNotes1.setText("");
			extraNotes2 = (MultiAutoCompleteTextView) findViewById(R.id.extra2);
			extraNotes2.setText("");
			extraNotes3 = (MultiAutoCompleteTextView) findViewById(R.id.extra3);
			extraNotes3.setText("");
			extraNotes4 = (MultiAutoCompleteTextView) findViewById(R.id.extra4);
			extraNotes4.setText("");
			extraNotes5 = (MultiAutoCompleteTextView) findViewById(R.id.extra5);
			extraNotes5.setText("");
			
		}
		else if(id.equals("PAPAVERACEAE")){
			idnum = (EditText) findViewById(R.id.idEdit);
			idnum.setText(id);
			species = (EditText) findViewById(R.id.speciesEdit);
			species.setText("Sanguinaria canadensis L.");
			common = (EditText) findViewById(R.id.commonEdit);
			common.setText("Bloodroot");
			Calendar c = Calendar.getInstance();
			String m = getMonth(c.get(Calendar.MONTH));
			date = (TextView) findViewById(R.id.dateTime);
			date.setText(m + " " + String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + ", " + String.valueOf(c.get(Calendar.YEAR)));
			time = (TextView) findViewById(R.id.dateTime2);
			time.setText(String.valueOf(c.get(Calendar.HOUR)) + " : " + String.valueOf(c.get(Calendar.MINUTE)) + " ");
			pic = (ImageView) findViewById(R.id.image_preview);
			pic.setImageResource(R.drawable.plant0);
			lati = (TextView) findViewById(R.id.lat);
			lati.setText("42° 1' 49\" N");
			longi = (TextView) findViewById(R.id.lon);
			longi.setText("93° 35' 27\" W");
			plantNotes = (MultiAutoCompleteTextView) findViewById(R.id.pNotes);
			plantNotes.setText("");
			habitatNotes = (MultiAutoCompleteTextView) findViewById(R.id.hNotes);
			habitatNotes.setText("Iowa: Story Co.: Ames: Carr Woods (East River Valley Access; flat wooded area, specimens interspersed among other members of the herbaceous layer)");
			
		}
		else if(id.equals("CRASSULACEAE")){
			idnum = (EditText) findViewById(R.id.idEdit);
			idnum.setText(id);
			species = (EditText) findViewById(R.id.speciesEdit);
			species.setText("Penthorum sedoides L.");
			common = (EditText) findViewById(R.id.commonEdit);
			common.setText("Ditch Stonecrop");
			Calendar c = Calendar.getInstance();
			String m = getMonth(c.get(Calendar.MONTH));
			date = (TextView) findViewById(R.id.dateTime);
			date.setText(m + " " + String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + ", " + String.valueOf(c.get(Calendar.YEAR)));
			time = (TextView) findViewById(R.id.dateTime2);
			time.setText(String.valueOf(c.get(Calendar.HOUR)) + " : " + String.valueOf(c.get(Calendar.MINUTE)) + " ");
			pic = (ImageView) findViewById(R.id.image_preview);
			pic.setImageResource(R.drawable.plant2);
			lati = (TextView) findViewById(R.id.lat);
			lati.setText("42° 4' 14\" N");
			longi = (TextView) findViewById(R.id.lon);
			longi.setText("93° 39' 4\" W");
			plantNotes = (MultiAutoCompleteTextView) findViewById(R.id.pNotes);
			plantNotes.setText("Quite abundant along the riverbank on the west side, south of the boat ramp/Riverside Dr. overpass.");
			habitatNotes = (MultiAutoCompleteTextView) findViewById(R.id.hNotes);
			habitatNotes.setText("Iowa: Story Co.: Ames: Sleepy Hollow Access to South Skunk River.");
		
		}
		else if(id.equals("COMMELINACEAE")){
			idnum = (EditText) findViewById(R.id.idEdit);
			idnum.setText(id);
			species = (EditText) findViewById(R.id.speciesEdit);
			species.setText("Tradescantia ohiensis Raf.");
			common = (EditText) findViewById(R.id.commonEdit);
			common.setText("Ohio Spiderwort");
			Calendar c = Calendar.getInstance();
			String m = getMonth(c.get(Calendar.MONTH));
			date = (TextView) findViewById(R.id.dateTime);
			date.setText(m + " " + String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + ", " + String.valueOf(c.get(Calendar.YEAR)));
			time = (TextView) findViewById(R.id.dateTime2);
			time.setText(String.valueOf(c.get(Calendar.HOUR)) + " : " + String.valueOf(c.get(Calendar.MINUTE)) + " ");
			pic = (ImageView) findViewById(R.id.image_preview);
			pic.setImageResource(R.drawable.plant3);
			lati = (TextView) findViewById(R.id.lat);
			lati.setText("42° 3' 23\" N");
			longi = (TextView) findViewById(R.id.lon);
			longi.setText("93° 38' 42\" W");
			plantNotes = (MultiAutoCompleteTextView) findViewById(R.id.pNotes);
			plantNotes.setText("At the time of collection, Stange Rd. heading north ended in a T-intersection with Bloomington Dr.  Specimen was located in a routinely water-logged ditch on the north side of the T-intersection.  Subsequent urban development around 2007 or 2008 destroyed the habitat; Stange Rd. now runs further north, on top of where the population grew.");
			habitatNotes = (MultiAutoCompleteTextView) findViewById(R.id.hNotes);
			habitatNotes.setText("Iowa: Story Co.: Ames: Intersection of Stange and Bloomington.");
		}
		else{
			Calendar c = Calendar.getInstance();
			String m = getMonth(c.get(Calendar.MONTH));
			date = (TextView) findViewById(R.id.dateTime);
			date.setText(m + " " + String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + ", " + String.valueOf(c.get(Calendar.YEAR)));
			time = (TextView) findViewById(R.id.dateTime2);
			time.setText(String.valueOf(c.get(Calendar.HOUR)) + " : " + String.valueOf(c.get(Calendar.MINUTE)) + " ");
			lati = (TextView) findViewById(R.id.lat);
			lati.setText("Click to Edit");
			longi = (TextView) findViewById(R.id.lon);
			longi.setText("Click to Edit");
			pic = (ImageView) findViewById(R.id.image_preview);
		}
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
			MultiAutoCompleteTextView btv3 = (MultiAutoCompleteTextView) findViewById(R.id.extra1);
			btv3.setVisibility(0);
		}
		else{
			TextView tv9 = (TextView) findViewById(R.id.textView9);
			tv9.setVisibility(8);
			RelativeLayout erl1 = (RelativeLayout) findViewById(R.id.extraRealative1);
			erl1.setVisibility(8);
			MultiAutoCompleteTextView btv3 = (MultiAutoCompleteTextView) findViewById(R.id.extra1);
			btv3.setVisibility(8);
		}
		if(sharedPrefs.getBoolean("SwitchPref2", false)){
			TextView tv10 = (TextView) findViewById(R.id.textView10);
			tv10.setVisibility(0);
			tv10.setText(sharedPrefs.getString("editTextPref2", "Nothing"));
			RelativeLayout erl2 = (RelativeLayout) findViewById(R.id.extraRealative2);
			erl2.setVisibility(0);
			MultiAutoCompleteTextView btv4 = (MultiAutoCompleteTextView) findViewById(R.id.extra2);
			btv4.setVisibility(0);
		}
		else{
			TextView tv10 = (TextView) findViewById(R.id.textView10);
			tv10.setVisibility(8);
			RelativeLayout erl2 = (RelativeLayout) findViewById(R.id.extraRealative2);
			erl2.setVisibility(8);
			MultiAutoCompleteTextView btv4 = (MultiAutoCompleteTextView) findViewById(R.id.extra2);
			btv4.setVisibility(8);
		}
		if(sharedPrefs.getBoolean("SwitchPref3", false)){
			TextView tv11 = (TextView) findViewById(R.id.textView11);
			tv11.setVisibility(0);
			tv11.setText(sharedPrefs.getString("editTextPref3", "Nothing"));
			RelativeLayout erl3 = (RelativeLayout) findViewById(R.id.extraRealative3);
			erl3.setVisibility(0);
			MultiAutoCompleteTextView btv5 = (MultiAutoCompleteTextView) findViewById(R.id.extra3);
			btv5.setVisibility(0);
		}
		else{
			TextView tv11 = (TextView) findViewById(R.id.textView11);
			tv11.setVisibility(8);
			RelativeLayout erl3 = (RelativeLayout) findViewById(R.id.extraRealative3);
			erl3.setVisibility(8);
			MultiAutoCompleteTextView btv5 = (MultiAutoCompleteTextView) findViewById(R.id.extra3);
			btv5.setVisibility(8);
		}
		if(sharedPrefs.getBoolean("SwitchPref4", false)){
			TextView tv12 = (TextView) findViewById(R.id.textView12);
			tv12.setVisibility(0);
			tv12.setText(sharedPrefs.getString("editTextPref4", "Nothing"));
			RelativeLayout erl4 = (RelativeLayout) findViewById(R.id.extraRealative4);
			erl4.setVisibility(0);
			MultiAutoCompleteTextView btv6 = (MultiAutoCompleteTextView) findViewById(R.id.extra4);
			btv6.setVisibility(0);
		}
		else{
			TextView tv12 = (TextView) findViewById(R.id.textView12);
			tv12.setVisibility(8);
			RelativeLayout erl4 = (RelativeLayout) findViewById(R.id.extraRealative4);
			erl4.setVisibility(8);
			MultiAutoCompleteTextView btv6 = (MultiAutoCompleteTextView) findViewById(R.id.extra4);
			btv6.setVisibility(8);
		}
		if(sharedPrefs.getBoolean("SwitchPref5", false)){
			TextView tv13 = (TextView) findViewById(R.id.textView13);
			tv13.setVisibility(0);
			tv13.setText(sharedPrefs.getString("editTextPref5", "Nothing"));
			RelativeLayout erl5 = (RelativeLayout) findViewById(R.id.extraRealative5);
			erl5.setVisibility(0);
			MultiAutoCompleteTextView btv7 = (MultiAutoCompleteTextView) findViewById(R.id.extra5);
			btv7.setVisibility(0);
		}
		else{
			TextView tv13 = (TextView) findViewById(R.id.textView13);
			tv13.setVisibility(8);
			RelativeLayout erl5 = (RelativeLayout) findViewById(R.id.extraRealative5);
			erl5.setVisibility(8);
			MultiAutoCompleteTextView btv7 = (MultiAutoCompleteTextView) findViewById(R.id.extra5);
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
		return super.onCreateOptionsMenu(menu);
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
		idnum = (EditText) findViewById(R.id.idEdit);
		File imageDirectory;
		int numOfPics = 0;
		if(!idnum.getText().toString().isEmpty()){
			imageDirectory = new File(Environment.getExternalStorageDirectory().toString()+"/Pocket Botanist/"+idnum.getText()+"/");
			if (!imageDirectory.exists()){
				imageDirectory.mkdir();
				foldername = idnum.getText()+"/";
			}
			else{
				numOfPics = imageDirectory.list().length;
				foldername = idnum.getText()+"/";
			}
		}
		else{
			String date;
			Calendar c = Calendar.getInstance();
			date = c.get(Calendar.MONTH)+1 + "-" + String.valueOf(c.get(Calendar.DAY_OF_MONTH)) + "-" + String.valueOf(c.get(Calendar.YEAR));
			imageDirectory = new File(Environment.getExternalStorageDirectory().toString()+"/Pocket Botanist/"+"New Entry-"+date+"/");
			foldername = "New Entry-"+date+"/";
			File[] contents = imageDirectory.listFiles();
			int i = 1;
			while(imageDirectory.exists() && contents != null){
				imageDirectory = new File(Environment.getExternalStorageDirectory().toString()+"/Pocket Botanist/"+"New Entry-"+date+"("+String.valueOf(i)+")"+"/");
				i++;
				contents = imageDirectory.listFiles();
				foldername = "New Entry-"+date+"("+String.valueOf(i--)+")"+"/";
			}
			imageDirectory.mkdir();
		}
		
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		if(numOfPics == 0){
			filename = idnum.getText()+".jpg";
		}
		else{
			filename = idnum.getText()+"("+numOfPics+")"+".jpg";
		}
		File file = new File(Environment.getExternalStorageDirectory()+"/Pocket Botanist/"+foldername, filename);
		
		Uri outputFileUri = Uri.fromFile(file); 
		takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri); 

		startActivityForResult(takePictureIntent, 1);
		
	}

	@Override
	public void onViewPicture(DialogFragment dialog) {
		//TODO implement advanced gallery code
		File folder = new File(path+idnum.getText()+"/");
		if(!folder.exists()){
			folder.mkdir();
		}
		allFiles = folder.listFiles();
		if(allFiles.length == 0){
			Toast.makeText(getBaseContext(), "No pictures currently associated with this entry.", Toast.LENGTH_SHORT).show();
		}
		else{
			new MultiMediaScanner(this.getBaseContext(), allFiles);
		}
	}
    
    public class MultiMediaScanner implements MediaScannerConnectionClient {

        private MediaScannerConnection mMs;
        private File[] mFile;

        public MultiMediaScanner(Context context, File[] f) {
            mFile = f;
            mMs = new MediaScannerConnection(context, this);
            mMs.connect();
        }

        public void onMediaScannerConnected() {
        	for(int i = 0; i < mFile.length; i++){
        		mMs.scanFile(mFile[i].getAbsolutePath(), null);
        	}
        }

        public void onScanCompleted(String path, Uri uri) {
        	if(numTimes==mFile.length-1){
        		Intent intent = new Intent(Intent.ACTION_VIEW);
            	intent.setData(uri);
            	startActivity(intent);
            	mMs.disconnect();
            	numTimes=0;
        	}
        	else{
            	numTimes++;
        	}
        }

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
