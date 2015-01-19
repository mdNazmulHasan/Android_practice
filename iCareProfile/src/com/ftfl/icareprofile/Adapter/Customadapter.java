package com.ftfl.icareprofile.Adapter;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftfl.icareprofile.R;
import com.ftfl.icareprofile.model.DietModel;

public class Customadapter extends ArrayAdapter<DietModel> {
	private final Activity context;

	List<DietModel> dietModel;

	static class ViewHolder {
		public TextView name, time, menu, date;
		public ImageView image;
	}

	public Customadapter(Activity context, List<DietModel> dietModel) {
		super(context, R.layout.row_list_items, dietModel);
		this.context = context;
		this.dietModel = dietModel;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		// Get the data item for this position
		DietModel mDietModel = getItem(position);
		// Check if an existing view is being reused, otherwise inflate the view
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(
					R.layout.row_list_items, parent, false);
		}
		// Lookup view for data population
		TextView name = (TextView) convertView.findViewById(R.id.dietName);
		TextView menu = (TextView) convertView.findViewById(R.id.dietMenu);
		TextView time = (TextView) convertView.findViewById(R.id.dietTime);
		TextView date = (TextView) convertView.findViewById(R.id.dietDate);
		// Populate the data into the template view using the data object
		name.setText(mDietModel.getDietName());
		menu.setText(mDietModel.getDietMenu());
		time.setText(mDietModel.getDietTime());
		date.setText(mDietModel.getDietDate());

		// Return the completed view to render on screen
		return convertView;
	}

}
