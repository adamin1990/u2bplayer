package com.ijiaban.u2bplayer.dao;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class CommonDaov2 {
	/**
	 * <atom:category term='Film' label='��Ӱ�Ͷ���' xml:lang='zh-CN'><yt:assignable/>
	 * <yt:browsable regions='AE AR AT AU BA BE BG BH BR CA ...'/></atom:category>
	 */
	public static String CATEGORY = "category";
	public static String FEED ="feed";
	public static String ID ="id";
	public static String UPDATED ="updated";
	public static String TITLE = "title";
	public static String LINK ="link";
	public static String AUTHOR = "author";
	public static String TOTALRESULTS = "totalResults";
	public static String STARTINDEX = "startIndex";
	public static String ITEMSPERPAGE = "itemsPerPage";
	public static String ENTRY = "entry";
	public static String PUBLISHED = "published";
	public static String NAME = "name";
	public static String URI = "uri";
	public static String USERID = "userId";
	public static String COMMENTS = "comments";
	public static String FEEDLINK = "feedLink";
	public static String DESCRIPTION = "description";
	public static String THUMBNAIL = "thumbnail";
	public static String DURATION = "duration";
	public static String UPLOADED = "uploaded";
	public static String VIDEOID = "videoid";
	public static String RATING = "rating";
	public static String STATISTICS = "statistics"; 
	public static String CONTENT = "content";
	 
	/**
	 * ��ȡ����Ϣ
	 * @param path  ��Ϣ��ַ
	 * @return      ������
	 * @throws IOException 
	 * @throws XmlPullParserException 
	 * @throws Exception
	 */
	public static XmlPullParser getXmlFromU2B(String path) throws IOException, XmlPullParserException  {
		URL url = new URL(path);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setAllowUserInteraction(true);
		conn.setAllowUserInteraction(true);
		conn.setConnectTimeout(5 * 1000);// �������ӳ�ʱ
		conn.setRequestMethod("GET");// ��get��ʽ��������
		if (conn.getResponseCode() != 200)
			throw new RuntimeException("����urlʧ��");
		InputStream inputStream = conn.getInputStream();// �õ����緵�ص�������  
//		InputStream myStream = inputStream;
		XmlPullParser xmlParser = Xml.newPullParser();
		xmlParser.setInput(inputStream, "UTF-8"); 
//		inputStream.close();//������Ҫ��conn�Ͽ����ӣ�����ֱ�Ӱ���״̬�ı�
//		conn.disconnect(); 
		return xmlParser;
	}
	 
}
