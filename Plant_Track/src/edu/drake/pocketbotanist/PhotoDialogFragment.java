package edu.drake.pocketbotanist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class PhotoDialogFragment extends DialogFragment{
	
	private String[] options = {"Take Picture","View Pictures"};
	public interface PhotoDialogListener{
		public void onTakePicture(DialogFragment dialog);
		public void onViewPicture(DialogFragment dialog);
	}
	
	PhotoDialogListener pListener;
	
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try{
			pListener = (PhotoDialogListener) activity;
		} catch (ClassCastException e){
			throw new ClassCastException(activity.toString()+"must implement PhotoDialogListener");
		}
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle("What would you like to do?");
	    builder.setItems(options, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               // The 'which' argument contains the index position
	               // of the selected item
	            	  if(which == 0){
	            		  pListener.onTakePicture(PhotoDialogFragment.this);
	            	  }
	            	  else{
	            		  pListener.onViewPicture(PhotoDialogFragment.this);
	            	  }
	           }
	    });
	    return builder.create();
	}

}
