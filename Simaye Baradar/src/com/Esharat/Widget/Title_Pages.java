package com.Esharat.Widget;

import com.Esharat.Classes.PersianReshape;
import com.Esharat.Simaye.Baradar.*;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Title_Pages extends RelativeLayout {
	
	
	private String font_iraniansans = "irsans.ttf";
	private Typeface tf_IRsans;
	private SharedPreferences Pref;
	private String Str_Mode;
	
	private RelativeLayout Title_View;
	private TitleBarClickListener mTitlebarClickListener;
	private TitleBarLongClickListener mTitleLong;
	private Context mContext;
	private LayoutInflater mInflater;
	private TextView mTitle;
	private ImageView mAppIcon;
	private ImageView mShare;
	private ImageView mDownload;
	private ImageView mMark;
	
	public Title_Pages(Context context) {
		super(context);
		this.mContext = context;
		tf_IRsans = Typeface.createFromAsset(context.getAssets(), "font/" + font_iraniansans + "");
		Pref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
		Init();
	}
	
	public Title_Pages(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.mContext = context;
		tf_IRsans = Typeface.createFromAsset(context.getAssets(), "font/" + font_iraniansans + "");
		Pref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
		Init();
	}
	
	public Title_Pages(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.mContext = context;
		tf_IRsans = Typeface.createFromAsset(context.getAssets(), "font/" + font_iraniansans + "");
		Pref = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
		Init();
	}
	
	//--------------------------------------------INIT-------------------------------
	
	public interface TitleBarClickListener{
		public void ClickEvent(int id);
	}
	
	public void setOnItemCliclListener(TitleBarClickListener mTitleClicked) {
		this.mTitlebarClickListener = mTitleClicked;
	}
	
	public interface TitleBarLongClickListener{
		public void LongClickEvent(int id);
	}
	
	public void setOnItemLongCliclListener(TitleBarLongClickListener mTitleClicked) {
		this.mTitleLong = mTitleClicked;
	}
	
	public void Init(){
	
		Str_Mode = Pref.getString("Font Mode", "");
		
		mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		Title_View = (RelativeLayout) mInflater.inflate(R.layout.title_bar_layout, null);
		addView(Title_View);
		
		mAppIcon    =   (ImageView) Title_View.findViewById(R.id.Img_Icon);
		mDownload   =   (ImageView) Title_View.findViewById(R.id.Img_Download);
		mMark       =   (ImageView) Title_View.findViewById(R.id.Img_Bookmark);
		mShare      =   (ImageView) Title_View.findViewById(R.id.Img_Share);
		mTitle      =   (TextView) Title_View.findViewById(R.id.Lbl_Title_Page);
		
		mTitle.setTypeface(tf_IRsans);
		
		if(Str_Mode.equals("Mode 1"))
	    	 mTitle.setText(mTitle.getText());
	     else if (Str_Mode.equals("Mode 2"))
	    	 mTitle.setText(PersianReshape.reshape(mTitle.getText().toString()));
		
		mAppIcon.setOnClickListener(new OnClickListener() {public void onClick(View v) {mTitlebarClickListener.ClickEvent(v.getId());}});
		
		mAppIcon.setOnLongClickListener(new OnLongClickListener() {public boolean onLongClick(View v) {mTitleLong.LongClickEvent(v.getId()); return false;}});
		
		mDownload.setOnClickListener(new OnClickListener() {public void onClick(View v) {mTitlebarClickListener.ClickEvent(v.getId());}});
		
		mMark.setOnClickListener(new OnClickListener() {public void onClick(View v) {mTitlebarClickListener.ClickEvent(v.getId());}});
		
		mShare.setOnClickListener(new OnClickListener() {public void onClick(View v) {mTitlebarClickListener.ClickEvent(v.getId());}});
		
	}
	
	public void Set_Title(int resid){
		mTitle.setText(resid);
	}
	
	public void Set_Title(String Str_Title){
		mTitle.setText(Str_Title);
	}
	
	public String Get_Title(){
		return mTitle.getText().toString();
	}
	
	public void Set_Mark_Visible(boolean Bln_Input){
		if(Bln_Input)
			mMark.setVisibility(VISIBLE);
		else
			mMark.setVisibility(INVISIBLE);
	}
	
	public boolean Get_Mark_Visible(){
		if(mMark.getVisibility()== VISIBLE)
			return true;
		else
			return false;
	}
	
	public void Set_TypeFace(Typeface Tf){
		mTitle.setTypeface(Tf);
	}
	
	public void Set_Download_Visible(boolean Bln_Input){
		if(Bln_Input)
			mDownload.setVisibility(VISIBLE);
		else
			mDownload.setVisibility(INVISIBLE);
	}
	
	public boolean Get_Download_Visible(){
		if(mDownload.getVisibility()== VISIBLE)
			return true;
		else
			return false;
	}
	
	public void Set_Share_Visible(boolean Bln_Input){
		if(Bln_Input)
			mShare.setVisibility(VISIBLE);
		else
			mShare.setVisibility(INVISIBLE);
	}
	
	public boolean Get_Share_Visible(){
		if(mShare.getVisibility()== VISIBLE)
			return true;
		else
			return false;
	}
	
	public void Set_App_Icon(int resId){
		mAppIcon.setImageResource(resId);
	}
	
	public void Set_Mark_Icon(int resId){
		mMark.setImageResource(resId);
	}

}
