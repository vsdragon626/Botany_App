package edu.drake.planttrack;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class Entrymap_1_3 extends Activity {

	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
         // TODO Auto-generated method stub  
         super.onCreate(savedInstanceState);  
         setContentView(R.layout.activity_entrymap_1_3);  
    }  

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.entrymap_1_3, menu);
		return true;
	}
}