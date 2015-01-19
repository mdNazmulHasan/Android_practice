package com.ftfl.icareprofile;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.ftfl.icareprofile.Adapter.Customadapter;
import com.ftfl.icareprofile.Adapter.dayCustomAdapter;
import com.ftfl.icareprofile.helper.DietTableHelper;
import com.ftfl.icareprofile.model.DietModel;

public class HomeActivity extends Activity {

	DietTableHelper mydb;
	dayCustomAdapter adapter;
	ListView mlist;
	ArrayList<DietModel> array_list = new ArrayList<DietModel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_homescreen);

		mlist = (ListView) findViewById(R.id.lvDate);

		mydb = new DietTableHelper(this);
		ArrayList<DietModel> array_list = mydb.getDietList(1);

		// setListAdapter(new ListAdapter(this, names, times, menus, date));

		adapter = new dayCustomAdapter(this, array_list);
		mlist.setAdapter(adapter);

		mlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(HomeActivity.this, DietListActivity.class);
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
		Intent i = new Intent(getApplicationContext(),
				CreateProfileActivity.class);
		startActivity(i);
	}

	private void ViewProfile() {
		Intent i = new Intent(HomeActivity.this, ViewProfileActivity.class);
		startActivity(i);
	}

	private void CreateDiet() {
		Intent i = new Intent(HomeActivity.this, CreateDietActivity.class);
		startActivity(i);
	}

	private void DietList() {
		Intent i = new Intent(HomeActivity.this, DietListActivity.class);
		startActivity(i);
	}
}
