package com.Esharat.Simaye.Baradar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends Activity {
	
	Button BtnBack;
	TextView Lbl_Text;

	private String font_iraniansans = "irsans.ttf";
	private Typeface tf_IRsans;
	private SharedPreferences Pref;
	private String Str_Mode;
	
	protected void Init(){
		
		tf_IRsans = Typeface.createFromAsset(getAssets(), "font/" + font_iraniansans + "");
		Pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Str_Mode = Pref.getString("Font Mode", "");
		
		//BtnBack = (Button) findViewById(R.id.Btn_About_Back);
		//Lbl_Text = (TextView) findViewById(R.id.Lbl_About_Text);
		
		//BtnBack.setOnClickListener(new OnClickListener() {
			
		//	public void onClick(View v) {
		//		AboutActivity.this.finish();
		//	}
		//});
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
		overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_layout);
		Init();
	}

}
