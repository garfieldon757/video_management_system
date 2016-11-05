package com.adp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.adp.dao.VideoDAO;
import com.adp.model.Video;
import com.adp.model.VideoCategory;

@Repository("vd")
@Transactional
public class VideoDAOIpml implements VideoDAO{

	@PersistenceContext(name="un")
	private EntityManager em ;
	
	@Override
	public List<VideoCategory> getVideoCategoryList() {
		
		String jpql = "select vc from VideoCategory vc";
		List<VideoCategory> videoCategoryList = em.createQuery(jpql).getResultList();
		return videoCategoryList;
		
	}
	
	@Override
	public VideoCategory getVideoCategoryByVideoCategoryID(int videoCategoryID) {
		String jpql = "select vc from VideoCategory vc where vc.videoCategoryID =:videoCategoryID";
		VideoCategory videoCategroy = (VideoCategory) em.createQuery(jpql).setParameter("videoCategoryID", videoCategoryID ).getResultList().get(0);
		return videoCategroy;
	}

	@Override
	public List<Video> getVideoListByVideoCategroyIDAndPage(VideoCategory videoCategory , int page) {
		String jpql = "select v from Video v where v.videoCategory =:videoCategory";
		int startVideoIndex = ( page -1 ) * 16 ;
		List<Video> videoList = em.createQuery(jpql)
													.setParameter("videoCategory", videoCategory)
													.setMaxResults(16)
													.setFirstResult(startVideoIndex)
													.getResultList();
//		int startVideoIndex = ( page - 1 ) * 16;
//		int endVideoIndex = page * 16 -1;
//		if( (videoList.size() - endVideoIndex) < 16 ){
//			endVideoIndex = videoList.size();
//		}
//		videoList = videoList.subList(  startVideoIndex , endVideoIndex );//根据page参数选取16条video
		return videoList;
	}

	@Override
	public int getVideoListSizeByVideoCategory(VideoCategory videoCategory) {
		String jpql = "select count(v) from Video v where v.videoCategory =:videoCategory";
		String videoListSizeTemp=em.createQuery(jpql).setParameter("videoCategory", videoCategory ).getSingleResult().toString();
		int videoListSize = Integer.parseInt(videoListSizeTemp);
		return videoListSize;
	}

	

}
