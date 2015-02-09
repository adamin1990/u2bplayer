package com.ijiaban.u2bplayer.dao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Xml;

import com.ijiaban.u2bplayer.bean.Category;
import com.ijiaban.u2bplayer.common.dao.XmlCommonDao;

/**
 * �������
 * @author Administrator
 *
 */
public class CategoryDao {
	/**
	 * ���ݲ�ѯ��ַ����ȡ���ļ���
	 * @param path  �����ַ
	 * @return      ��𼯺�
	 */
	public static List<Category> getCategoryByXml(String path) throws Exception{
		 
		List<Category> cates = new ArrayList<Category>();
		Category category = null;

		XmlPullParser xmlParser = XmlCommonDao.getXmlFromU2B(path);

		int evtType = xmlParser.getEventType();
		while(evtType!=XmlPullParser.END_DOCUMENT){
 			switch (evtType) {
			case XmlPullParser.START_TAG:
				String tag = xmlParser.getName(); 
				if(tag.equalsIgnoreCase(XmlCommonDao.CATEGORY)){
 
					category = new Category();
					category.setName(xmlParser.getAttributeValue(null, "label"));
					category.setSname(xmlParser.getAttributeValue(null, "term"));
				} 
				break;
			case XmlPullParser.END_TAG:
 
				if(xmlParser.getName().equalsIgnoreCase(XmlCommonDao.CATEGORY)&&category!=null){
 
					cates.add(category);
					category = null;
				}
				break; 
			default:
				break;
			}
			evtType = xmlParser.next();
		} 
		return cates;
	}
}
