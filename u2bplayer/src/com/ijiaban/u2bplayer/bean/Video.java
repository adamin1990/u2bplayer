package com.ijiaban.u2bplayer.bean;

/**
 * 
 * @author Administrator
 *
 */
public class Video {
	
	public String id; 
	public String published;//����ʱ��
	public String updated;//����ʱ��
	public Category category ;//�������
	public String title; //����
	public String content;//ֱ����Ƶ��ַ
	public String related;//�����Ƶ��ַ
	public Author author;//����
	public String comment;//��������
	public String videoid;//����id
	public String defaultpic; //Ĭ�ϵĲ�����ҳ��ַ
	public int duration;//��Ƶ����
	public float rating	;//�����Ǽ�
	public int viewCount;//���Ŵ���
	public int favoriteCount ;//�ղش���
	public int  numDislikes;//��ϲ������
	public int numLikes;//ϲ������
	public String description;//��Ƶ����
	public String detail;//��Ƶ��ϸ��ַ
	 
	
	
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPublished() {
		return published;
	}
	public void setPublished(String published) {
		this.published = published;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRelated() {
		return related;
	}
	public void setRelated(String related) {
		this.related = related;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getVideoid() {
		return videoid;
	}
	public void setVideoid(String videoid) {
		this.videoid = videoid;
	}
	public String getDefaultpic() {
		return defaultpic;
	}
	public void setDefaultpic(String defaultpic) {
		this.defaultpic = defaultpic;
	}
	 
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public int getFavoriteCount() {
		return favoriteCount;
	}
	public void setFavoriteCount(int favoriteCount) {
		this.favoriteCount = favoriteCount;
	}
	public int getNumDislikes() {
		return numDislikes;
	}
	public void setNumDislikes(int numDislikes) {
		this.numDislikes = numDislikes;
	}
	public int getNumLikes() {
		return numLikes;
	}
	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	 
	
}
