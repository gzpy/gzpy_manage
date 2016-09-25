package com.gzpy.advert.service;

import org.springframework.data.domain.Page;

import com.gzpy.advert.entity.Ad;


public interface AdService {
	/**
	 * 分页查找所有广告
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<Ad> findAdByCurrentPage(int currentPage,int pageSize);

	
	
	/**
	 * 添加广告
	 * @param ad
	 * @return
	 */
	public Ad saveAd(Ad ad);



	
	
	/**
	 * 按ID查找广告
	 * @param adId
	 * @return
	 */
	public Ad findAdById(String id);
	
	/**
	 * 按ID删除广告
	 * @param adId
	 */
	public void deleteAd(String id);



	
}
