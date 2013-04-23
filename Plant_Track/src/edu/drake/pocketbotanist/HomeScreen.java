package edu.drake.pocketbotanist;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class HomeScreen extends Activity {
	
	ListView listView;
	private final String[] values = new String[] {"ST2-225","ST3-212","ST3-123","ST4-145"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		//Inital folder creation code
		File appDirectory = new File(Environment.getExternalStorageDirectory().toString()+"/Pocket Botanist/");
		if (!appDirectory.exists()){
			appDirectory.mkdir();
		}
		
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
				entryScreenList(((TextView) view).getText().toString());
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
		intent.putExtra("passer", "New Entry");
		startActivity(intent);
	}
	
	public void entryScreenList(String t){
		Intent intent = new Intent(getBaseContext(), EntryScreen.class);
		intent.putExtra("passer", t);
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