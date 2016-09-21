package com.gzpy.product.service;

import org.springframework.data.domain.Page;

import com.gzpy.product.entity.Product;

public interface ProductService {
	
	//分页查找
	public Page<Product> finProductByCurrentPage(int currentPage,int pageSize);
	
	//添加产品
	public Product addProduct(Product product);
}
