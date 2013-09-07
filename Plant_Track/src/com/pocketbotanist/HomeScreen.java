package com.pocketbotanist;

import java.io.File;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import com.pocketbotanist.contentprovider.MyEntryContentProvider;
import com.pocketbotanist.database.EntryTable;



public abstract class HomeScreen extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

	private static final int DELETE_ID = Menu.FIRST + 1;
	// private Cursor cursor;
	private SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		this.getListView().setDividerHeight(2);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		//Initial folder creation code
		File appDirectory = new File(Environment.getExternalStorageDirectory().toString()+"/Pocket Botanist/");
		if (!appDirectory.exists()){
			appDirectory.mkdir();
		}

		//Drop down list code
		Spinner spinner = (Spinner) findViewById(R.id.action_spinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.action_list, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);


		fillData();
		//View listItem=(View)((Menu) this.getListView()).getItem(0);
		registerForContextMenu(getListView());
		if (adapter.isEmpty())
			System.out.println("IT'S EMPTY");
		String[] projection = { EntryTable.COLUMN_CUSTOMID };
		long info =  adapter.getItemId(0);
		Uri uri = Uri.parse(MyEntryContentProvider.CONTENT_URI + "/"
				+ info);
		Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
		cursor.moveToFirst();
		System.out.println((cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_CUSTOMID))));
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
			String[] projection = { EntryTable.COLUMN_PHOTOS };
			AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
			.getMenuInfo();
			Uri uri = Uri.parse(MyEntryContentProvider.CONTENT_URI + "/"
					+ info.id);
			Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
			cursor.moveToFirst();
			File temp = new File(cursor.getString(cursor
					.getColumnIndexOrThrow(EntryTable.COLUMN_PHOTOS)));
			if(temp.exists()){
				File[] files = temp.listFiles();
				for(int i = 0; i < files.length; i++){
					files[i].delete();
				}
				temp.delete();
			}
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
		//SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
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
			entryScreen("New Entry");
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void entryScreen(String t){
		Intent intent = new Intent(this, EntryScreen.class);
		intent.putExtra("passer", t);
		startActivity(intent);
	}

	public void settCall(){
		Intent sett = new Intent(this,SettingsActivity.class);
		startActivity(sett);
	}

	public void map1_3(){
		
		Intent map13 = new Intent(this,Entrymap_1_3.class);
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

	//@Override
	//When we create the loader we're going to get the projection for the ID, customID, species name, and time columns
	//(insures that these exist within the database)
	//then we create our cursor loader which will be responsible for loading data from the database
	//using the projection and our content provider
	/* public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		String[] projection = { EntryTable.COLUMN_ID, EntryTable.COLUMN_CUSTOMID, EntryTable.COLUMN_SPECIES, EntryTable.COLUMN_TIME, EntryTable.COLUMN_PHOTOS, EntryTable.COLUMN_PHOTO };
		CursorLoader cursor = new CursorLoader(this,
				MyEntryContentProvider.CONTENT_URI, projection, null, null, null);
		if (cursor != null) {
			((Cursor) cursor).moveToFirst();
		}
		
		return cursor;
	} */

	private void fillData() {

		// Fields from the database (projection)
		String[] from = new String[] { EntryTable.COLUMN_CUSTOMID, EntryTable.COLUMN_SPECIES , EntryTable.COLUMN_TIME, EntryTable.COLUMN_PHOTO};
		// Fields on the UI to which we map
		int[] to = new int[] { R.id.customidlabel, R.id.namelabel, R.id.timelabel, R.id.imageView };

		getLoaderManager().initLoader(1, null, this);		//changed to 1
		adapter = new SimpleCursorAdapter(this, R.layout.list_row, null, from, to, 0);

		setListAdapter(adapter);
	}
	
	@Override
	  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    menu.add(0, DELETE_ID, 0, R.string.menu_delete);
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