package com.ijiaban.u2bplayer.common.bean;
/**
 * ����Ƶ����Դid
 * @author Adam
 *
 */
public class ResourceId {
public String kind;
public String channelId; //��������Ƶ��id
public ResourceId(String kind, String channelId) {
	super();
	this.kind = kind;
	this.channelId = channelId;
}
public String getKind() {
	return kind;
}
public void setKind(String kind) {
	this.kind = kind;
}
public String getChannelId() {
	return channelId;
}
public void setChannelId(String channelId) {
	this.channelId = channelId;
}
public ResourceId() {
	super();
}
}
