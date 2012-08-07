package com.Esharat.Menu;

import java.util.ArrayList;
import com.Esharat.Classes.PersianReshape;
import com.Esharat.Simaye.Baradar.R;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class CustomMenu {


	private ArrayList<CustomMenuItem> mMenuItems;
	private OnMenuItemSelectedListener mListener = null;
	private Context mContext = null;
	private LayoutInflater mLayoutInflater = null;
	private PopupWindow mPopupWindow = null;
	private boolean mIsShowing = false;
	private boolean mHideOnSelect = true;
	private int mRows = 0;
	private int mItemsPerLineInPortraitOrientation = 3;
	private int mItemsPerLineInLandscapeOrientation = 6;
	
	private String font_iraniansans = "irsans.ttf";
	private Typeface tf_IRsans;
	private SharedPreferences Pref;
	private String Str_Mode;
	

	public interface OnMenuItemSelectedListener {
		public void MenuItemSelectedEvent(CustomMenuItem selection);
	}
	
	public boolean isShowing() { return mIsShowing; }
	
	public void setHideOnSelect(boolean doHideOnSelect) { mHideOnSelect = doHideOnSelect; } 
	
	public void setItemsPerLineInPortraitOrientation(int count) { mItemsPerLineInPortraitOrientation = count; }
	
	public void setItemsPerLineInLandscapeOrientation(int count) { mItemsPerLineInLandscapeOrientation = count; }
	
	public synchronized void setMenuItems(ArrayList<CustomMenuItem> items) throws Exception {
		if (mIsShowing) {
			throw new Exception("Menu list may not be modified while menu is displayed.");
		}
		mMenuItems = items;
	}
	
	public CustomMenu(Context context, OnMenuItemSelectedListener listener, LayoutInflater lo) {
		mListener = listener;
		mMenuItems = new ArrayList<CustomMenuItem>();
		mContext = context;
		mLayoutInflater = lo;
	}
	
	public synchronized void show(View v) {
		mIsShowing = true;
		boolean isLandscape = false;
		tf_IRsans = Typeface.createFromAsset(mContext.getAssets(), "font/" + font_iraniansans + "");
		Pref = PreferenceManager.getDefaultSharedPreferences(mContext.getApplicationContext());
		Str_Mode = Pref.getString("Font Mode", "");
		
		int itemCount = mMenuItems.size();
		if (itemCount<1) return; //no menu items to show
		if (mPopupWindow != null) return; //already showing
		Display display = ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		if (display.getWidth() > display.getHeight()) isLandscape = true;
		View mView= mLayoutInflater.inflate(R.layout.custom_menu, null);
		mPopupWindow = new PopupWindow(mView,LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT, false);
        mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        mPopupWindow.setWidth(display.getWidth());
        mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        
        int divisor = mItemsPerLineInPortraitOrientation;
        if (isLandscape) divisor = mItemsPerLineInLandscapeOrientation;
        int remainder = 0;
        if (itemCount < divisor) {
        	mRows = 1;
        	remainder = itemCount;
        } else {
        	mRows = (itemCount / divisor);
        	remainder = itemCount % divisor;
        	if (remainder != 0) mRows++;
        }
        TableLayout table = (TableLayout)mView.findViewById(R.id.custom_menu_table);
        table.removeAllViews();
        for (int i=0; i < mRows; i++) {
        	TableRow row = null;
    		TextView tv = null;
    		ImageView iv = null;
    		row = new TableRow(mContext);
    		row.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
    		for (int j=0; j< divisor; j++) {
    			if (i*divisor+j >= itemCount) break;
    			final CustomMenuItem cmi = mMenuItems.get(i*divisor+j);
    			View itemLayout = mLayoutInflater.inflate(R.layout.custom_menu_item, null);
    			tv = (TextView)itemLayout.findViewById(R.id.custom_menu_item_caption);
    			tv.setTypeface(tf_IRsans);
    			if(Str_Mode.equals("Mode 1"))
    				tv.setText(cmi.getCaption());
    			else if (Str_Mode.equals("Mode 2"))
    				tv.setText(PersianReshape.reshape(cmi.getCaption()));
    			iv = (ImageView)itemLayout.findViewById(R.id.custom_menu_item_icon);
    			iv.setImageResource(cmi.getImageResourceId());
    			itemLayout.setOnClickListener( new OnClickListener() {
				   public void onClick(View v) {
						mListener.MenuItemSelectedEvent(cmi);
						if (mHideOnSelect) hide();
				   }
				});
    			row.addView(itemLayout);
    		}
    		table.addView(row);
        }
	}
	
	public synchronized void hide() {
		mIsShowing = false;
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
			mPopupWindow = null;
		}
		return;
	}
}
