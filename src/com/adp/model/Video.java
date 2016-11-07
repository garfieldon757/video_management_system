package com.adp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity//声明当前类为hibernate映射到数据库中的实体�?
@Table(name = "Video")//声明在数据库中自动生成的表名为User
public class Video {
	
	/*
	 {
      "可否下载": 0, 
      "语种": "日语", 
      "品牌名称": "新昭和", 
      "广告年份": "2016", 
      "广告视频资源链接": "http://static.video.qq.com/TPout.swf?vid=v03051tjfha&auto=0", 
      "国家或地区": "日本", 
      "视频名称": "影子篇", 
      "广告类型": "电视广告", 
      "广告视频封面图片链接": "http://k.cnad.com/upload/picture/201606/13/201606131029215650.jpg"
    }
	 * */
	
	@Id//声明此列为主�?
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer videoID;
	
	private int isDownloadable;
	private String language;
	private String brand;
	private String publishYear;
	private String videoSourceLink;
	private String country;
	private String videoName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="videoCategoryID")
	private VideoCategory videoCategory;
	
	private String videoCoverLink;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="userID")
	private User user;
	
	@OneToMany(mappedBy = "video", cascade=CascadeType.MERGE,fetch=FetchType.LAZY)
	private List<ProcessLog> processLogList = new ArrayList<ProcessLog>();
	
	public int getVideoID() {
		return videoID;
	}
	public List<ProcessLog> getProcessLogList() {
		return processLogList;
	}
	public void setProcessLogList(List<ProcessLog> processLogList) {
		this.processLogList = processLogList;
	}
	public void setVideoID(int videoID) {
		this.videoID = videoID;
	}
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getPublishYear() {
		return publishYear;
	}
	public void setPublishYear(String publishYear) {
		this.publishYear = publishYear;
	}
	public String getVideoSourceLink() {
		return videoSourceLink;
	}
	public void setVideoSourceLink(String videoSourceLink) {
		this.videoSourceLink = videoSourceLink;
	}
	
	public String getVideoName() {
		return videoName;
	}
	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}
	public VideoCategory getVideoCategory() {
		return videoCategory;
	}
	public void setVideoCategory(VideoCategory videoCategory) {
		this.videoCategory = videoCategory;
	}
	public String getVideoCoverLink() {
		return videoCoverLink;
	}
	public void setVideoCoverLink(String videoCoverLink) {
		this.videoCoverLink = videoCoverLink;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getIsDownloadable() {
		return isDownloadable;
	}
	public void setIsDownloadable(int isDownloadable) {
		this.isDownloadable = isDownloadable;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}

	
	
	
	
}
