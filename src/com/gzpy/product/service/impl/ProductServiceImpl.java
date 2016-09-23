package com.gzpy.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
