package com.gzpy.advert.service.impl;

import java.io.File;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzpy.advert.dao.AdDao;
import com.gzpy.advert.entity.Ad;
import com.gzpy.advert.service.AdService;


@Service
@Transactional
public class AdServiceImpl implements AdService {
	@Autowired
	private AdDao adDao;

	@Override
	public Page<Ad> findAdByCurrentPage(int currentPage, int pageSize,final String inputName,final String dlStatus) {
		Specification<Ad> spec =new Specification<Ad>() {
			
			@Override
			public Predicate toPredicate(Root<Ad> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				//获取条件字段
				Path<String> delStatus=root.get("delStatus");
				Path<String> adName =root.get("adName");
				//建立查询条件
				Predicate status=cb.like(delStatus, dlStatus);
				Predicate name=cb.like(adName, inputName);
				query.where(status,name);
				return query.getRestriction();
			}
		};
		Pageable pb = new PageRequest(currentPage - 1, pageSize,
				Sort.Direction.ASC, "adOrder");
		Page<Ad> page = adDao.findAll(spec,pb);

		return page;
	}

	@Override
	//添加广告
	public Ad saveAd(Ad ad) {
		
		return adDao.save(ad);
	}

	
	//删除广告
	public void deleteAd(String id,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Ad ad=adDao.findOne(id);
		String imagePath=ad.getImagePath();
		if(imagePath!=null){
			//获取图片路径
			String path=request.getSession().getServletContext().getRealPath(imagePath);
		File image=new File(path);
		image.delete();
		}
		adDao.delete(id);
	}

	@Override
	//按id查找广告
	public Ad findAdById(String id) {
		// TODO Auto-generated method stub
		return adDao.findOne(id);
	}

	@Override
	public Page<Ad> findAdByName(final String inputName,int currentPage, int pageSize) {
		Specification<Ad> spec = new Specification<Ad>() {
			public Predicate toPredicate(Root<Ad> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path<String> name = root.get("adName");
				Predicate searchName=cb.like(name, "%"+inputName+"%");
				query.where(cb.and(searchName));  
				return query.getRestriction();  
			}
		};
		return adDao.findAll(spec,new PageRequest(currentPage - 1, pageSize,
				Sort.Direction.ASC, "adOrder"));
	}

	

}
