package com.nazmul.Database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.nazmul.util.ImageModel;

public class DataSource {

	// Database fields
	private SQLiteDatabase mImageDatabase;
	private SQLiteHelper mImageDbHelper;

	ImageModel mSingleImage;
	List<ImageModel> mImages = new ArrayList<ImageModel>();

	public DataSource(Context context) {
		mImageDbHelper = new SQLiteHelper(context);
	}

	/*
	 * open a method for writable database
	 */
	public void open() throws SQLException {
		mImageDatabase = mImageDbHelper.getWritableDatabase();
	}

	/*
	 * close database connection
	 */
	public void close() {
		mImageDbHelper.close();
	}

	/*
	 * insert data into the database.
	 */

	public boolean insert(ImageModel imageGalery) {

		this.open();

		ContentValues cv = new ContentValues();

		cv.put(SQLiteHelper.COL_IMAGE_DATE, imageGalery.getmDate());
		cv.put(SQLiteHelper.COL_IMAGE_TIME, imageGalery.getmTime());
		cv.put(SQLiteHelper.COL_IMAGE_LATITUDE,
				imageGalery.getmLatitude());
		cv.put(SQLiteHelper.COL_IMAGE_LONGITUDE,
				imageGalery.getmLongitude());
		cv.put(SQLiteHelper.COL_IMAGE_DESCRIPTION,
				imageGalery.getmRemarks());
		cv.put(SQLiteHelper.COL_IMAGE, imageGalery.getmImage());

		Long check = mImageDatabase.insert(SQLiteHelper.TABLE_IMAGE,
				null, cv);
		mImageDatabase.close();

		this.close();

		if (check < 0)
			return false;
		else
			return true;
	}

	// Updating database by id
	public boolean updateData(long eActivityId, ImageModel imageGalery) {
		this.open();
		ContentValues cvUpdate = new ContentValues();

		cvUpdate.put(SQLiteHelper.COL_IMAGE_DATE, imageGalery.getmDate());
		cvUpdate.put(SQLiteHelper.COL_IMAGE_TIME, imageGalery.getmTime());
		cvUpdate.put(SQLiteHelper.COL_IMAGE_LATITUDE,
				imageGalery.getmLatitude());
		cvUpdate.put(SQLiteHelper.COL_IMAGE_LONGITUDE,
				imageGalery.getmLongitude());
		cvUpdate.put(SQLiteHelper.COL_IMAGE_DESCRIPTION,
				imageGalery.getmRemarks());
		cvUpdate.put(SQLiteHelper.COL_IMAGE, imageGalery.getmImage());

		int check = mImageDatabase.update(SQLiteHelper.TABLE_IMAGE,
				cvUpdate, SQLiteHelper.COL_IMAGE_ID + "=" + eActivityId,
				null);
		mImageDatabase.close();

		this.close();
		if (check == 0)
			return false;
		else
			return true;
	}

	// delete data form database.
	public boolean deleteData(long eActivityId) {
		this.open();
		try {
			mImageDatabase.delete(SQLiteHelper.TABLE_IMAGE,
					SQLiteHelper.COL_IMAGE_ID + "=" + eActivityId, null);
		} catch (Exception ex) {
			Log.e("ERROR", "data insertion problem");
			return false;
		}
		this.close();
		return true;
	}

	/*
	 * using cursor for display data from database.
	 */
	public ImageModel singleGalary(String id) {
		this.open();

		Cursor mCursor = mImageDatabase.query(SQLiteHelper.TABLE_IMAGE,
				new String[] { SQLiteHelper.COL_IMAGE_ID,
						SQLiteHelper.COL_IMAGE_DATE,
						SQLiteHelper.COL_IMAGE_TIME,
						SQLiteHelper.COL_IMAGE_LATITUDE,
						SQLiteHelper.COL_IMAGE_LONGITUDE,
						SQLiteHelper.COL_IMAGE_DESCRIPTION,
						SQLiteHelper.COL_IMAGE,

				}, SQLiteHelper.COL_IMAGE_ID + "= '" + id + "' ", null,
				null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mActivityId = mCursor.getString(mCursor
							.getColumnIndex("image_id"));
					String mActivityDate = mCursor.getString(mCursor
							.getColumnIndex("date"));
					String mActivityTime = mCursor.getString(mCursor
							.getColumnIndex("time"));
					String mActivityLatitude = mCursor.getString(mCursor
							.getColumnIndex("latitude"));
					String mActivityLongitude = mCursor.getString(mCursor
							.getColumnIndex("longitude"));
					String mActivityDescription = mCursor.getString(mCursor
							.getColumnIndex("description"));
					byte[] mImage = mCursor.getBlob(mCursor
							.getColumnIndex("image"));

					mSingleImage = new ImageModel(mActivityId, mActivityDate,
							mActivityTime, mActivityLatitude,
							mActivityLongitude, mActivityDescription, mImage);

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return mSingleImage;
	}

	/*
	 * create a Diet chart of ICareProfile. Here the data of the database
	 * according to the given id is set to the Diet chart and return a Diet
	 * chart object.
	 */

	public ImageModel updateActivityData(long eActivityId) {
		this.open();
		ImageModel UpdateActivity;

		Cursor mUpdateCursor = mImageDatabase.query(
				SQLiteHelper.TABLE_IMAGE, new String[] {
						SQLiteHelper.COL_IMAGE_ID,
						SQLiteHelper.COL_IMAGE_DATE,
						SQLiteHelper.COL_IMAGE_TIME,
						SQLiteHelper.COL_IMAGE_LATITUDE,
						SQLiteHelper.COL_IMAGE_LONGITUDE,
						SQLiteHelper.COL_IMAGE_DESCRIPTION,
						SQLiteHelper.COL_IMAGE,

				}, SQLiteHelper.COL_IMAGE_ID + "= '" + eActivityId + "' ",
				null, null, null, null);

		mUpdateCursor.moveToFirst();

		String mActivityId = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("image_id"));
		String mActivityDate = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("date"));
		String mActivityTime = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("time"));
		String mActivityLatitude = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("latitude"));
		String mActivityLongitude = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("longitude"));
		String mActivityDescription = mUpdateCursor.getString(mUpdateCursor
				.getColumnIndex("description"));
		byte[] mImage = mUpdateCursor.getBlob(mUpdateCursor
				.getColumnIndex("image"));


		mUpdateCursor.close();
		UpdateActivity = new ImageModel(mActivityId, mActivityDate,
				mActivityTime, mActivityLatitude,
				mActivityLongitude, mActivityDescription, mImage);

		this.close();
		return UpdateActivity;
	}

	/*
	 * using cursor for display data from database.
	 */
	public List<ImageModel> allImages() {
		this.open();

		Cursor mCursor = mImageDatabase.query(SQLiteHelper.TABLE_IMAGE,
				new String[] { SQLiteHelper.COL_IMAGE_ID,
						SQLiteHelper.COL_IMAGE_DATE,
						SQLiteHelper.COL_IMAGE_TIME,
						SQLiteHelper.COL_IMAGE_LATITUDE,
						SQLiteHelper.COL_IMAGE_LONGITUDE,
						SQLiteHelper.COL_IMAGE_DESCRIPTION,
						SQLiteHelper.COL_IMAGE,

				}, null, null, null, null, null);

		if (mCursor != null) {
			if (mCursor.moveToFirst()) {

				do {

					String mActivityId = mCursor.getString(mCursor
							.getColumnIndex("image_id"));
					String mActivityDate = mCursor.getString(mCursor
							.getColumnIndex("date"));
					String mActivityTime = mCursor.getString(mCursor
							.getColumnIndex("time"));
					String mActivityLatitude = mCursor.getString(mCursor
							.getColumnIndex("latitude"));
					String mActivityLongitude = mCursor.getString(mCursor
							.getColumnIndex("longitude"));
					String mActivityDescription = mCursor.getString(mCursor
							.getColumnIndex("description"));
					byte[] mImage = mCursor.getBlob(mCursor
							.getColumnIndex("image"));

					mImages.add(new ImageModel(mActivityId, mActivityDate,
							mActivityTime, mActivityLatitude,
							mActivityLongitude, mActivityDescription, mImage));

				} while (mCursor.moveToNext());
			}
			mCursor.close();
		}
		this.close();
		return mImages;
	}

}
