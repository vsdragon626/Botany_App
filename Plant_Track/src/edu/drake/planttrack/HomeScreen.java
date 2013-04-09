package edu.drake.planttrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cs.planttrack.R;

public class HomeScreen extends Activity {
	
	ListView listView;
	private final String[] values = new String[] {"ST2-225","ST3-212","ST3-123","ST4-145"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
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

}
//Dropdown menu code
//ActionBar actionBar = getActionBar();
//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.action_list,
//          android.R.layout.simple_spinner_dropdown_item);
//OnNavigationListener mOnNavigationListener;
//mOnNavigationListener = new OnNavigationListener() {
//	  // Get the same strings provided for the drop-down's ArrayAdapter
//	  //String[] strings = getResources().getStringArray(R.array.action_list);
//	  @Override
//	  public boolean onNavigationItemSelected(int position, long itemId) {
//	    // Create new fragment from our own Fragment class
//	    return true;
//	  }
//};
//actionBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);
