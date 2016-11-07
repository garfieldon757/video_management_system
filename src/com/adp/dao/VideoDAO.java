package com.adp.dao;

import java.util.List;

import com.adp.model.Video;
import com.adp.model.VideoCategory;

public interface VideoDAO {
	public List<VideoCategory> getVideoCategoryList();
	public VideoCategory getVideoCategoryByVideoCategoryID(int videoCategroyID);
	public List<Video> getVideoListByVideoCategroyIDAndPage(VideoCategory videoCategory , int page);
	public int getVideoListSizeByVideoCategory(VideoCategory videoCategory);
	public Video getVideoByVideoID(int videoID);
}
