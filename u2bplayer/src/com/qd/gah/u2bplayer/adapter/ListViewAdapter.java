package com.qd.gah.u2bplayer.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class ListViewAdapter extends BaseAdapter{

	public List<?> datas = new ArrayList<Object>(); 
	
	protected LayoutInflater      _inflater;
	/**
	 * ������
	 */
	public Context context;
	public ListViewAdapter(List<?> list,Context context) {
		// TODO ��ʼֵ
		this.datas = list; 
		this.context = context;
		 _inflater = LayoutInflater.from(context);
	} 
	@Override
	public int getCount() {
		// TODO ��ȡ����
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO  ��ȡĳ��item������
		return datas.get(position);
	} 
	@Override
	public long getItemId(int position) {
		// TODO ��ȡ��ʶ
		return setItemId(position);
	} 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO ��ʼ��ÿһ��item
		return setView(convertView, position, parent);
	}
	/**
	 * ��ʼ�� getView()
	 * @param convertView
	 * @param position
	 * @param parent
	 * @return
	 */
	public abstract View setView(View convertView, int position,ViewGroup parent);
	/**
	 * ��ȡitemid
	 * @param position
	 * @return
	 */
	public abstract long setItemId(int position );
	
	public void setDatas(List<?> datas) {
		this.datas = datas;
		notifyDataSetChanged();
	} 
	
	
}
