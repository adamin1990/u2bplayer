package com.ijiaban.uitls;

import org.json.JSONException;
import org.json.JSONObject;
public class PlayListItem {
    public final String id;
    public final String title;
    public final String description;
    public final String thumbnailUrl;
    public final String videoId;
    public final int position;
/**
 * ��ȡ�б���Ƶÿ����Ƶ����ϸ��Ϣ��
 * @param jsonItem
 * @throws JSONException
 */
    public PlayListItem(JSONObject jsonItem) throws JSONException {
        id = jsonItem.getString("id");
        final JSONObject snippet = jsonItem.getJSONObject("snippet");
        position = snippet.getInt("position");
        title = snippet.getString("title");
        description = snippet.getString("description");
        thumbnailUrl = snippet.getJSONObject("thumbnails").getJSONObject("medium").getString("url");
        videoId = snippet.getJSONObject("resourceId").getString("videoId");
    }
}