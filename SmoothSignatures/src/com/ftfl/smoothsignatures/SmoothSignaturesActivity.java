package com.ftfl.smoothsignatures;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class SmoothSignaturesActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(new SignatureView(getApplicationContext(),null));
		setContentView(new RenderPath(getApplicationContext(),null));
		
		
	}

}