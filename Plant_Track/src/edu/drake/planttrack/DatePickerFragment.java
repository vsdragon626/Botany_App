package edu.drake.planttrack;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

	public TextView activity_edittext;

	public DatePickerFragment(EditText edit_text) {
		activity_edittext = edit_text;
	}

	public DatePickerFragment() {

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	@Override
	public void onDateSet(DatePicker view, int year, int month, int day) {
		String m = "";
		switch (month+1) {
		case 1:  
			m = "January";
			break;
		case 2:  
			m = "February";
			break;
		case 3:  
			m = "March";
			break;
		case 4:  
			m = "April";
			break;
		case 5:  
			m = "May";
			break;
		case 6:  
			m = "June";
			break;
		case 7:  
			m = "July";
			break;
		case 8:  
			m = "August";
			break;
		case 9:  
			m = "September";
			break;
		case 10: 
			m = "October";
			break;
		case 11: 
			m = "November";
			break;
		case 12: 
			m = "December";
			break;
		default: 
			m = "Invalid month";
			break;
		}
		activity_edittext.setText(m + " " + String.valueOf(day) + ", " + String.valueOf(year));
	}
}
