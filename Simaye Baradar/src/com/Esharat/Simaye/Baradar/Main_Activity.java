package com.Esharat.Simaye.Baradar;

import java.util.ArrayList;

import com.Esharat.Menu.CustomMenu;
import com.Esharat.Menu.CustomMenu.OnMenuItemSelectedListener;
import com.Esharat.Menu.CustomMenuItem;
import com.Esharat.Widget.Title_Pages;
import com.Esharat.Widget.Title_Pages.TitleBarClickListener;
import com.Esharat.Widget.Title_Pages.TitleBarLongClickListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Main_Activity extends Activity implements 
														OnMenuItemSelectedListener,
														TitleBarClickListener{
	
	private SharedPreferences Pref;
	private String Str_Mode;
	private String font_iraniansans = "irsans.ttf";
	private Typeface tf_IRsans;
	
	private CustomMenu mMenu;
	private Title_Pages Title_Main;
	
	
	Dialog dialog;
	TextView BtnView;
	TextView BtnBookmark;
	TextView BtnDownload;
	TextView Lbl_Title;
	
	private LinearLayout MainNoNet;
	private TextView lbl_NoInternet;
	private Button Btn_Refresh, Btn_EXIT;
	
	private void Init(){
		Pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Str_Mode = Pref.getString("Font Mode", "");
		mMenu = new CustomMenu(this, this, getLayoutInflater());
        mMenu.setHideOnSelect(true);
        mMenu.setItemsPerLineInPortraitOrientation(5);
        mMenu.setItemsPerLineInLandscapeOrientation(6);
        loadMenuItems();
        Title_Main = (Title_Pages) findViewById(R.id.titlePages1);
        tf_IRsans = Typeface.createFromAsset(getAssets(), "font/" + font_iraniansans + "");
		
		dialog = new Dialog(Main_Activity.this);
		dialog.getWindow();
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.context_menu_item);
        dialog.setCancelable(true);
        
        Title_Main.Set_Download_Visible(false);
		Title_Main.Set_Mark_Visible(false);
		Title_Main.Set_Share_Visible(false);
		Title_Main.Set_App_Icon(R.drawable.ic_launcher);
		Title_Main.setOnItemCliclListener(this);
		Title_Main.Set_TypeFace(tf_IRsans);
		
		Title_Main.setOnItemLongCliclListener(new TitleBarLongClickListener() {

			public void LongClickEvent(int id) {
				if(id == R.id.Img_Icon)
					doMenu();
			}
		});
	}
	
	private void loadMenuItems() {
		ArrayList<CustomMenuItem> menuItems = new ArrayList<CustomMenuItem>();
		CustomMenuItem cmi;
		
		cmi = new CustomMenuItem();
		cmi.setCaption("œ—»«—Â „«");
		cmi.setImageResourceId(R.drawable.ic_menu_info_details);
		cmi.setId(1);
		menuItems.add(cmi);
		
		cmi = new CustomMenuItem();
		cmi.setCaption("œ«‰·ÊœÂ«");
		cmi.setImageResourceId(R.drawable.ic_menu_download);
		cmi.setId(2);
		menuItems.add(cmi);
		
		cmi = new CustomMenuItem();
		cmi.setCaption("«—”«· »«“ŒÊ—œ");
		cmi.setImageResourceId(R.drawable.ic_menu_send);
		cmi.setId(3);
		menuItems.add(cmi);
		
		cmi = new CustomMenuItem();
		cmi.setCaption("‰‘«‰ ‘œÂ Â«");
		cmi.setImageResourceId(R.drawable.ic_menu_star);
		cmi.setId(4);
		menuItems.add(cmi);
		
		
		if (!mMenu.isShowing())
		try {
			mMenu.setMenuItems(menuItems);
		} catch (Exception e) {
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Egads!");
			alert.setMessage(e.getMessage());
			alert.show();
		}
	}
	
	 private void doMenu() {
			if (mMenu.isShowing())
				mMenu.hide();
			else 
				mMenu.show(findViewById(R.id.listView1));
		}
	
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)  {
		if (keyCode == KeyEvent.KEYCODE_MENU) {
			if(!mMenu.isShowing()){
				doMenu();
			}
			else {
				mMenu.hide();
			}
	    }
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(mMenu.isShowing())
			{
				mMenu.hide();
				return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(mMenu.isShowing())
			mMenu.hide();
		return super.onTouchEvent(event);
	}
	
	@Override 
	public void onBackPressed() {
		if(mMenu.isShowing())
			mMenu.hide();
		else
			Main_Activity.this.finish();
	}
	
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Init();
	}



	public void MenuItemSelectedEvent(CustomMenuItem selection) {
		//LVIf = Manager.LVI1;
		mMenu.hide();
		switch(selection.getId()){
			case 1: 
				startActivity(new Intent(Main_Activity.this, AboutActivity.class));
				overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
				break;
				
			case 2:
				//startActivity(new Intent(Main_Activity.this, downloadActivity.class));
				overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
				break;
				
			case 3:
				startActivity(new Intent(Main_Activity.this,Send_Email.class));
				overridePendingTransition(R.anim.push_down_in, R.anim.push_down_out);
				break;
			case 4:
				//startActivity(new Intent(Main_Activity.this,BookMarkActivity.class));
				overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
				break;
		}
	}

	public void ClickEvent(int id) {
		
	}
}
