package com.adp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity//声明当前类为hibernate映射到数据库中的实体�?
@Table(name = "VideoCategory")//声明在数据库中自动生成的表名为User
public class VideoCategory {

	@Id//声明此列为主�?
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer videoCategoryID;
	
	private String videoCategoryName;  
	
	private String videoCategoryUrl;
	
	@OneToMany(mappedBy = "videoCategory", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	@JsonIgnore
	private List<Video> videoList = new ArrayList<Video>();
	
	public List<Video> getVideoList() {
		return videoList;
	}
	public void setVideoList(List<Video> videoList) {
		this.videoList = videoList;
	}
	public Integer getVideoCategoryID() {
		return videoCategoryID;
	}
	public void setVideoCategoryID(Integer videoCategoryID) {
		this.videoCategoryID = videoCategoryID;
	}
	public String getVideoCategoryName() {
		return videoCategoryName;
	}
	public void setVideoCategoryName(String videoCategoryName) {
		this.videoCategoryName = videoCategoryName;
	}
	public String getVideoCategoryUrl() {
		return videoCategoryUrl;
	}
	public void setVideoCategoryUrl(String videoCategoryUrl) {
		this.videoCategoryUrl = videoCategoryUrl;
	}
	
}
