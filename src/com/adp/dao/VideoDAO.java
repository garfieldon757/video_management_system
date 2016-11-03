package com.adp.dao;

import java.util.List;

import com.adp.model.Video;
import com.adp.model.VideoCategory;

public interface VideoDAO {
	public List<VideoCategory> getVideoCategoryList();
	public VideoCategory getVideoCategoryByVideoCategoryID(String videoCategroyID);
	public List<Video> getVideoListByVideoCategroyIDAndPage(VideoCategory videoCategory , Integer page);
	public int getVideoListSizeByVideoCategoryID(String videoCategoryID);
}
