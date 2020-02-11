package org.sunyulstercs.supportsmeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;

public class CalendarActivity extends AppCompatActivity {

	private static final String TAG = "CalendarActivity";


	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_layout);
		CalendarView mCalendarView = findViewById(R.id.calendarView);

		mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
			@Override
			public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
				String date = month + "/" + dayOfMonth + "/" + year;
				Log.d(TAG, "onSelectedDayChange: mm/dd/yyyy: " + date);

				Intent intent = new Intent(CalendarActivity.this, CalendarMain.class);
				intent.putExtra("date", date);
				startActivity(intent);
			}
		});
	}

}
