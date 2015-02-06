package com.nazmul.camcord;

import java.lang.reflect.Field;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.nazmul.camcord.R;
import com.nazmul.adapter.Customadapter;
import com.nazmul.Database.DataSource;
import com.nazmul.Database.SQLiteHelper;
import com.nazmul.util.ImageModel;

public class LocationListViewActivity extends Activity {

	SQLiteHelper mDBHelper;
	DataSource mImageDataSource;
	ListView mListView;
	TextView mId_tv = null;
	TextView mLatitude_tv = null;
	TextView mLongitude_tv = null;
	String mId = "";
	String mLatitude = "";
	String mLongitude = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_image_list_view);
		getOverflowMenu();

		mDBHelper = new SQLiteHelper(this);
		mImageDataSource = new DataSource(this);
		List<ImageModel> hospital_list = mImageDataSource.allImages();
		Customadapter arrayAdapter = new Customadapter(this, hospital_list);
		mListView = (ListView) findViewById(R.id.lvImageList);
		mListView.setAdapter(arrayAdapter);
		
		
		final String[] option = new String[] { "View Data","Google Map", "Delete Data" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.select_dialog_item, option);
	
	}	

		
			
	public void editData(String eId)
	{
		Intent mEditIntent = new Intent(getApplicationContext(),
				InsertLocationInfoActivity.class);
		
		mEditIntent.putExtra("id",eId);
		startActivity(mEditIntent);
		
	}
	
	public void deleteData(String eId)
	{
		mImageDataSource = new DataSource(this);
		Long id = Long.parseLong(eId);
		mImageDataSource.deleteData(id);
		this.recreate();
	}

	public void detailsView(String eId) {
//		Intent mEditIntent = new Intent(getApplicationContext(),
//				SingleLocationViewActivity.class);
//		mEditIntent.putExtra("activityId", eId);
//		startActivity(mEditIntent);

	}

	

	public void defaultMap() {
		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setData(Uri.parse("geo:0,0?q="
				+ ("" + mLatitude + "," + mLongitude + "")));
		try {
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_List:
			startActivity(new Intent(getApplicationContext(),
					LocationListViewActivity.class));
			return true;
		case R.id.action_insertInfo:
			startActivity(new Intent(getApplicationContext(),
					InsertLocationInfoActivity.class));
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void getOverflowMenu() {

		try {
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			if (menuKeyField != null) {
				menuKeyField.setAccessible(true);
				menuKeyField.setBoolean(config, false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
