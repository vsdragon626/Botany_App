package edu.drake.planttrack;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
	public TextView activity_edittext;

	public TimePickerFragment(EditText edit_text) {
		activity_edittext = edit_text;
	}

	public TimePickerFragment() {

	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

		// Create a new instance of DatePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute,DateFormat.is24HourFormat(getActivity()));

	}

	@Override
	public void onTimeSet(TimePicker view, int hour, int minute) {
		if(hour > 12){
			if(minute < 10){
				activity_edittext.setText(String.valueOf(hour-12) + " : 0" + String.valueOf(minute)+" PM");
			}
			else{
				activity_edittext.setText(String.valueOf(hour-12) + " : " + String.valueOf(minute) + " PM");
			}
		}
		else{
			if(minute < 10){
				activity_edittext.setText(String.valueOf(hour) + " : 0" + String.valueOf(minute) + " AM");
			}
			else{
				activity_edittext.setText(String.valueOf(hour) + " : " + String.valueOf(minute) + " AM");
			}
		}
	}
}
