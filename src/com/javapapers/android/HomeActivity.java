package com.javapapers.android;



import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.GoogleMap;

public class HomeActivity extends Activity implements AdapterView.OnItemClickListener {

	String[] name = {"vikas sharma", "deepak agrawal", "kshitij rastogi", "shivam chaudhary", "aayush varshney", "ankesh mahshwari", "shubham tyagi", "nilendu das", "alisha singh", "shalini sahu", "saloni agrawal", "kunal srivastava", "himanshu jain","gagandeep singh","sumit bana"};
	AutoCompleteTextView textview;
	TextView t;

	private Button b = null;
	DatePicker dp = null;
	TimePicker tp =  null;
	Context c = null;
	TextView e = null;
	Button choose=null;
	Button opt = null;
	static boolean flag;
	Button subdnt;
	TextView datetime;
	Button subm;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		
		textview = (AutoCompleteTextView)findViewById(R.id.autonames);
		ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,android.R.layout.simple_dropdown_item_1line,name);
		textview.setThreshold(1);
		textview.setAdapter(adapter);
		t = (TextView)findViewById(R.id.txt);

		textview.setOnItemClickListener(this);

        subm = (Button) findViewById(R.id.submit);
        
		Spotme ob = Spotme.getInstance();
		c = this;
		datetime = (TextView) findViewById(R.id.dateNtime);
		b = (Button) findViewById(R.id.selectDate);
		datetime.setText("");
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("Deepak","It is coming ");

				final Dialog dialog = new Dialog(c);

				dialog.setContentView(R.layout.date_time_layout);

				dialog.setTitle("Schedule Hangup");

				dialog.show();
				subdnt = (Button) dialog.findViewById(R.id.submitDatenTime);
				tp = (TimePicker) dialog.findViewById(R.id.timePicker1);
				dp = (DatePicker) dialog.findViewById(R.id.datePicker1);
				
				if(subdnt!=null){

					subdnt.setOnClickListener(new OnClickListener() {

						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							 String strDateTime = dp.getYear() + "-" + (dp.getMonth() + 1) + "-" + dp.getDayOfMonth() + " "+ tp.getCurrentHour() + ":" + tp.getCurrentMinute();
							 Log.i("Deepak","checking");
							 datetime.setText(strDateTime);
							 dialog.dismiss();
							
						}
					});
				}

			}
		});

		choose = (Button) findViewById(R.id.chooseNow);
		choose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(c, Spotme.class);
				startActivityForResult(i, 1);
				flag = false;
			}
		});

		opt = (Button) findViewById(R.id.opt);
		opt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(c, Spotme.class);
				startActivityForResult(i, 1);
				flag = true;
			}
		});
		
		subm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(c);
				alertDialogBuilder.setMessage("Congratulations!!! Hangup has been scheduled....");
				alertDialogBuilder.show();
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		Log.i("Deepak","Coming to home");
		TextView v = (TextView) findViewById(R.id.locationSelected);
		Log.i("Deepak",Spotme.Placedatafinal);
		v.setText(Spotme.Placedatafinal);
		v.setVisibility(View.VISIBLE);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		String str = textview.getText().toString();
		String s = t.getText().toString();
		if (s.length() != 0) {
			s += ", ";
		}
		t.setText(s + str);
		textview.setText("");
	}
}
