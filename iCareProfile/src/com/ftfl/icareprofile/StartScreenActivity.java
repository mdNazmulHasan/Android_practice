package com.ftfl.icareprofile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ftfl.icareprofile.model.Profile;

public class StartScreenActivity extends Activity {

	Button btnCreateProfile;
	Button btnViewProfile;
	Button btnAddDietChart;
	Button btnViewDietChart;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_icare_start_screen);
		btnCreateProfile = (Button) findViewById(R.id.btnCreateProfile);
		btnViewProfile = (Button) findViewById(R.id.btnViewProfile);
		btnAddDietChart = (Button) findViewById(R.id.btnAddDietChart);
		btnViewDietChart = (Button) findViewById(R.id.btnViewDietChart);

		btnCreateProfile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						CreateProfileActivity.class);
				startActivity(i);

			}
		});

		btnViewProfile.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						ViewProfileActivity.class);
				startActivity(i);

			}
		});

		btnAddDietChart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						CreateDietActivity.class);
				startActivity(i);

			}
		});

		btnViewDietChart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						DietListActivity.class);
				startActivity(i);

			}
		});

	}

}
