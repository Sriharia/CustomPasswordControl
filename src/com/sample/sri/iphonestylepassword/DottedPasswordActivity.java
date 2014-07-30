package com.sample.sri.iphonestylepassword;

import com.sample.sri.iphonestyelpasswrod.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class DottedPasswordActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dotted_password_activity);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dotted_password, menu);
		findViewById(R.id.userName).requestFocus();
		findViewById(R.id.submit).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DottedPasswordLayout dpl = (DottedPasswordLayout) findViewById(R.id.dottedPassword);
				if (null != dpl.getText())
					Toast.makeText(getApplicationContext(),
							"Password is: " + dpl.getText().toString(),
							Toast.LENGTH_LONG).show();
				else
					Log.e("DPL", "pwd is null");
			}
		});
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
