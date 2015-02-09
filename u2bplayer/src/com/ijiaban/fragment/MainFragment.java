package com.ijiaban.fragment;

import java.util.ArrayList;
import java.util.List;

import android.R.color;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.auth.GooglePlayServicesAvailabilityException;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.ijiaban.actionbar.pulltorefreshlib.OnRefreshListener;
import com.ijiaban.navigationdrawer.SherlockActionBarDrawerToggle;
import com.ijiaban.tabslide.PagerSlidingTabStrip;
import com.ijiaban.u2bplayer.common.bean.OwnerPlists;
import com.ijiaban.u2bplayer.common.bean.SubItem;
import com.ijiaban.uitls.AbstractGetChannelList;
import com.ijiaban.uitls.AbstractGetNameTask;
import com.ijiaban.uitls.GetChannelListInForeground;
import com.ijiaban.uitls.GetNameInForeground;
import com.ijiaban.youtubeplayer.ui.MainActiivty;
import com.lt.test.Constants;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.qd.gah.u2bplayer.ChannelActivity;
import com.qd.gah.u2bplayer.InitActivity;
import com.fcukgfw.lt.R;
import com.qd.gah.u2bplayer.SearchActivity;

public class MainFragment extends SherlockFragment implements OnRefreshListener {
	 private static final String TAG = "MainFragment";
	 static String TITLE=null;
	 private AdView mAdView;

	 OnArticleSelectedListener mListener;
	 OnArticleSelectedListener mListener2;
//	 OnDingyueSelectedListener dingyueListener;
	private DrawerLayout mDrawerLayout; // ���
	private ListView listView; //�û������б�
	private ListView subListView; //�û������б�  
	private TextView mName; // �û���
	private ImageView mImage; // ͷ��
	private ScrollView left;
	private LinearLayout lin;
	private String mEmail;  //����
	public static String CHANNNEL_ID="UCpko_-a4wgz2u_DgDgd9fqA";
	public static final String EXTRA_ACCOUNTNAME = "extra_accountname";  //
	private ActionBarHelper mActionBar;
	private String SCOPE=Constants.OAUTH_SCOPE; // ���ʵķ�Χ
	 static final int REQUEST_CODE_PICK_ACCOUNT = 1000;
	    static final int REQUEST_CODE_RECOVER_FROM_AUTH_ERROR = 1001;
	    static final int REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR = 1002;
	public ImageLoader imageloader = ImageLoader.getInstance();
	DisplayImageOptions options = new DisplayImageOptions.Builder()
			.showStubImage(R.drawable.ic_launcher2) // ����ͼƬ�����ڼ���ʾ��ͼƬ
			.showImageForEmptyUri(R.drawable.ic_launcher2) // ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
			.showImageOnFail(R.drawable.ic_launcher2) // ����ͼƬ���ػ��������з���������ʾ��ͼƬ
			.cacheInMemory(true) // �������ص�ͼƬ�Ƿ񻺴����ڴ���
			.cacheOnDisc(true) // �������ص�ͼƬ�Ƿ񻺴���SD����
			.displayer(new RoundedBitmapDisplayer(10)) // ���ó�Բ��ͼƬ
			.build(); // �������ù���DisplayImageOption����
    private Handler handler;
    private TextView subChannelId; //�ҵĶ��ĵ�Ƶ��id
    private ProgressBar bar=null; //������ 
    private Button login; //��¼
    static String OWNER_LISTID=null;
    public List<String> taglist = new ArrayList<String>();
    
    
	
//	private static final String[] strs = new String[] { "first", "second",
//			"third", "fourth", "fifth" };// ����һ��String����������ʾListView������
	private SherlockActionBarDrawerToggle mDrawerToggle;
	/**
	 * viewpager���
	 */
	private PagerSlidingTabStrip tabs;
	private ViewPager pager;
	private MyPagerAdapter adapter;

	private Drawable oldBackground = null;
	private int currentColor = 0xFF666666;
	/**
	 * ʵ��fragment���ͨ�� 
	 * @author adamin
	 *
	 */
	public interface OnArticleSelectedListener {
        public void onArticleSelected(String id);
    }
//	/**
//	 * ����channelid��ʵ���� 
//	 * @author adamin
//	 *
//	 */
//    public interface OnDingyueSelectedListener {
//        public void onDingyueSelected(String id);
//    }
	public static Fragment newInstance() {
		Fragment f = new MainFragment();
		return f;
	}
	/**
	 * ͨ���������activityǿ������ת����ʵ����һ��OnArticleSelectedListener����
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		 try {
	            mListener = (OnArticleSelectedListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString() + " must implement OnArticleSelectedListener");
	        }
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_PICK_ACCOUNT) {
            if (resultCode == getActivity().RESULT_OK) {
                mEmail = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                getUsername();
            } else if (resultCode == getActivity().RESULT_CANCELED) {
          
            	Toast.makeText(getActivity(), "You must pick an account", Toast.LENGTH_SHORT).show();
            }
        } else if ((requestCode == REQUEST_CODE_RECOVER_FROM_AUTH_ERROR ||
                requestCode == REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR)
                && resultCode == getActivity().RESULT_OK) {
            handleAuthorizeResult(resultCode, data);
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    
	}

	private void handleAuthorizeResult(int resultCode, Intent data) {

        if (data == null) {
            show("Unknown error, click the button again");
            return;
        }
        if (resultCode == getActivity().RESULT_OK) {
            Log.i(TAG, "Retrying");
            getTask(this, mEmail, SCOPE).execute();
            return;
        }
        if (resultCode == getActivity().RESULT_CANCELED) {
            show("User rejected authorization.");
            return;
        }
        show("Unknown error, click the button again");
    		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.frame_main_layout, container,
				false);
		LinearLayout layout = (LinearLayout) view.findViewById(R.id.adlayoutmainfragment);
		
//		mDrawerToggle=new SherlockActionBarDrawerToggle(getActivity(), mDrawerLayout, R.drawable.ic_navigation_drawer, R.drawable.ic_navigation_drawer, R.drawable.ic_navigation_drawer);
//		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mAdView = new AdView(getActivity());
        mAdView.setAdUnitId(getResources().getString(R.string.ad_unit_id));
        mAdView.setAdSize(AdSize.BANNER);
//        mAdView.setAdListener(new ToastAdListener(this));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
        		LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layout.addView(mAdView, params);
        mAdView.loadAd(new AdRequest.Builder().build());
		 setRetainInstance(true);
		setHasOptionsMenu(true);
//		int titleId = Resources.getSystem().getIdentifier(
//				"action_bar_title", "id", "android");
//TextView fcuk = (TextView) view.findViewById(titleId);
//fcuk.setTextColor(getResources().getColor(R.color.holo_yellow_dark));
//fcuk.setTextSize(30);

		mDrawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
		left=(ScrollView)view.findViewById(R.id.left_drawer);
		listView = (ListView) left.findViewById(R.id.bofangliebiaolist);
		subListView=(ListView)left.findViewById(R.id.sublistview);
		login =(Button)left.findViewById(R.id.loginid);
		mName=(TextView)left.findViewById(R.id.message);
		mImage=(ImageView)left.findViewById(R.id.touxiang);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				 int statusCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity());
			        if (statusCode == ConnectionResult.SUCCESS) {
			            getUsername();
			           
			        } else if (GooglePlayServicesUtil.isUserRecoverableError(statusCode)) {
			            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(
			                    statusCode, getActivity(), 0 /* request code not used */);
			            dialog.show();
			        } else {
			            Toast.makeText(getActivity(), R.string.unrecoverable_error, Toast.LENGTH_SHORT).show();
			        }
			}
		});
		
	        handler=new Handler(){

				@Override
				public void handleMessage(Message msg) {
					if(msg.what==1){
						bar.setVisibility(View.VISIBLE);
				   }
				}
	        	
	        };

		/**
		 * tab viewpager�������
		 */
		tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
		// �»���ɫ
		tabs.setIndicatorColor(getResources().getColor(R.color.white));
		tabs.setBackgroundColor(getResources().getColor(R.color.tabsfcuk));
		tabs.setDividerColor(getResources().getColor(R.color.white));
		tabs.setTextColor(getResources().getColor(R.color.white));
		tabs.setTextSize(30);
		tabs.setUnderlineHeight(3);
		pager = (ViewPager) view.findViewById(R.id.apager);
		adapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
		pager.setAdapter(adapter);
		pager.getAdapter().notifyDataSetChanged();
		final int pageMargin = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
						.getDisplayMetrics());
		pager.setPageMargin(pageMargin);
		tabs.setViewPager(pager);
		/**
		 * drawer�������
		 */
		mDrawerLayout.setDrawerListener(new DemoDrawerListener());
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		mActionBar = createActionBarHelper();
		mActionBar.init();

		mDrawerToggle = new SherlockActionBarDrawerToggle(this.getActivity(),
				mDrawerLayout, R.drawable.ic_navigation_drawer2,
				R.string.drawer_open, R.string.drawer_close);
		mDrawerToggle.syncState();
		//��ʼ��imageloader
		imageloader.init(ImageLoaderConfiguration.createDefault(getActivity()));
		
		return view;

	}

	public void getUsername() {
	
			 if (mEmail == null) {
		            pickUserAccount();
		        } else {
		            if (isDeviceOnline()) {
		                getTask(this, mEmail, SCOPE).execute();
		                login.setVisibility(View.GONE);
		            } else {
		                Toast.makeText(getActivity(), "No network connection available", Toast.LENGTH_SHORT).show();
		            }
		        }
			
	}

	private boolean isDeviceOnline() {
		 ConnectivityManager connMgr = (ConnectivityManager)getActivity().
	                getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	        if (networkInfo != null && networkInfo.isConnected()) {
	            return true;
	        }
	        return false;
	}

	private void pickUserAccount() {
		  String[] accountTypes = new String[]{"com.google"};
	        Intent intent = AccountPicker.newChooseAccountIntent(null, null,
	                accountTypes, false, null, null, null, null);
	        startActivityForResult(intent, REQUEST_CODE_PICK_ACCOUNT);		
	}

	protected AbstractGetNameTask getTask(MainFragment mainFragment, String mEmail2,
			String sCOPE2) {
		// TODO Auto-generated method stub
		return new GetNameInForeground(mainFragment, mEmail2, sCOPE2);
	}
//	protected AbstractGetChannelList getChannelListTask(MainFragment mainfragment,String memail2,String scope2){
//		return  new GetChannelListInForeground(mainfragment, memail2, scope2);
//	}

	public void handleException(final Exception e) {
	        getActivity().runOnUiThread(new Runnable() {
	            @Override
	            public void run() {
	                if (e instanceof GooglePlayServicesAvailabilityException) {
	                    // The Google Play services APK is old, disabled, or not present.
	                    // Show a dialog created by Google Play services that allows
	                    // the user to update the APK
	                    int statusCode = ((GooglePlayServicesAvailabilityException)e)
	                            .getConnectionStatusCode();
	                    Dialog dialog = GooglePlayServicesUtil.getErrorDialog(statusCode,
	                            getActivity(),
	                            REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
	                    dialog.show();
	                } else if (e instanceof UserRecoverableAuthException) {
	                    // Unable to authenticate, such as when the user has not yet granted
	                    // the app access to the account, but the user can fix this.
	                    // Forward the user to an activity in Google Play services.
	                    Intent intent = ((UserRecoverableAuthException)e).getIntent();
	                    startActivityForResult(intent,
	                            REQUEST_CODE_RECOVER_FROM_PLAY_SERVICES_ERROR);
	                }
	            }
	        });
	    }
	/**
	 * ����ͷ�� 
	 * @param url ͷ���ַ 
	 */
	public void displayimage(final String url) {
	    
	getActivity().runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				

		    	imageloader.displayImage(url,mImage,options);
			}
		});

	}
	
	  /**
     * �Ҷ��ĵ�Ƶ��
     * @param lists
     */
    public void  listsubitemlist(final List<SubItem> lists){
   	   getActivity().runOnUiThread(new Runnable() {
   		   
   		
   		@Override
   		public void run() {
//   		     Message m=new Message();
   		     Message m2=new Message();
   		     m2.what=0;
//   		     m.what=1;
//   		     handler.sendMessage(m);
   			 subListView.setAdapter(new SUBListAdaper(lists,getActivity()));	
   			setListViewHeightBasedOnChildren(subListView);
   			 handler.sendMessage(m2);
   		}
   	
   	});
   	
   	   
   	   
      }
	 /**
     * �ҵ��б� 
     * @param lists
     */
   public void  listplaylist(final List<OwnerPlists> lists){
	   getActivity().runOnUiThread(new Runnable() {
		   
		
		@Override
		public void run() {
//		     Message m=new Message();
		     Message m2=new Message();
		     m2.what=0;
//		     m.what=1;
//		     handler.sendMessage(m);
			 listView.setAdapter(new OwnerListAdaper(lists,getActivity()));	
			 setListViewHeightBasedOnChildren(listView);
			 handler.sendMessage(m2);
		}

		
	
	});
	
	   
	   
   }
   /**
    * ����listview���ݵĶ��ٿ��Ƹ߶�
    * @param listView
    */
   private void setListViewHeightBasedOnChildren(ListView listView) {
	   ListAdapter listAdapter = listView.getAdapter();
	    if (listAdapter == null) {
	        // pre-condition
	        return;
	    }

	    int totalHeight = 0;
	    for (int i = 0; i < listAdapter.getCount(); i++) {
	        View listItem = listAdapter.getView(i, null, listView);
	        listItem.measure(0, 0);
	        totalHeight += listItem.getMeasuredHeight();
	    }

	    ViewGroup.LayoutParams params = listView.getLayoutParams();
	    params.height = totalHeight
	            + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	    listView.setLayoutParams(params);
	}
	/**
	 * ��ʾ�û��� 
	 * @param message  �û��� 
	 */
	  public void show(final String message) {
	        getActivity().runOnUiThread(new Runnable() {
	            @Override
	            public void run() {
	                mName.setText(message);
	            }
	        });
	  }
	@Override
	public void onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu,
			MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		/*
		 * The action bar home/up action should open or close the drawer.
		 * mDrawerToggle will take care of this.
		 */
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	private ActionBarHelper createActionBarHelper() {
		return new ActionBarHelper();
	}

	private class ActionBarHelper {
		private final ActionBar mActionBar;
		private CharSequence mDrawerTitle;
		private CharSequence mTitle;

		private ActionBarHelper() {
			mActionBar = ((SherlockFragmentActivity) getActivity())
					.getSupportActionBar();
		}

		public void init() {
			mActionBar.setDisplayHomeAsUpEnabled(true);
			mActionBar.setHomeButtonEnabled(true);
			mTitle = mDrawerTitle = getActivity().getTitle();
		}

		/**
		 * When the drawer is closed we restore the action bar state reflecting
		 * the specific contents in view.
		 */
		public void onDrawerClosed() {
//			mActionBar.setTitle(mTitle);
		}

		/**
		 * When the drawer is open we set the action bar to a generic title. The
		 * action bar should only contain data relevant at the top level of the
		 * nav hierarchy represented by the drawer, as the rest of your content
		 * will be dimmed down and non-interactive.
		 */
		public void onDrawerOpened() {
			mActionBar.setTitle(mDrawerTitle);
		}

		public void setTitle(CharSequence title) {
			mTitle = title;
		}
	}

	/**
	 * A drawer listener can be used to respond to drawer events such as
	 * becoming fully opened or closed. You should always prefer to perform
	 * expensive operations such as drastic relayout when no animation is
	 * currently in progress, either before or after the drawer animates.
	 * 
	 * When using ActionBarDrawerToggle, all DrawerLayout listener methods
	 * should be forwarded if the ActionBarDrawerToggle is not used as the
	 * DrawerLayout listener directly.
	 */
	private class DemoDrawerListener implements DrawerLayout.DrawerListener {
		
		@Override
		public void onDrawerOpened(View drawerView) {
			mDrawerToggle.onDrawerOpened(drawerView);
			mActionBar.onDrawerOpened();
		}

		@Override
		public void onDrawerClosed(View drawerView) {
			mDrawerToggle.onDrawerClosed(drawerView);
			mActionBar.onDrawerClosed();
		}

		@Override
		public void onDrawerSlide(View drawerView, float slideOffset) {
			mDrawerToggle.onDrawerSlide(drawerView, slideOffset);
		}

		@Override
		public void onDrawerStateChanged(int newState) {
			mDrawerToggle.onDrawerStateChanged(newState);
		}
	}

	@Override
	public void onRefreshStarted(View view) {
		// TODO Auto-generated method stub

	}

	/**
	 * viewpager������
	 * 
	 * @author adamin
	 * 
	 */
	public class MyPagerAdapter extends FragmentPagerAdapter {
	
		private  FragmentManager mFragmentManager;
	    private Fragment mFragmentAtPos0;

		private ArrayList<Fragment> fragmentList;
		TuiJFragment tj1 = new TuiJFragment();
		VideoCategoryFragment tj2 = new VideoCategoryFragment();
		OwnerlistKongbai tj3 = new OwnerlistKongbai();
//		DingYueFragment tj5 = new DingYueFragment();
//		TuiJFragment tj6 = new TuiJFragment();
//		TuiJFragment tj7 = new TuiJFragment();
//		TuiJFragment tj8 = new TuiJFragment();
//		SearchListFragment tj4 = new SearchListFragment();
		private final String[] TITLES = { "RECOMMEND", "CATEGORY", "PLAYLIST" };
		
		

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}



		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return POSITION_NONE;
		}
		


		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			// TODO Auto-generated method stub
			return super.instantiateItem(container, position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			super.destroyItem(container, position, object);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return TITLES[position];
		}

		@Override
		public int getCount() {
			return TITLES.length;
		}
		@SuppressWarnings("unchecked")
		@Override
		public Fragment getItem(int position) {
			
			fragmentList = new ArrayList<Fragment>();
			fragmentList.add(tj1);
			fragmentList.add(tj2);
			fragmentList.add(tj3);
//			fragmentList.add(tj5);
//			fragmentList.add(tj6);
//			fragmentList.add(tj7);
//			fragmentList.add(tj8);
			return fragmentList.get(position);
			// return SuperAwesomeCardFragment.newInstance(position);
		} 
	}
	   public class OwnerListAdaper extends BaseAdapter{
		   private List<OwnerPlists> list;
		   private Context context;
		//   private LayoutInflater mInflater;//�õ�һ��LayoutInfalter�����������벼�� /*���캯��*/ 
			


			
				@Override
				public int getCount() {
					return list.size();
				}

				public OwnerListAdaper(List<OwnerPlists> list, Context context) {
			super();
			this.list = list;
			this.context = context;
		}

				@Override
				public Object getItem(int arg0) {
					return list.get(arg0);
				}

				@Override
				public long getItemId(int arg0) {
					return arg0;
				}

				@Override
				public View getView(final int arg0, View convertView, ViewGroup arg2) {
					ViewHolder viewholder;
					
					if (convertView == null) {
		                convertView = getActivity().getLayoutInflater().inflate(R.layout.owner_list_item,
		null);
		                viewholder = new ViewHolder();
		                viewholder.thumbnail=(ImageView)convertView.findViewById(R.id.liebiaoimage);
		    			viewholder.title=(TextView)convertView.findViewById(R.id.liebiaotext);
		    			convertView.setTag(viewholder);
				}
					
					else{
						viewholder = (ViewHolder)convertView.getTag();//ȡ��ViewHolder����
					}
					viewholder.title.setText(list.get(arg0).getTitle());
					viewholder.title.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							mListener.onArticleSelected(list.get(arg0).getId());
							OWNER_LISTID=list.get(arg0).getId();
							TITLE=list.get(arg0).getTitle();
//							SharedPreferences sharedPreferences=getActivity().getSharedPreferences("listids", Context.MODE_PRIVATE);
//
//							Editor editor= sharedPreferences.edit();//��ȡ�༭��
//
//							editor.putString("listid",list.get(arg0).getId());
//
//
//							editor.commit();//�ύ�޸�
							mDrawerLayout.closeDrawer(left);
							pager.setCurrentItem(2);
							OwnerlistKongbai dingyue=new OwnerlistKongbai();
                            OwnerListFragment tj=new OwnerListFragment();
//                            Bundle bundle = new Bundle();
//                            bundle.putString("chinnelid",list.get(arg0).getResourceid().getChannelId());
//                            dingyue.setArguments(bundle);
                            FragmentManager fragmentManager = getFragmentManager();
                            //��ʼFragment����
                            FragmentTransaction fTransaction = fragmentManager.beginTransaction();
                            fTransaction.replace(R.id.ownerlist_kongbai, tj).commit();
					
						}
					});
					imageloader.displayImage(list.get(arg0).getThumbnails().default_url, viewholder.thumbnail, options);
					
					return convertView;
				
		    }
				/*��ſؼ�*/public final class ViewHolder{
				    public TextView title;
				    public ImageView thumbnail;
				    }

		    }
	   /**
	     * �Ҷ���Ƶ����������
	     * @author adamin
	     *
	     */
	    public class SUBListAdaper extends BaseAdapter{
	  	   private List<SubItem> list;
	  	   private Context context;
	  	//   private LayoutInflater mInflater;//�õ�һ��LayoutInfalter�����������벼�� /*���캯��*/ 
	  		


	  		
	  			@Override
	  			public int getCount() {
	  				return list.size();
	  			}

	  			public SUBListAdaper(List<SubItem> list, Context context) {
	  		super();
	  		this.list = list;
	  		this.context = context;
	  	}

	  			@Override
	  			public Object getItem(int arg0) {
	  				return list.get(arg0);
	  			}

	  			@Override
	  			public long getItemId(int arg0) {
	  				return arg0;
	  			}

	  			@Override
	  			public View getView(final int arg0, View convertView, ViewGroup arg2) {
	  				final ViewHolder viewholder;
	  				
	  				if (convertView == null) {
	  	                convertView = getActivity().getLayoutInflater().inflate(R.layout.owner_list_item,
	  	null);
	  	                viewholder = new ViewHolder();
	  	                viewholder.thumbnail=(ImageView)convertView.findViewById(R.id.liebiaoimage);
	  	    			viewholder.title=(TextView)convertView.findViewById(R.id.liebiaotext);
//	  	    			viewholder.channelId=(TextView)convertView.findViewById(R.id.subchannelid);
	  	    			convertView.setTag(viewholder);
	  			}
	  				
	  				else{
	  					viewholder = (ViewHolder)convertView.getTag();//ȡ��ViewHolder����
	  				}
	  				convertView.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							String s=list.get(arg0).getResourceid().getChannelId();
                            CHANNNEL_ID=s;
                            TITLE=list.get(arg0).getTitle();
							
							mDrawerLayout.closeDrawer(left);
							 Intent intent =new Intent();
							 intent.setClass(getSherlockActivity(), ChannelActivity.class);
							 startActivity(intent);							
						}
					});
	  				viewholder.title.setText(list.get(arg0).getTitle());
//	  				viewholder.channelId.setText(list.get(arg0).getResourceid().getChannelId());
//	  				viewholder.title.setOnClickListener(new OnClickListener() {
//						
//						@Override
//						public void onClick(View v) {
//							String s=list.get(arg0).getResourceid().getChannelId();
//                            CHANNNEL_ID=s;
//                            TITLE=list.get(arg0).getTitle();
//							
//							mDrawerLayout.closeDrawer(left);
//							 Intent intent =new Intent();
//							 intent.setClass(getSherlockActivity(), ChannelActivity.class);
//							 startActivity(intent);
//						}
//
//						private AbstractGetChannelList getChannelListTask(
//								MainFragment mainFragment, String mEmail,
//								String sCOPE) {
//							return new GetChannelListInForeground(mainFragment, mEmail, sCOPE);
//						}
//					});
//	  				viewholder.thumbnail.setOnClickListener(new OnClickListener() {
//						
//						@Override
//						public void onClick(View v) {
//							String s=list.get(arg0).getResourceid().getChannelId();
//                            CHANNNEL_ID=s;
//                            TITLE=list.get(arg0).getTitle();
//							
//							mDrawerLayout.closeDrawer(left);
//							 Intent intent =new Intent();
//							 intent.setClass(getSherlockActivity(), ChannelActivity.class);
//							 startActivity(intent);
//						}
//					});
				
	  				imageloader.displayImage(list.get(arg0).getThumbnails().default_url, viewholder.thumbnail, options);
	  				return convertView;
	  			
	  	    }
	  			/*��ſؼ�*/public final class ViewHolder{
	  			    public TextView title;
	  			    public ImageView thumbnail;
	  			    public TextView channelId;
	  			    }

	  	    }
}
