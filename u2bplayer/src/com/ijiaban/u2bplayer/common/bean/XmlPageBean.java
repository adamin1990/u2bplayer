package com.ijiaban.u2bplayer.common.bean;

import java.util.List;

public class XmlPageBean {

	
	public List<?> list;
	/**
	 * ����ʲô��pageBean
	 */
	public String title;
	public String id;
	/**
	 * �б����ʱ��
	 */
	public String updated;
	
	/**
	 * <link
        href="http://gdata.youtube.com/feeds/api/videos?q=skateboarding+dog&amp;start-index=21&amp;max-results=3&amp;v=2"
        rel="self"
        type="application/atom+xml" />

	 */
	public String link;//���β�ѯ��ַ
	
	/**
	 * ��һҳ��ȡ��ַ
	 */
	public String previous;
	/**
	 * ��һҳ��ȡ��ַ
	 */
	public String next;
	/**
	 * һ���ж��ٸ�
	 */
	public int totalResults ;
	
	public int itemsPerPage;
	
	
	
	
	public int getItemsPerPage() {
		return itemsPerPage;
	}
	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public String getPrevious() {
		return previous;
	}
	public void setPrevious(String previous) {
		this.previous = previous;
	}
	public String getNext() {
		return next;
	}
	public void setNext(String next) {
		this.next = next;
	}
	public int getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	} 
	
}
