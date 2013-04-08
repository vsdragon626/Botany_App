package edu.drake.planttrack;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class MapDialogFragment extends DialogFragment{
	private String[] options = {"Drop Pin","Manual Input"};
	public interface MapDialogListener{
		public void onDrop(DialogFragment dialog);
		public void onManual(DialogFragment dialog);
	}
	
	MapDialogListener mListener;
	
	public void onAttach(Activity activity){
		super.onAttach(activity);
		try{
			mListener = (MapDialogListener) activity;
		} catch (ClassCastException e){
			throw new ClassCastException(activity.toString()+"must implement MapDialogListener");
		}
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle("How do you want to enter coordinates?");
	    builder.setItems(options, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               // The 'which' argument contains the index position
	               // of the selected item
	            	  if(which == 0){
	            		  mListener.onDrop(MapDialogFragment.this);
	            	  }
	            	  else{
	            		  mListener.onManual(MapDialogFragment.this);
	            	  }
	           }
	    });
	    return builder.create();
	}
}
