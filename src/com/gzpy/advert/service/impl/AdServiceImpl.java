package com.gzpy.advert.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzpy.advert.dao.AdDao;
import com.gzpy.advert.entity.Ad;
import com.gzpy.advert.service.AdService;
import com.gzpy.product.entity.Product;

@Service
@Transactional
public class AdServiceImpl implements AdService {
	@Autowired
	private AdDao adDao;

	@Override
	public Page<Ad> findAdByCurrentPage(int currentPage, int pageSize) {
		Pageable pb = new PageRequest(currentPage - 1, pageSize,
				Sort.Direction.ASC, "Id");
		Page<Ad> page = adDao.findAll(pb);

		return page;
	}

	@Override
	//添加广告
	public Ad saveAd(Ad ad) {
		
		return adDao.save(ad);
	}

	
	//删除广告
	public void deleteAd(String id) {
		// TODO Auto-generated method stub
		adDao.delete(id);
	}

	@Override
	//按id查找广告
	public Ad findAdById(String id) {
		// TODO Auto-generated method stub
		return adDao.findOne(id);
	}

}
