package com.ftfl.icareprofile.helper;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ftfl.icareprofile.model.DietModel;

public class DietTableHelper extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "ICareProfile.db";
	public static final String DIET_TABLE_NAME = "diet";
	public static final String DIET_COLUMN_ID = "id";
	public static final String DIET_COLUMN_PROFILE_ID = "profileId";
	public static final String DIET_COLUMN_TIME = "time";
	public static final String DIET_COLUMN_NAME = "name";
	public static final String DIET_COLUMN_MENU = "menu";
	public static final String DIET_COLUMN_DATE = "date";

	public DietTableHelper(Context context) {
		super(context, DATABASE_NAME, null, 3);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("create table diet"
				+ "(id integer primary key,profileId integer, name text,time text,menu text,date text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS diet");
		onCreate(db);
	}

	public long createDiet(DietModel dietObj, int profile_id) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues contentValues = new ContentValues();
		contentValues.put("profileId", 1);
		contentValues.put(DIET_COLUMN_NAME, dietObj.getDietName());
		contentValues.put(DIET_COLUMN_TIME, dietObj.getDietTime());
		contentValues.put(DIET_COLUMN_MENU, dietObj.getDietMenu());
		contentValues.put(DIET_COLUMN_DATE, dietObj.getDietDate());

		return db.insert("diet", null, contentValues);

	}

	public ArrayList<DietModel> getDietList(int profile_id) {
		ArrayList<DietModel> array_list = new ArrayList<DietModel>();

		// hp = new HashMap();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor result = db.rawQuery("select * from diet", null);
		if (result.moveToFirst()) {
			do {

				int dietId = result.getInt(0);
				int profileId = result.getInt(1);
				String dietName = result.getString(2);
				String dietTime = result.getString(3);
				String dietMenu = result.getString(4);
				String dietDate = result.getString(5);

				DietModel dietList = new DietModel(dietName, dietTime,
						dietMenu, dietDate);
				dietList.setDietId(dietId);
				dietList.setProfileId(profile_id);
				array_list.add(dietList);
			} while (result.moveToNext());

		}
		return array_list;
	}

}
