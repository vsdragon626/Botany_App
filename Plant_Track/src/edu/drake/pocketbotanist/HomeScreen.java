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


public class HomeScreen extends ListActivity implements
LoaderManager.LoaderCallbacks<Cursor> {
	
		
	 private static final int ACTIVITY_CREATE = 0;
	  private static final int ACTIVITY_EDIT = 1;
	  private static final int DELETE_ID = Menu.FIRST + 1;
	
	 // private Cursor cursor;
	  private SimpleCursorAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		this.getListView().setDividerHeight(2);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		//Drop down list code
		Spinner spinner = (Spinner) findViewById(R.id.action_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.action_list, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		fillData();
		registerForContextMenu(getListView());
		
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
	

	@Override
	  public boolean onContextItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case DELETE_ID:
	      AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
	          .getMenuInfo();
	      Uri uri = Uri.parse(MyEntryContentProvider.CONTENT_URI + "/"
	          + info.id);
	      getContentResolver().delete(uri, null, null);
	      fillData();
	      return true;
	    }
	    return super.onContextItemSelected(item);
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
	
	
	  // Opens the second activity if an entry is clicked
	  @Override
	  protected void onListItemClick(ListView l, View v, int position, long id) {
	    super.onListItemClick(l, v, position, id);
	    Intent i = new Intent(this, EntryScreen.class);
	    Uri itemUri = Uri.parse(MyEntryContentProvider.CONTENT_URI + "/" + id);
	    i.putExtra(MyEntryContentProvider.CONTENT_ITEM_TYPE, itemUri);

	    startActivity(i);
	  }

	@Override
	//When we create the loader we're going to get the projection for the ID, customID, species name, and time columns
	//(insures that these exist within the database)
	//then we create our cursor loader which will be responsible for loading data from the database
	//using the projection and our content provider
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		String[] projection = { EntryTable.COLUMN_ID, EntryTable.COLUMN_CUSTOMID, EntryTable.COLUMN_SPECIES, EntryTable.COLUMN_TIME };
	    CursorLoader cursorLoader = new CursorLoader(this,
	        MyEntryContentProvider.CONTENT_URI, projection, null, null, null);
	    return cursorLoader;
	}
	
	private void fillData() {

	    // Fields from the database (projection)
	    // Must include the _id column for the adapter to work
	    String[] from = new String[] { EntryTable.COLUMN_CUSTOMID, EntryTable.COLUMN_SPECIES , EntryTable.COLUMN_TIME };
	    // Fields on the UI to which we map
	    int[] to = new int[] { R.id.customidlabel, R.id.namelabel, R.id.timelabel };

	    getLoaderManager().initLoader(0, null, this);
	    adapter = new SimpleCursorAdapter(this, R.layout.list_row, null, from,
	        to, 0);

	    setListAdapter(adapter);
	  }


	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor data) {
		adapter.swapCursor(data);		
	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		adapter.swapCursor(null);
	}

}