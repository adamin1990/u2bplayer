package com.qd.gah.u2bplayer;

import com.fcukgfw.lt.R;
import com.ijiaban.youtubeplayer.ui.MainActiivty;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class InitActivity extends Activity {

	boolean isFirstIn = false;

	private static final int GO_HOME = 1000;
	private static final int GO_GUIDE = 1001;
	// �ӳ�3��
	private static final long SPLASH_DELAY_MILLIS = 2000;

	private static final String SHAREDPREFERENCES_NAME = "first_pref";
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_init); 
		if(isDeviceOnline()){
			init();
		}
		else 
		{
			AlertDialog.Builder builder = new Builder(InitActivity.this);
	   builder.setMessage("Please check the internet��");
	   builder.setTitle("net warning��");
	   builder.create().show();
		}
	
	} 
	private void init() {
		// ��ȡSharedPreferences����Ҫ������
		// ʹ��SharedPreferences����¼�����ʹ�ô���
		SharedPreferences preferences = getSharedPreferences(
				SHAREDPREFERENCES_NAME, MODE_PRIVATE);

		// ȡ����Ӧ��ֵ�����û�и�ֵ��˵����δд�룬��true��ΪĬ��ֵ
		isFirstIn = preferences.getBoolean("isFirstIn", true);

		// �жϳ�����ڼ������У�����ǵ�һ����������ת���������棬������ת��������
		if (!isFirstIn) {
			// ʹ��Handler��postDelayed������3���ִ����ת��MainActivity
			mHandler.sendEmptyMessageDelayed(GO_HOME, SPLASH_DELAY_MILLIS);
		} else {
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, SPLASH_DELAY_MILLIS);
		}
	}
	/**
	 * Handler:��ת����ͬ����
	 */
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case GO_HOME:
				goHome();
				break;
			case GO_GUIDE:
				goGuide();
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	private void goHome() {
 
		Intent intent = new Intent(InitActivity.this, MainActiivty.class);
 
		InitActivity.this.startActivity(intent);
		InitActivity.this.finish();
	} 
	private void goGuide() {
		Intent intent = new Intent(InitActivity.this, GuideActivity.class);
		InitActivity.this.startActivity(intent);
		InitActivity.this.finish();
	}
	private boolean isDeviceOnline() {
		 ConnectivityManager connMgr = (ConnectivityManager)this.
	                getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	        if (networkInfo != null && networkInfo.isConnected()) {
	            return true;
	        }
	        return false;
	}
}
