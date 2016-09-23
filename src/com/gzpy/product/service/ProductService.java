package com.gzpy.product.service;

import org.springframework.data.domain.Page;

import com.gzpy.product.entity.Product;

public interface ProductService {
	
	/**
	 * 分页查找所有产品
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public Page<Product> findProductByCurrentPage(int currentPage,int pageSize);
	
	/**
	 * 保存产品
	 * @param product
	 * @return
	 */
	public Product saveProduct(Product product);
	
	/**
	 * 按ID查找产品
	 * @param productId
	 * @return
	 */
	public Product findProductById(String productId);
	
	/**
	 * 按ID删除产品
	 * @param productId
	 */
	public void deleteProduct(String productId);
}
