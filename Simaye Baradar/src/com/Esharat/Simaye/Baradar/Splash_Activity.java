package com.Esharat.Simaye.Baradar;

import com.Esharat.Classes.PersianReshape;

import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;

public class Splash_Activity extends Activity implements OnClickListener{
	
	SharedPreferences Pref;
	Editor PrefEditor;
	
	private String font_IRsans = "irsans.ttf";
	private Typeface tf_IRsans;
	
	Dialog dialog;
	TextView BtnFontMod1;
	TextView BtnFontMod2;
	String strText = "«Ì‰ „ ‰ —« œ—”  „Ì»Ì‰„";
	
	ImageView imgSplash;
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
	@Override public void onBackPressed() {}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		tf_IRsans = Typeface.createFromAsset(getAssets(), "font/" + font_IRsans + "");
		imgSplash = (ImageView) findViewById(R.id.Img_Splash);
		imgSplash.setVisibility(View.INVISIBLE);
		
		dialog = new Dialog(Splash_Activity.this);
		dialog.getWindow();
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.menu_font_mode);
        BtnFontMod1 = (TextView) dialog.findViewById(R.id.Btn_Context_FontMode1);
        BtnFontMod2 = (TextView) dialog.findViewById(R.id.Btn_Context_FontMode2);
        BtnFontMod1.setTypeface(tf_IRsans);
        BtnFontMod2.setTypeface(tf_IRsans);
        BtnFontMod1.setText(strText);
        BtnFontMod2.setText(PersianReshape.reshape(strText));
        BtnFontMod1.setOnClickListener(this);
        BtnFontMod2.setOnClickListener(this);
        
        Pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        PrefEditor = Pref.edit();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		String strmod = Pref.getString("Font Mode", "");
		if(strmod.equals(""))
			dialog.show();
		else{
			ShowMain();
		}
	}
	
	private void ShowMain(){
		imgSplash.setVisibility(View.VISIBLE);
		new Handler().postDelayed(new Thread(){
			@Override
			public void run(){
				startActivity(new Intent(Splash_Activity.this, Main_Activity.class));
				finish();
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		}, 2000);
	}

	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.Btn_Context_FontMode1 :
				PrefEditor.putString("Font Mode", "Mode 1");
				PrefEditor.commit();
				break;
				
			case R.id.Btn_Context_FontMode2 :
				PrefEditor.putString("Font Mode", "Mode 2");
				PrefEditor.commit();
				break;

			default :
				break;
		}
		dialog.dismiss();
		ShowMain();
	}
}
