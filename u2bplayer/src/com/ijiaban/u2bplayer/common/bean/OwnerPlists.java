package com.ijiaban.u2bplayer.common.bean;

import java.util.Date;
/**
 * �û������б� 
 *
 * @author adamin
 *
 */
public class OwnerPlists {
	public String id; //�����б��id
	public String kind; //���� 
	public Date publishedAt;//��������
	public String channelId; //Ƶ��id
	public String description; //�б����� 
	public String title; //�б�����
	public Thumbnails thumbnails; //����ͼ
	public String channelTitle; //Ƶ������
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public Date getPublishedAt() {
		return publishedAt;
	}
	public void setPublishedAt(Date publishedAt) {
		this.publishedAt = publishedAt;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Thumbnails getThumbnails() {
		return thumbnails;
	}
	public void setThumbnails(Thumbnails thumbnails) {
		this.thumbnails = thumbnails;
	}
	public String getChannelTitle() {
		return channelTitle;
	}
	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}
	public OwnerPlists(String id, String kind, Date publishedAt,
			String channelId, String description, String title,
			Thumbnails thumbnails, String channelTitle) {
		super();
		this.id = id;
		this.kind = kind;
		this.publishedAt = publishedAt;
		this.channelId = channelId;
		this.description = description;
		this.title = title;
		this.thumbnails = thumbnails;
		this.channelTitle = channelTitle;
	}
	public OwnerPlists() {
		super();
	}

}
