package com.ijiaban.youtubeplayer.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.fcukgfw.lt.R;
import com.ijiaban.fragment.MainFragment;
import com.ijiaban.fragment.OwnerListFragment;
import com.ijiaban.fragment.VideoCategoryFragment.OnGridSelectedListener;
import com.ijiaban.fragment.VideosFragment;

public class MainActiivty extends SherlockFragmentActivity implements MainFragment.OnArticleSelectedListener,OnGridSelectedListener{
    public static String LIST_ID=null;
//	private MyPagerAdapter adapter;
	thransid td ;
	
	private static final int CONTENT_VIEW_ID = 666;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
//		tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
//		pager = (ViewPager) findViewById(R.id.pager);
//		adapter = new MyPagerAdapter(getSupportFragmentManager());
//
//		pager.setAdapter(adapter);
//		final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
//				.getDisplayMetrics());
//		pager.setPageMargin(pageMargin);
//
//		tabs.setViewPager(pager);

		FrameLayout frame = new FrameLayout(this);
		frame.setId(CONTENT_VIEW_ID);
		setContentView(frame, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		if (arg0 == null) {
			setInitialFragment();
		}
//		getActionBar().setBackgroundDrawable(getWallpaper());
	}
	private void setInitialFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		fragmentTransaction.add(CONTENT_VIEW_ID, MainFragment.newInstance())
		.commit();
		
	}
	
	@Override
	public void onArticleSelected(String id) {
//             newInstance(id);
		OwnerListFragment own =new OwnerListFragment();
		own.use(id);    
//	   FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//
//       // �����fragment�滻�κ���fragment_container�еĶ���
//       // ���������back stack���Ա��û����Ի��˵�֮ǰ��״̬
//       transaction.replace(R.id.ownercontainer, own);
//       transaction.addToBackStack(null);
//
//       // �ύ����
//       transaction.commit();

	}
//    public static OwnerListFragment newInstance(String arg){
//        OwnerListFragment fragment = new OwnerListFragment();
//        Bundle bundle = new Bundle();
//        bundle.putString( "fuck", arg);
//        fragment.setArguments(bundle);
//         return fragment;
// }
public interface thransid{
	 public String onSelected(String id);
}
@Override
public void onGridSelected(long id) {
	VideosFragment v=new VideosFragment();
	v.use(id);
	
}
@Override
public boolean onKeyDown(int keyCode, KeyEvent event) {

	if (keyCode == KeyEvent.KEYCODE_BACK) {
		 Dialog alertDialog = new AlertDialog.Builder(this).
				    setTitle(this.getResources().getString(R.string.dear)).
				    setMessage(this.getResources().getString(R.string.notice)).
				    setIcon(R.drawable.ic_launcher2).
				    setPositiveButton(this.getResources().getString(R.string.rotate), new DialogInterface.OnClickListener() {
				     
				     @Override
				     public void onClick(DialogInterface dialog, int which) {

							String str = "market://details?id="
									+ getPackageName();
							Intent localIntent = new Intent(
									"android.intent.action.VIEW");
							localIntent.setData(Uri.parse(str));
							try {
								startActivity(localIntent);
							} catch (ActivityNotFoundException  e) {
								Intent intent = new Intent(Intent.ACTION_VIEW);	
								intent.setData(Uri.parse("https://play.google.com/store/apps/details?id="+getPackageName()));
				    			startActivity(Intent.createChooser(intent, getTitle()));  
							}
						
				     }
				    }).
				    setNegativeButton(this.getResources().getString(R.string.exit), new DialogInterface.OnClickListener() {
				     
				     @Override
				     public void onClick(DialogInterface dialog, int which) {
				    		dialog.dismiss();
							finish();
							System.exit(0);
				     }
				    }).
				    create();
				  alertDialog.show();

		return true;
	}
	return super.onKeyDown(keyCode, event);

}
}
