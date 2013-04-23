package edu.drake.pocketbotanist;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.content.Intent;
import android.net.Uri;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SimpleCursorAdapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import edu.drake.pocketbotanist.contentprovider.MyEntryContentProvider;
import edu.drake.pocketbotanist.database.EntryTable;

import com.cs.pocketbotanist.R;

public class HomeScreen extends Activity implements
LoaderManager.LoaderCallbacks<Cursor> {
	
	ListView listView;
	private final String[] values = new String[] {"ST2-225","ST3-212","ST3-123","ST4-145"};
	
	 // private Cursor cursor;
	  private SimpleCursorAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		//Drop down list code
		Spinner spinner = (Spinner) findViewById(R.id.action_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.action_list, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		//created a simple listview, but need to change to take data in from a cursor.
		listView = (ListView) findViewById(R.id.mainlistview);
		updateList();
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				//TODO action taken on selection of row
				entryScreen();
			}
		});
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	
	private void updateList() {
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, values){
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View view =super.getView(position, convertView, parent);
				return view;
			}
		};
		
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_screen, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.menu_settings:
	        settCall();
	        return true;
	    case R.id.menu_map:
	    	map1_3();
	    	return true;
	    case R.id.menu_edit:
	    	entryScreen();
	    	return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	public void entryScreen(){
		Intent intent = new Intent(getBaseContext(), EntryScreen.class);
		startActivity(intent);
	}
	
	public void settCall(){
		Intent sett = new Intent(getBaseContext(),SettingsActivity.class);
        startActivity(sett);
	}
	
	public void map1_3(){
		Intent map13 = new Intent(getBaseContext(),Entrymap_1_3.class);
        startActivity(map13);
	}

	@Override
	//When we create the loader we're going to get the projection for the ID customID and common name columns
	//(insures that these exist within the database)
	//then we create our cursor loader which will be responsible for loading data from the database
	//using the projection and our content providor
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		String[] projection = { EntryTable.COLUMN_ID, EntryTable.COLUMN_CUSTOMID, EntryTable.COLUMN_COMMONNAME };
	    CursorLoader cursorLoader = new CursorLoader(this,
	        MyEntryContentProvider.CONTENT_URI, projection, null, null, null);
	    return cursorLoader;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		adapter.swapCursor(data);		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		adapter.swapCursor(null);
	}

}