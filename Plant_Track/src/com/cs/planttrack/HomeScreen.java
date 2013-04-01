package com.cs.planttrack;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
	
	public void entryScreen(View v){
		Intent intent = new Intent(v.getContext(), EntryScreen.class);
		startActivity(intent);
	}

}
