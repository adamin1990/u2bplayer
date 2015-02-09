package com.ijiaban.u2bplayer.common.dao.v3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ijiaban.u2bplayer.bean.v3.PageBean;

public class CommonDao {

	/**
	 * 获取json字符串
	 * @param path
	 *            地址
	 * @return json
	 * @throws Exception
	 *             网络连接错误
	 */
	public static String getDataFromServer(String path) throws Exception {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setAllowUserInteraction(true);
		conn.setAllowUserInteraction(true);
		conn.setConnectTimeout(5 * 1000);// 设置连接超时
		conn.setRequestMethod("GET");// 以get方式发起请求
		if (conn.getResponseCode() != 200)
			throw new RuntimeException("请求url失败");
		InputStream is = conn.getInputStream();// 得到网络返回的输入流
		String result = readData(is, "utf-8");
		conn.disconnect();
		return result;
	} 
	/**
	 * 根据输入流来获取字符串
	 * @param inSream 数据输入流
	 * @param charsetName 数据的编码格式
	 * @return 字符串
	 * @throws IOException IO异常
	 */
	public static String readData(InputStream inSream, String charsetName) throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inSream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		outStream.close();
		inSream.close();
		return new String(data, charsetName);
	}
	/**
	 * 把json字符串转换成JSON然后变为有序数组
	 * @param data   json字符串
	 * @return       List<Map<String, Object>>
	 * @throws 		 JSONException 
	 */
	@SuppressWarnings("unchecked")
	public static PageBean StringToPageBean(String data ) throws JSONException{
		
		JSONObject jsonObject  = new JSONObject(data);
		String prePageToken = "";
		String nextPageToken = "";
		if(jsonObject.has("nextPageToken")){
			nextPageToken = jsonObject.getString("nextPageToken");
		}
		if(jsonObject.has("prePageToken")){
			prePageToken = jsonObject.getString("prePageToken");
		} 
		int totalResults = 0;
		int resultsPerPage = 0;
		if(jsonObject.has("pageInfo")){
			JSONObject pageInfo = new JSONObject(jsonObject.getString("pageInfo"));
			totalResults = pageInfo.getInt("totalResults");
			resultsPerPage = pageInfo.getInt("resultsPerPage");
		} 
		JSONArray items = new JSONArray(jsonObject.getString("items")); 
		PageBean pageBean = new PageBean(totalResults, resultsPerPage, nextPageToken, prePageToken, items);
		return pageBean;
	}
	/**
	 * 注释
	 * @param data
	 * @return
	 * @throws JSONException
	 */
	public static List<String> StringtoArray(String data) throws JSONException{
		List<String> plistids=new ArrayList<String>();
		JSONObject jb=new JSONObject(data);
		JSONArray ja=new JSONArray(jb.getString("items"));
		for(int i=0;i<ja.length();i++){
			JSONObject jb2=ja.getJSONObject(i);
			if(jb2.has("contentDetails")){
				JSONObject jb3=new JSONObject(jb2.getString("contentDetails"));
				if(jb3.has("playlists")){
					JSONArray ja2=jb3.getJSONArray("playlists");
					String s=ja2.getString(0);
					plistids.add(s);
					
				}
				
			}
		}
		return plistids;
		
	}
}
