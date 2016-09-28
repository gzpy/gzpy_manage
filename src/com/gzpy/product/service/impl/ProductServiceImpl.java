package com.gzpy.product.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gzpy.product.dao.ProductDao;
import com.gzpy.product.entity.Product;
import com.gzpy.product.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	
	public Page<Product> findProductByCurrentPage(int currentPage, int pageSize) {
		
		Pageable pb = new PageRequest(currentPage - 1, pageSize,
				Sort.Direction.ASC, "productId");
		Page<Product> page = productDao.findAll(pb);
		
		return page;
	}


	@Override
	public Page<Product> findProductBySearch(int currentPage, int pageSize,
			final String pTitle, final String dStatus) {
		
		Specification<Product> spec = new Specification<Product>() {
			public Predicate toPredicate(Root<Product> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				
				Path<String> delStatus = root.get("delStatus");
				Path<String> productTitle = root.get("productTitle");
				
				Predicate status = cb.like(delStatus,dStatus);
				Predicate title = cb.like(productTitle, pTitle);
				
				query.where(cb.and(status,title));
				
				return query.getRestriction();   
			}
		};
		
		Pageable pb = new PageRequest(currentPage - 1, pageSize,
				Sort.Direction.ASC, "productId");
		
		return productDao.findAll(spec, pb);
	}
	
	@Override
	public Product saveProduct(Product product) {
		
		return productDao.save(product);
	}

	@Override
	public Product findProductById(String productId) {
		
		return productDao.findOne(productId);
	}

	@Override
	public void deleteProduct(String productId) {
		
		productDao.delete(productId);
	}

}
