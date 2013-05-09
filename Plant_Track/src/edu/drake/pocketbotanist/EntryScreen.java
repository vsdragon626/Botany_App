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
import android.graphics.Bitmap;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
<<<<<<< HEAD
<<<<<<< HEAD
=======
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
>>>>>>> 2a6b28037db1541c8225c5513ea1ceca618f6bd2
=======
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
>>>>>>> origin/Camera-Pretty-Branch
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
<<<<<<< HEAD
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
	private GestureDetector gestureDetector;
<<<<<<< HEAD
    View.OnTouchListener gestureListener;
    File imagePath;
    File[] images;
    int iter = 0;
	
=======
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Spinner;
import edu.drake.pocketbotanist.contentprovider.*;
import edu.drake.pocketbotanist.database.*;


public class EntryScreen extends Activity implements MapDialogFragment.MapDialogListener, 
PhotoDialogFragment.PhotoDialogListener {
	private Uri itemUri;
	private EditText mSpecies;
	private EditText mID;
	private EditText mCommonName;
	private TextView mDate;
	private TextView mTime;
	private ImageView mPhoto;
	private TextView mLongitude;
	private TextView mLatitude;
	private MultiAutoCompleteTextView mPlantNotes;
	private MultiAutoCompleteTextView mHabitatNotes;
	private MultiAutoCompleteTextView mExtraNotes1;
	private MultiAutoCompleteTextView mExtraNotes2;
	private MultiAutoCompleteTextView mExtraNotes3;
	private MultiAutoCompleteTextView mExtraNotes4;
	private MultiAutoCompleteTextView mExtraNotes5;

>>>>>>> origin/Database-Branch
=======
    private View.OnTouchListener gestureListener;
    private File imagePath;
    private File[] images;
    private int iter = 0;
    private boolean picAdded = false;
	
>>>>>>> origin/Camera-Pretty-Branch
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry_screen);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


		mSpecies = (EditText) findViewById(R.id.editText1);
		mID = (EditText) findViewById(R.id.editText1);
		mCommonName = (EditText) findViewById(R.id.editText1);
		mDate = (TextView) findViewById(R.id.textView5);
		mTime = (TextView) findViewById(R.id.textView5);
		mPhoto = (ImageView) findViewById(R.id.image_preview);
		mLongitude = (TextView) findViewById(R.id.textView5);
		mLatitude = (TextView) findViewById(R.id.textView5);
		mPlantNotes = (MultiAutoCompleteTextView) findViewById(R.id.bigtext3);
		mHabitatNotes = (MultiAutoCompleteTextView) findViewById(R.id.bigtext3);
		mExtraNotes1 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext3);
		mExtraNotes2 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext3);
		mExtraNotes3 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext3);
		mExtraNotes4 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext3);
		mExtraNotes5 = (MultiAutoCompleteTextView) findViewById(R.id.bigtext3);
		
		Button saveButton = (Button) findViewById(R.id.button5);
		
<<<<<<< HEAD
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			ActionBar actionBar = getActionBar();
			actionBar.setTitle(extras.getString("passer"));
			fillData(extras.getString("passer"));
		}

		final TextView pButton = (TextView) findViewById(R.id.picButton);
<<<<<<< HEAD
=======
		saveButton.setOnClickListener(new View.OnClickListener() {
		      public void onClick(View view) {
		          saveState();
		    	  finish();
		      }

		    });

		final Button pButton = (Button) findViewById(R.id.picButton);
>>>>>>> origin/Database-Branch
=======
>>>>>>> origin/Camera-Pretty-Branch
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

		pic.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(View v){
				PhotoDialogFragment photoDialog = new PhotoDialogFragment();
				photoDialog.show(getFragmentManager(), "Photo Dialog");
				return true;
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

<<<<<<< HEAD
		final Button cancel = (Button) findViewById(R.id.cancelButton);
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		imagePath = new File(path+idnum.getText().toString());
		images = imagePath.listFiles();
		gestureDetector = new GestureDetector(this, new myGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        }; 
        pic.setOnTouchListener(gestureListener);
<<<<<<< HEAD
=======
		longi.setText("Click to Edit");
>>>>>>> origin/Database-Branch
=======
>>>>>>> origin/Camera-Pretty-Branch

		updatePref();

		Bundle extras = getIntent().getExtras();

		// Check from the saved Instance
		itemUri = (savedInstanceState == null) ? null : (Uri) savedInstanceState
				.getParcelable(MyEntryContentProvider.CONTENT_ITEM_TYPE);

		// Or passed from the other activity
		if (extras != null) {
			itemUri = extras
					.getParcelable(MyEntryContentProvider.CONTENT_ITEM_TYPE);

			fillData(itemUri);
		}	
	}
<<<<<<< HEAD
	
	class myGestureDetector extends SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        	images = imagePath.listFiles();
        	try {
                if (Math.abs(e1.getY() - e2.getY()) > 250)
                    return false;
                if(e1.getX() - e2.getX() > 60 && Math.abs(velocityX) > 200) {
                	//TODO Left Swipe
                	if(iter>0){
                		iter--;
                	}
                	else{
                		iter = images.length-1;
                	}
                	System.out.print("Iter:"+iter+" In subtraction");
                	//Toast.makeText(getBaseContext(), "Left Swipe", Toast.LENGTH_SHORT).show();
                }  else if (e2.getX() - e1.getX() > 60 && Math.abs(velocityX) > 200) {
                    //TODO Right Swipe
                	if(iter>images.length-1){
                		iter++;
                	}
                	else{
                		iter = 0;
                	}
                	System.out.print("Iter:"+iter+" In subtraction");
                	//Toast.makeText(getBaseContext(), "Right Swipe", Toast.LENGTH_SHORT).show();
                }
            	Uri pathUri = Uri.parse(images[iter].getAbsolutePath());
            	pic.setImageURI(pathUri);
            } catch (Exception e) {
                Log.v("Gesture Error", "Failed to read gesture");
            }
            return false;
        }
    }
	
	class myGestureDetector extends SimpleOnGestureListener {
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
                	System.out.print("Iter:"+iter+" In subtraction");
                	//Toast.makeText(getBaseContext(), "Left Swipe", Toast.LENGTH_SHORT).show();
                }  else if (e2.getX() - e1.getX() > 60 && Math.abs(velocityX) > 150) {
                    //TODO Right Swipe
                	if(iter > images.length-1 && iter >= 0){
                		iter++;
                	}
                	else{
                		iter = 0;
                	}
                	System.out.print("Iter:"+iter+" In subtraction");
                	//Toast.makeText(getBaseContext(), "Right Swipe", Toast.LENGTH_SHORT).show();
                }
            	Uri pathUri = Uri.parse(images[iter].getAbsolutePath());
            	pic.setImageURI(pathUri);
            	picAdded = true;
            } catch (Exception e) {
                Log.v("Gesture Error", "Failed to read gesture");
            }
            return false;
        }
    }
	/*
	 * Try and work this in
	 * public static Bitmap loadImageFromUrl(String url) {

        Bitmap bm;
        try {  

                URL aURL = new URL(url);  
                URLConnection conn = aURL.openConnection(); 

                conn.connect();  
                InputStream is = null;
                try
                {
                 is= conn.getInputStream();  
                }catch(IOException e)
                {
                     return null;
                }

                BufferedInputStream bis = new BufferedInputStream(is);  

                bm = BitmapFactory.decodeStream(bis);

                bis.close();  
                is.close();  

           } catch (IOException e) {  
            return null;
           }  

        return  Bitmap.createScaledBitmap(bm,100,100,true);


    }
	 */
	
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
			if(!picAdded) pic.setImageResource(R.drawable.plant1);
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
			if(!picAdded) pic.setImageResource(R.drawable.plant0);
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
			if(!picAdded) pic.setImageResource(R.drawable.plant2);
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
			if(!picAdded) pic.setImageResource(R.drawable.plant3);
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
=======
>>>>>>> origin/Database-Branch

	@Override
	public void onRestart(){
		super.onRestart();
		finish();
		startActivity(getIntent());
	}

	/*
	@Override
	protected void onPause() {
		super.onPause();
		saveState();
	}
*/
	private void fillData(Uri uri) {
		String temp1 = "";
		String temp2 = "";
		String[] projection = {EntryTable.COLUMN_SPECIES, EntryTable.COLUMN_CUSTOMID,
				EntryTable.COLUMN_COMMONNAME, EntryTable.COLUMN_DATE,
				EntryTable.COLUMN_TIME, EntryTable.COLUMN_PHOTOS,
				EntryTable.COLUMN_PHOTO, EntryTable.COLUMN_LONGITUDE,
				EntryTable.COLUMN_LATITUDE, EntryTable.COLUMN_PLANTNOTES,
				EntryTable.COLUMN_HABITATNOTES, EntryTable.COLUMN_EXTRANOTES1,
				EntryTable.COLUMN_EXTRANOTES2, EntryTable.COLUMN_EXTRANOTES3,
				EntryTable.COLUMN_EXTRANOTES4, EntryTable.COLUMN_EXTRANOTES5 };
		Cursor cursor = getContentResolver().query(uri, projection, null, null,
				null);
		if (cursor != null) {
			cursor.moveToFirst();

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
			temp1 = (cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_PHOTOS)));
			//mPhoto
			temp2 = (cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_PHOTO)));
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
		//String photos = .getText().toString();
		//String photo = .getText().toString();
		String photos = "lol";
		String photo = "placeholders!";
		String lon = mLongitude.getText().toString();
		String lat = mLatitude.getText().toString();
		String plantNotes = mPlantNotes.getText().toString();
		String habitatNotes = mHabitatNotes.getText().toString();
		String extraNotes1 = mExtraNotes1.getText().toString();
		String extraNotes2 = mExtraNotes2.getText().toString();
		String extraNotes3 = mExtraNotes3.getText().toString();
		String extraNotes4 = mExtraNotes4.getText().toString();
		String extraNotes5 = mExtraNotes5.getText().toString();
		
		 species = "placeholders!";
		 id = "placeholders!";
		 name = "placeholders!";
		 date = "placeholders!";
		 time = "placeholders!";
		//String photos = .getText().toString();
		//String photo = .getText().toString();
		 photos = "lol";
		 photo = "placeholders!";
		 lon = "placeholders!";
		 lat = "placeholders!";
		plantNotes = "placeholders!";
		habitatNotes ="placeholders!";
		 extraNotes1 = "placeholders!";
		 extraNotes2 = "placeholders!";
		 extraNotes3 ="placeholders!";
		 extraNotes4 = "placeholders!";
		 extraNotes5 = "placeholders!";
		
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
		
<<<<<<< HEAD
<<<<<<< HEAD
=======
		//TODO write method to fill picture
>>>>>>> 2a6b28037db1541c8225c5513ea1ceca618f6bd2
=======
		fillPic(file);
	}
	
	public void fillPic(File f){
		pic.setImageURI(null);
		Uri pathUri = Uri.parse(f.getAbsolutePath());
    	pic.setImageURI(pathUri);
    	picAdded = true;
>>>>>>> origin/Camera-Pretty-Branch
	}

	@Override
	public void onViewPicture(DialogFragment dialog) {
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
<<<<<<< HEAD
    
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
	
=======

>>>>>>> origin/Database-Branch
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
