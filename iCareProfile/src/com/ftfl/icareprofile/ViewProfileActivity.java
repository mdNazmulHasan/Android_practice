package com.ftfl.icareprofile;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ftfl.icareprofile.model.Profile;
import com.google.gson.Gson;

public class ViewProfileActivity extends Activity {

	Profile pObj;
	SharedPreferences myPrefs;
	private TextView tvName;
	private TextView tvFatherName;
	private TextView tvMotherName;
	private TextView tvDateOfBirth;
	private TextView tvHeight;
	private TextView tvWeight;
	private TextView tvEyeColor;
	private TextView tvSpecialComment;
	private Button btnAddDailyDiet;
	private Button btnUpdateMyPref;
	ListView mlist;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_profile);

		// initialize
		myPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
		tvName = (TextView) findViewById(R.id.tvName);
		tvFatherName = (TextView) findViewById(R.id.tvFatherName);
		tvMotherName = (TextView) findViewById(R.id.tvMotherName);
		tvDateOfBirth = (TextView) findViewById(R.id.tvDateOfBirth);
		tvHeight = (TextView) findViewById(R.id.tvHeight);
		tvWeight = (TextView) findViewById(R.id.tvWeight);
		tvEyeColor = (TextView) findViewById(R.id.tvEyeColor);
		tvSpecialComment = (TextView) findViewById(R.id.tvSpecialComments);
		btnAddDailyDiet = (Button) findViewById(R.id.btnAddDailyDiet);
		btnUpdateMyPref = (Button) findViewById(R.id.btnUpdateMyPref);

		// getting data from shared preference
		Gson gson = new Gson();
		String json = myPrefs.getString("myProfile", "");
		pObj = gson.fromJson(json, Profile.class);

		// setting data to text view
		tvName.setText("Name: " + pObj.getName());
		tvFatherName.setText("Father's Name:    " + pObj.getFateherName());
		tvMotherName.setText("Mother's Name:    " + pObj.getMotherName());
		tvDateOfBirth.setText("Date of Birth:    " + pObj.getDateOfBirth());
		tvHeight.setText("Weight:    " + pObj.getWeight());
		tvWeight.setText("Height:    " + pObj.getHeight());
		tvEyeColor.setText("Eye Color:    " + pObj.getEyeColor());
		tvSpecialComment.setText("Note:    " + pObj.getSpecialComment());

		initCallbacks();

	}

	private void initCallbacks() {

		// go to adding Diet chart
		btnAddDailyDiet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(ViewProfileActivity.this,
						CreateDietActivity.class);
				startActivity(i);

			}
		});
		// update profile
		btnUpdateMyPref.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Editor prefsEditor = myPrefs.edit();
				Gson gson = new Gson();
				String json = gson.toJson(pObj);
				prefsEditor.putString("myProfile", json);
				prefsEditor.commit();

				Intent i = new Intent(ViewProfileActivity.this,
						CreateProfileActivity.class);
				startActivity(i);

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.action_createProfile:
			// search action
			CreateProfile();
			return true;
		case R.id.action_viewProfile:
			// location found
			ViewProfile();
			return true;
		case R.id.action_addDiet:
			// refresh
			CreateDiet();
			return true;
		case R.id.action_viewDiet:
			// help action
			DietList();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/**
	 * Launching new activity
	 * */
	private void CreateProfile() {
		// Intent intent = new
		// Intent(getApplicationContext(),com.ftfl.sqliteexample.DisplayContact.class);
		Intent i = new Intent(getApplicationContext(),
				CreateProfileActivity.class);
		startActivity(i);
	}

	private void ViewProfile() {
		Intent i = new Intent(ViewProfileActivity.this, HomeActivity.class);
		startActivity(i);
	}

	private void CreateDiet() {
		Intent i = new Intent(ViewProfileActivity.this,
				CreateDietActivity.class);
		startActivity(i);
	}

	private void DietList() {
		Intent i = new Intent(ViewProfileActivity.this, DietListActivity.class);
		startActivity(i);
	}

}
