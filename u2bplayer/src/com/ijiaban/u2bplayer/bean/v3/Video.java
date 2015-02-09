package com.ijiaban.u2bplayer.bean.v3;

import java.util.Date;
/**
 * ��Ƶ����ϸ������ id, snippet(��Ƶ�Ļ�����Ϣ), contentDetails��������Ƶ��ʽ��, fileDetails���޷�������, 
 * liveStreamingDetails���޷�������, player��������ַ�ȣ�, processingDetails���޷�������, 
 * recordingDetails, statistics��ͳ����Ϣ��, status��״̬��, 
 * suggestions���޷�������, and topicDetails�����������صĻ��⣩.
 * @author Administrator
 *
 */
public class Video {

	 public String id;  //id
	  public String kind;  //���� 
	  public Date publishedAt;  //�������� 
	  public String channelId;  //Ƶ��id
	  public String title;     //��Ƶ����
	  public String description;  //��Ƶ���� 
	  public String channelTitle;  //Ƶ������ 
	  public String categoryId;    //����id
	  public Thumbnails thumbnails;  //����ͼ 
	  
	  public Statistics statistics ; //ͳ������
	  
	  

	  public Video() {
		// TODO 
	  } 
	  public Video(String id,String kind,Date publishedAt,String channelId,String title,String description,String channelTitle,String categoryId,Thumbnails thumbnails) {
			// TODO ��ʼ��
		  this.id = id;
		  this.kind = kind;
		  this.channelId = channelId;
		  this.publishedAt = publishedAt;
		  this.title = title;
		  this.description = description;
		  this.channelTitle = channelTitle; 
		  this.categoryId = categoryId;
		  this.thumbnails = thumbnails;  
	  } 
	public Date getPublishedAt() {
		return publishedAt;
	}

	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public Thumbnails getThumbnails() {
		return thumbnails;
	}

	public void setThumbnails(Thumbnails thumbnails) {
		this.thumbnails = thumbnails;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public Statistics getStatistics() {
		return statistics;
	}
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	} 
	
}
