package com.ftfl.icareprofile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.ftfl.icareprofile.helper.DietTableHelper;
import com.ftfl.icareprofile.model.DietModel;
import com.ftfl.icareprofile.model.Profile;
import com.google.gson.Gson;

public class CreateDietActivity extends Activity {
	private Spinner dietNameSprinner;
	private ArrayAdapter<CharSequence> adapter;
	private EditText menuDescriptionEditText;
	private TimePicker time;
	private Button cteateDiet;
	private DatePicker dpDiet;
	private CheckBox cbAlarm;
	private int profileId = 1;
	int mHour = 0;
	int mMinute = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icare_create_diet);
		initialize();
		setSpinner();
	}

	public void createDiet(View view) {

		int day = dpDiet.getDayOfMonth();
		int month = dpDiet.getMonth();
		int year = dpDiet.getYear();

		String dateOfDiet = day + "/" + (month + 1) + "/" + year;

		mHour = time.getCurrentHour();
		mMinute = time.getCurrentMinute();
		String dietMenu = menuDescriptionEditText.getText().toString();

		if (cbAlarm.isChecked()) {

			Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM);
			alarmIntent.putExtra(AlarmClock.EXTRA_MESSAGE, dietMenu);
			alarmIntent.putExtra(AlarmClock.EXTRA_HOUR, mHour);
			alarmIntent.putExtra(AlarmClock.EXTRA_MINUTES, mMinute);
			alarmIntent.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
			alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(alarmIntent);
		}

		String dietName = dietNameSprinner.getSelectedItem().toString();
		String dietTime = time.getCurrentHour() + ":" + time.getCurrentMinute();

		DietModel diet = new DietModel(dietName, dietTime, dietMenu, dateOfDiet);
		diet.setProfileId(profileId);
		DietTableHelper helper = new DietTableHelper(this);

		Toast.makeText(this,
				String.valueOf(helper.createDiet(diet, profileId)),
				Toast.LENGTH_SHORT).show();

	}

	public void initialize() {

		dietNameSprinner = (Spinner) findViewById(R.id.dietName);
		menuDescriptionEditText = (EditText) findViewById(R.id.menuEditText);
		dpDiet = (DatePicker) findViewById(R.id.dpDiet);
		time = (TimePicker) findViewById(R.id.time);
		cbAlarm = (CheckBox) findViewById(R.id.cbAlarm);
		cteateDiet = (Button) findViewById(R.id.CreateDiet);

	}

	public void setSpinner() {

		adapter = ArrayAdapter.createFromResource(this,
				R.array.dietNameSpinner, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		dietNameSprinner.setAdapter(adapter);
	}

}
