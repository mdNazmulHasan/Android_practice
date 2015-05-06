package com.nerdcastle.nazmulhasan.layouttry;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

public class MainActivity extends Activity {TableLayout mTlayout;
    TableRow tr;
    String[] mTextofButton = { "D", "E", "I", "J", "L", "M", "G", "R", "N",
            "T", "H", "P", "K", "Y", "V" };

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTlayout = (TableLayout) findViewById(R.id.mTlayout);

        int i = 0;
        while (i < 20) {
            if (i % 3 == 0) {
                tr = new TableRow(this);
                mTlayout.addView(tr);
            }
            Button btn = new Button(this);
            btn.setText("i "+i);
            btn.setId(i);
            btn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    System.out.println("v.getid is:- " + v.getId());
                }
            });
            tr.addView(btn);
            i++;
        }
    }
}