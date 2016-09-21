package com.gzpy.product.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gzpy.common.BaseController;
import com.gzpy.product.entity.Product;
import com.gzpy.product.service.ProductService;
import com.gzpy.util.GenerateGUID;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{
	
	@Autowired
	private ProductService productService;
	
	private String currentPage;//当前页码
	
	private String pageSize;//单个页面显示数量
	
	@RequestMapping("/toProductManage.do")
	public String toProductManage(String pageNum,String numPerPage,ModelMap map){
		
		if(pageNum == null || "".equals(pageNum)){
			currentPage = "1";
		} else {
			currentPage = pageNum;
		}
		
		if(numPerPage == null || "".equals(numPerPage)){
			pageSize = "2";
		} else {
			pageSize = numPerPage;
		}
		
		int totalPage = productService.finProductByCurrentPage(
				Integer.parseInt(currentPage), Integer.parseInt(pageSize)).getTotalPages();
		long totalCount = productService.finProductByCurrentPage(
				Integer.parseInt(currentPage), Integer.parseInt(pageSize)).getTotalElements();
		List<Product> list_product = productService.finProductByCurrentPage(
				Integer.parseInt(currentPage), Integer.parseInt(pageSize)).getContent();
		
		map.addAttribute("currentPage", currentPage);
		map.addAttribute("pageSize", pageSize);
		map.addAttribute("totalPage", totalPage);
		map.addAttribute("totalCount", totalCount);
		map.addAttribute("list_product",list_product);
		
		return "product/productList.jsp";
	}
	
	@RequestMapping("/toAddProduct.do")
	public String toAddProduct(){
		
		return "product/addProduct.jsp";
	}
	
	@RequestMapping("/addProduct.do")
	public ModelAndView addProduct(Product product,String productIssueDate){
		
		product.setIssueDate(Date.valueOf(productIssueDate));
		product.setProductId(GenerateGUID.getGuid());
		product.setDelStatus("N");
		
		Product pd = productService.addProduct(product);
		
		if(pd == null || "".equals(pd)){
			return this.ajaxDoneError("添加失败,请重新添加！");
		} else {
			return this.ajaxDoneSuccess("添加成功", "productManage", "closeCurrent");
		}
	}
}
