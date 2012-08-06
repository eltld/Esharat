package com.Esharat.Simaye.Baradar;

import com.Esharat.Classes.PersianReshape;
import com.Esharat.Widget.Title_Pages;
import com.Esharat.Widget.Title_Pages.TitleBarClickListener;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.Esharat.Classes.*;

public class Send_Email extends Activity {
	
	private String font_iraniansans = "irsans.ttf";
	private Typeface tf_IRsans;
	private SharedPreferences Pref;
	private String Str_Mode;
	
	private ProgressDialog progressDialog;
	private ProgressBar progressBar;
	
	Animation shake;
	
	private Title_Pages Title_Main;
	
	TextView Lbl_Name;
	TextView Lbl_Email;
	TextView Lbl_Message;
	TextView Lbl_Desc;
	
	Button Btn_Send;
	
	EditText Txt_Name;
	EditText Txt_Email;
	EditText Txt_Message;
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
		overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
		
		return super.onKeyDown(keyCode, event);
	}

	
	private void getCustomToast(String message)
	 {
	     LayoutInflater li = getLayoutInflater();
	     View toastlayout =  li.inflate(R.layout.custom_toast, (ViewGroup)findViewById(R.id.customToastLayout));
	     TextView text = (TextView) toastlayout.findViewById(R.id.textView1);
	     text.setTypeface(tf_IRsans);
	     
	     if(Str_Mode.equals("Mode 1"))
	    	 text.setText(message);
	     else if (Str_Mode.equals("Mode 2"))
	    	 text.setText(PersianReshape.reshape(message));

	     Toast toast = new Toast(this);
	     toast.setDuration(Toast.LENGTH_LONG);
	     toast.setView(toastlayout);
	     toast.show();
	 }
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Title_Main.Set_Download_Visible(savedInstanceState.getBoolean("Title_Download"));
		Title_Main.Set_Mark_Visible(savedInstanceState.getBoolean("Title_Mark"));
		Title_Main.Set_Share_Visible(savedInstanceState.getBoolean("Title_Share"));
		
		Txt_Email.setText(PersianReshape.reshape(savedInstanceState.getString("TExt_Email")));
		Txt_Message.setText(PersianReshape.reshape(savedInstanceState.getString("TExt_Message")));
		Txt_Name.setText(PersianReshape.reshape(savedInstanceState.getString("TExt_Name")));
		
		
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean("Title_Download", Title_Main.Get_Download_Visible());
		outState.putBoolean("Title_Mark", Title_Main.Get_Mark_Visible());
		outState.putBoolean("Title_Share", Title_Main.Get_Share_Visible());
		
		outState.putString("TExt_Email", Txt_Email.getText().toString());
		outState.putString("TExt_Message", Txt_Message.getText().toString());
		outState.putString("TExt_Name", Txt_Name.getText().toString());
		
	}

	protected void Init(){
		shake = AnimationUtils.loadAnimation(this, R.anim.shake);
		tf_IRsans = Typeface.createFromAsset(getAssets(), "font/" + font_iraniansans + "");
		Pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		Str_Mode = Pref.getString("Font Mode", "");
		
		Title_Main = (Title_Pages) findViewById(R.id.titlePages1);
		
		Title_Main.Set_Download_Visible(false);
		Title_Main.Set_Mark_Visible(false);
		Title_Main.Set_Share_Visible(false);
		Title_Main.Set_TypeFace(tf_IRsans);
		Title_Main.Set_App_Icon(R.drawable.ic_launcher);
		Title_Main.setOnItemCliclListener(new TitleBarClickListener() {
			
			public void EventClicked(int id) {
				switch(id){
					case R.id.Img_Icon: finish(); break;
					case R.id.Img_Bookmark: break;
					case R.id.Img_Download: break;
					case R.id.Img_Share: break;
				}
			}
		});
		
		Lbl_Email     =  (TextView)  findViewById(R.id.Lbl_Email_Email);
		Lbl_Message   =  (TextView)  findViewById(R.id.Lbl_Message_Email);
		Lbl_Name      =  (TextView)  findViewById(R.id.Lbl_Name_Email);
		Lbl_Desc      =  (TextView)  findViewById(R.id.Lbl_Description_Email);
		
		Btn_Send      =  (Button)    findViewById(R.id.Btn_Send_Email);
		
		Txt_Email     =  (EditText)  findViewById(R.id.Txt_Email_Email);
		Txt_Message   =  (EditText)  findViewById(R.id.Txt_Message_Email);
		Txt_Name      =  (EditText)  findViewById(R.id.Txt_Name_Email);
	}
	
	protected void SetFace(){
		
		Txt_Email.setTypeface(tf_IRsans);
		Txt_Message.setTypeface(tf_IRsans);
		Txt_Name.setTypeface(tf_IRsans);
		Lbl_Email.setTypeface(tf_IRsans);
		Lbl_Message.setTypeface(tf_IRsans);
		Lbl_Name.setTypeface(tf_IRsans);
		Lbl_Desc.setTypeface(tf_IRsans);
		Btn_Send.setTypeface(tf_IRsans);
		
		if(Str_Mode.equals("Mode 1")){
			Lbl_Email.setText(Lbl_Email.getText().toString());
			Lbl_Message.setText(Lbl_Message.getText().toString());
			Lbl_Name.setText(Lbl_Name.getText().toString());
			Lbl_Desc.setText(Lbl_Desc.getText().toString());
			Btn_Send.setText(Btn_Send.getText().toString());
		}else if (Str_Mode.equals("Mode 2")){
			Lbl_Email.setText(PersianReshape.reshape(Lbl_Email.getText().toString()));
			Lbl_Message.setText(PersianReshape.reshape(Lbl_Message.getText().toString()));
			Lbl_Name.setText(PersianReshape.reshape(Lbl_Name.getText().toString()));
			Lbl_Desc.setText(PersianReshape.reshape(Lbl_Desc.getText().toString()));
			Btn_Send.setText(PersianReshape.reshape(Btn_Send.getText().toString()));
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_email);
		
		
		Init();
		SetFace();
		
		Btn_Send.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				String strName;
				String strEmail;
				String strMessage;
				StringBuilder sb = new StringBuilder();
				String strFinalMessage;
				
				strEmail = Txt_Email.getText().toString();
				strMessage = Txt_Message.getText().toString();
				strName = Txt_Name.getText().toString();
				
				if(strEmail.equals(""))
					Txt_Email.startAnimation(shake);
				if(strMessage.equals(""))
					Txt_Message.startAnimation(shake);
				if (strName.equals(""))
						Txt_Name.startAnimation(shake);
				if(!strEmail.equals("") && !strMessage.equals("") && !strName.equals("")){
					sb.append("مشخصات ارسال کننده به شرح زير می باشد : ");
					sb.append("\n");
					sb.append("نام و نام خانوادگی : " + strName);
					sb.append("\n");
					sb.append("پست الکترونيکی : " + strEmail);
					sb.append("\n\n\n");
					sb.append("متن پيام : ");
					sb.append("\n");
					sb.append(strMessage);
					
					strFinalMessage = sb.toString();

					new SendingEmail().execute(strFinalMessage);
				}
			}
		});
	}
	
	private void showLoading(){
		progressDialog = ProgressDialog.show(Send_Email.this, "", "");
		progressDialog.setContentView(R.layout.loadinglayout);
		progressBar = (ProgressBar) progressDialog.findViewById(R.id.Progress_Bar1);
		progressBar.setIndeterminateDrawable(getResources().getDrawable(R.drawable.animloading));
	}
	
	private void stopLoading() {		
		if(progressDialog.isShowing())
			progressDialog.dismiss();
	}
	
	public class SendingEmail extends AsyncTask<String, Void, Void> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			showLoading();
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			stopLoading();
			getCustomToast("پيام شما با موفقيت ارسال شد.   با تشکر");
		}

		@Override
		protected Void doInBackground(String... params) {
			
			try {   
                GMailSender sender = new GMailSender("mohammadrbestdl@gmail.com", "937935mrmh991");
                sender.sendMail("Email From Android App", params[0].toString(), "programmer.net2009@live.com", "programmer.net2009@live.com"); 
            } catch (Exception e) {   
               getCustomToast("متاسفنه پيام ارسال نشد، اطفا مججدا تلاش نماييد");   
            }
			return null;
		}
	}
}