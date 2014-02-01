package com.pocketbotanist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.pocketbotanist.R;
import com.pocketbotanist.contentprovider.MyEntryContentProvider;
import com.pocketbotanist.database.EntryTable;


public class EntryScreen extends Activity implements MapDialogFragment.MapDialogListener, 
PhotoDialogFragment.PhotoDialogListener{
	private int numTimes;
	private String filename,foldername;
	private File[] allFiles;
	private String path = Environment.getExternalStorageDirectory().toString()+"/Pocket Botanist/";
	private GestureDetector gestureDetector;
	private Uri itemUri;
	private EditText mSpecies;
	private EditText mID;
	private EditText mCommonName;
	private TextView mDate;
	private TextView mTime;
	private ImageView mPhoto;
	private String mPhotos = "";
	private TextView mLongitude;
	private TextView mLatitude;
	private MultiAutoCompleteTextView mPlantNotes;
	private MultiAutoCompleteTextView mHabitatNotes;
	private MultiAutoCompleteTextView mExtraNotes1;
	private MultiAutoCompleteTextView mExtraNotes2;
	private MultiAutoCompleteTextView mExtraNotes3;
	private MultiAutoCompleteTextView mExtraNotes4;
	private MultiAutoCompleteTextView mExtraNotes5;
	private View.OnTouchListener gestureListener;
	private String imageUri = "";
	private File imagePath;
	private File[] images;
	private int iter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_screen);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mSpecies = (EditText) findViewById(R.id.speciesEdit);
		mID = (EditText) findViewById(R.id.idEdit);
		mCommonName = (EditText) findViewById(R.id.commonEdit);
		mDate = (TextView) findViewById(R.id.dateTime);
		mTime = (TextView) findViewById(R.id.dateTime2);
		mPhoto = (ImageView) findViewById(R.id.image_preview);
		mLongitude = (TextView) findViewById(R.id.longit);
		mLatitude = (TextView) findViewById(R.id.latit);
		mPlantNotes = (MultiAutoCompleteTextView) findViewById(R.id.pNotes);
		mHabitatNotes = (MultiAutoCompleteTextView) findViewById(R.id.hNotes);
		mExtraNotes1 = (MultiAutoCompleteTextView) findViewById(R.id.extra1);
		mExtraNotes2 = (MultiAutoCompleteTextView) findViewById(R.id.extra2);
		mExtraNotes3 = (MultiAutoCompleteTextView) findViewById(R.id.extra3);
		mExtraNotes4 = (MultiAutoCompleteTextView) findViewById(R.id.extra4);
		mExtraNotes5 = (MultiAutoCompleteTextView) findViewById(R.id.extra5);

		Button saveButton = (Button) findViewById(R.id.saveButton);

		Bundle extras = getIntent().getExtras();

		final TextView pButton = (TextView) findViewById(R.id.picButton);

		saveButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				saveState();
				finish();
			}

		});

		pButton.setOnClickListener(new View.OnClickListener() {      
			@Override
			public void onClick(View v) {
				PhotoDialogFragment photoDialog = new PhotoDialogFragment();
				photoDialog.show(getFragmentManager(), "Photo Dialog");	
			}
		});

		final TextView lButton = (TextView) findViewById(R.id.locButton);
		lButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MapDialogFragment mapDialog = new MapDialogFragment();
				mapDialog.show(getFragmentManager(), "Map Dialog");	
			}
		});

		mDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new DatePickerFragment((TextView) mDate);
				newFragment.show(getFragmentManager(), "datePicker");
			}
		});

		mTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment newFragment = new TimePickerFragment((TextView) mTime);
				newFragment.show(getFragmentManager(), "timePicker");
			}
		});

		mPhoto.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(View v){
				PhotoDialogFragment photoDialog = new PhotoDialogFragment();
				photoDialog.show(getFragmentManager(), "Photo Dialog");
				return true;
			}
		});

		mLatitude.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				MapDialogFragment mapDialog = new MapDialogFragment();
				mapDialog.show(getFragmentManager(), "Map Dialog");
			}
		});

		mLongitude.setOnClickListener(new View.OnClickListener() {

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

		imagePath = new File(path+mID.getText().toString());
		images = imagePath.listFiles();
		gestureDetector = new GestureDetector(this, new myGestureDetector());
		gestureListener = new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				return gestureDetector.onTouchEvent(event);
			}
		}; 
		mPhoto.setOnTouchListener(gestureListener);

		mLongitude.setText("Click to Edit");
		mLatitude.setText("Click to Edit");

		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		if(hour > 12){
			if(minute < 10){
				mTime.setText(String.valueOf(hour-12) + " : 0" + String.valueOf(minute)+" PM");
			}
			else{
				mTime.setText(String.valueOf(hour-12) + " : " + String.valueOf(minute) + " PM");
			}
		}
		else{
			if(minute < 10){
				mTime.setText(String.valueOf(hour) + " : 0" + String.valueOf(minute) + " AM");
			}
			else{
				mTime.setText(String.valueOf(hour) + " : " + String.valueOf(minute) + " AM");
			}
		}

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		String m = getMonth(month);
		mDate.setText(m + " " + String.valueOf(day) + ", " + String.valueOf(year));


		updatePref();

		// Check from the saved Instance
		itemUri = (savedInstanceState == null) ? null : (Uri) savedInstanceState
				.getParcelable(MyEntryContentProvider.CONTENT_ITEM_TYPE);

		// Or passed from the other activity
		if (extras != null) {
			itemUri = extras
					.getParcelable(MyEntryContentProvider.CONTENT_ITEM_TYPE);

			fillData(itemUri);
		}

		ActionBar actionBar = getActionBar();
		if (mID.getText().toString().compareTo("") != 1){
			actionBar.setTitle("New Entry");
		}
		else{
			actionBar.setTitle(mID.getText().toString());
		}
	}

	class myGestureDetector extends SimpleOnGestureListener {
		//TODO make sure this no longer gives out of memory error
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			images = imagePath.listFiles();
			try {
				if (Math.abs(e1.getY() - e2.getY()) > 250)
					return false;
				if(e1.getX() - e2.getX() > 60 && Math.abs(velocityX) > 150) {
					//TODO Left Swipe
					if(iter <= 0 && iter < images.length-1){
						iter++;
					}
					else{
						iter = images.length-1;
					}
					//Toast.makeText(getBaseContext(), "Left Swipe", Toast.LENGTH_SHORT).show();
				}  else if (e2.getX() - e1.getX() > 60 && Math.abs(velocityX) > 150) {
					//TODO Right Swipe
					if(iter > images.length-1 && iter >= 0){
						iter++;
					}
					else{
						iter = 0;
					}
					//Toast.makeText(getBaseContext(), "Right Swipe", Toast.LENGTH_SHORT).show();
				}
				Bitmap bm = null;
				try {
					bm = Bitmap.createScaledBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(images[iter].getAbsolutePath())), 100, 100, false);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Uri pathUri = Uri.parse(images[iter].getAbsolutePath());
				imageUri = pathUri.toString();
				mPhoto.setImageBitmap(bm);
			} catch (Exception e) {
				Log.v("Gesture Error", "Failed to read gesture");
			}
			return false;
		}
	}

	@Override
	public void onRestart(){
		super.onRestart();
		updatePref();
	}

	private void fillData(Uri uri) {
		String[] projection = {EntryTable.COLUMN_SPECIES, EntryTable.COLUMN_CUSTOMID,
				EntryTable.COLUMN_COMMONNAME, EntryTable.COLUMN_DATE,
				EntryTable.COLUMN_TIME, EntryTable.COLUMN_PHOTOS,
				EntryTable.COLUMN_PHOTO, EntryTable.COLUMN_LONGITUDE,
				EntryTable.COLUMN_LATITUDE, EntryTable.COLUMN_PLANTNOTES,
				EntryTable.COLUMN_HABITATNOTES, EntryTable.COLUMN_EXTRANOTES1,
				EntryTable.COLUMN_EXTRANOTES2, EntryTable.COLUMN_EXTRANOTES3,
				EntryTable.COLUMN_EXTRANOTES4, EntryTable.COLUMN_EXTRANOTES5 };				
		if (uri != null) {
			Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
			cursor.moveToFirst();
			if (cursor.getColumnCount() > 0) {
			mSpecies.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_SPECIES)));
			mID.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_CUSTOMID)));
			mCommonName.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_COMMONNAME)));
			mDate.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_DATE)));
			mTime.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_TIME)));
			mPhotos = (cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_PHOTOS)));
			mPhoto.setImageURI(Uri.parse((cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_PHOTO)))));
			mLongitude.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_LONGITUDE)));
			mLatitude.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_LATITUDE)));
			mPlantNotes.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_PLANTNOTES)));
			mHabitatNotes.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_HABITATNOTES)));
			mExtraNotes1.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_EXTRANOTES1)));
			mExtraNotes2.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_EXTRANOTES2)));
			mExtraNotes3.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_EXTRANOTES3)));
			mExtraNotes4.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_EXTRANOTES4)));
			mExtraNotes5.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_EXTRANOTES4)));
			}
			// Always close the cursor
			cursor.close();
		}
	}

	private void saveState() {
		String species = mSpecies.getText().toString();
		String id = mID.getText().toString();
		String name = mCommonName.getText().toString();
		String date = mDate.getText().toString();
		String time = mTime.getText().toString();
		String photos = mPhotos;
		String photo = imageUri;
		String lon = mLongitude.getText().toString();
		String lat = mLatitude.getText().toString();
		String plantNotes = mPlantNotes.getText().toString();
		String habitatNotes = mHabitatNotes.getText().toString();
		String extraNotes1 = mExtraNotes1.getText().toString();
		String extraNotes2 = mExtraNotes2.getText().toString();
		String extraNotes3 = mExtraNotes3.getText().toString();
		String extraNotes4 = mExtraNotes4.getText().toString();
		String extraNotes5 = mExtraNotes5.getText().toString();

		ContentValues values = new ContentValues();

		values.put(EntryTable.COLUMN_SPECIES, species);
		values.put(EntryTable.COLUMN_CUSTOMID, id);
		values.put(EntryTable.COLUMN_COMMONNAME, name);
		values.put(EntryTable.COLUMN_DATE, date);
		values.put(EntryTable.COLUMN_TIME, time);
		values.put(EntryTable.COLUMN_PHOTOS, photos);
		values.put(EntryTable.COLUMN_PHOTO, photo);
		values.put(EntryTable.COLUMN_LONGITUDE, lon);
		values.put(EntryTable.COLUMN_LATITUDE, lat);
		values.put(EntryTable.COLUMN_PLANTNOTES, plantNotes);
		values.put(EntryTable.COLUMN_HABITATNOTES,  habitatNotes);
		values.put(EntryTable.COLUMN_EXTRANOTES1,  extraNotes1);
		values.put(EntryTable.COLUMN_EXTRANOTES2,  extraNotes2);
		values.put(EntryTable.COLUMN_EXTRANOTES3,  extraNotes3);
		values.put(EntryTable.COLUMN_EXTRANOTES4,  extraNotes4);
		values.put(EntryTable.COLUMN_EXTRANOTES5,  extraNotes5);

		if (itemUri == null) {
			// New item
			itemUri = getContentResolver().insert(MyEntryContentProvider.CONTENT_URI, values);
		} else {
			// Update item
			getContentResolver().update(itemUri, values, null, null);
		}
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

	public void closeMe()
	{
		finish();
	}

	@Override
	public void onManual(DialogFragment dialog){
		Intent intent = new Intent(EntryScreen.this, ManualLocationActivity.class);
		startActivity(intent);
	}

	@Override
	public void onTakePicture(DialogFragment dialog) {
		File imageDirectory;
		int numOfPics = 0;
		if(!mID.getText().toString().isEmpty()){
			imageDirectory = new File(Environment.getExternalStorageDirectory().toString()+"/Pocket Botanist/"+mID.getText()+"/");
			if (!imageDirectory.exists()){
				imageDirectory.mkdir();
				foldername = mID.getText()+"/";
			}
			else{
				numOfPics = imageDirectory.list().length;
				foldername = mID.getText()+"/";
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

		mPhotos = path+foldername;

		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		if(numOfPics == 0){
			filename = mID.getText()+".bmp";
		}
		else{
			filename = mID.getText()+"("+numOfPics+")"+".bmp";
		}
		File file = new File(Environment.getExternalStorageDirectory()+"/Pocket Botanist/"+foldername, filename);

		Uri outputFileUri = Uri.fromFile(file); 
		takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri); 

		startActivityForResult(takePictureIntent, 1);
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    // Check which request we're responding to
	    if (requestCode == 1) {
	        // Make sure the request was successful
	        if (resultCode == RESULT_OK) {
	        	File f = new File(Environment.getExternalStorageDirectory()+"/Pocket Botanist/"+foldername, filename);
	        	fillPic(f);
	        }
	    }
	}

	public void fillPic(File f){
		//TODO WORK ON THIS SHIT!
		Bitmap bm = null;
		try {
			bm = Bitmap.createScaledBitmap(MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(f.getAbsolutePath())), 100, 100, false);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Uri pathUri = Uri.parse(f.getAbsolutePath());
		imageUri = pathUri.toString();
		mPhoto.setImageBitmap(bm);
	}

	@Override
	public void onViewPicture(DialogFragment dialog) {
		File folder = new File(path+mID.getText()+"/");
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