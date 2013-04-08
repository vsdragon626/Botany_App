package edu.drake.planttrack;

import edu.drake.planttrack.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class HomeScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
//		ActionBar actionBar = getActionBar();
//		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
//		SpinnerAdapter mSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.action_list,
//		          android.R.layout.simple_spinner_dropdown_item);
//		OnNavigationListener mOnNavigationListener;
//		mOnNavigationListener = new OnNavigationListener() {
//			  // Get the same strings provided for the drop-down's ArrayAdapter
//			  //String[] strings = getResources().getStringArray(R.array.action_list);
//			  @Override
//			  public boolean onNavigationItemSelected(int position, long itemId) {
//			    // Create new fragment from our own Fragment class
//			    return true;
//			  }
//		};
//		actionBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_home_screen, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    int itemId = item.getItemId();
		if (itemId == R.id.menu_settings) {
			settCall();
			return true;
		} else if (itemId == R.id.menu_map) {
			map1_3();
			return true;
		} else {
			return super.onOptionsItemSelected(item);
		}
	}
	public void entryScreen(View v){
		Intent intent = new Intent(v.getContext(), EntryScreen.class);
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
