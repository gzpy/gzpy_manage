package com.gzpy.product.controller;

import java.sql.Date;
import java.util.Calendar;
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

/**
 * 产品管理
 * @author 品韵科技
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{
	
	@Autowired
	private ProductService productService;
	
	private int currentPage;//当前页码
	
	private int pageSize;//单个页面显示数量
	
	@RequestMapping("/toProductManage.do")
	public String toProductManage(String pageNum,String numPerPage,ModelMap map){
		
		if(pageNum == null || "".equals(pageNum)){
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(pageNum);
		}
		
		if(numPerPage == null || "".equals(numPerPage)){
			pageSize = 5;
		} else {
			pageSize = Integer.parseInt(numPerPage);
		}
		
		int totalPage = productService.findProductByCurrentPage(currentPage, pageSize).getTotalPages();
		long totalCount = productService.findProductByCurrentPage(currentPage,pageSize).getTotalElements();
		List<Product> list_product = productService.findProductByCurrentPage(currentPage,pageSize).getContent();
		
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
	
	/**
	 * 添加产品
	 * @param product
	 * @param productIssueDate
	 * @return
	 */
	@RequestMapping("/addProduct.do")
	public ModelAndView addProduct(Product product,String productIssueDate){
		
		product.setIssueDate(Date.valueOf(productIssueDate));
		product.setProductId(GenerateGUID.getGuid());
		product.setDelStatus("N");
		
		Product result = productService.saveProduct(product);
		
		if(result == null || "".equals(result)){
			return this.ajaxDoneError("添加失败,请重新添加！");
		} else {
			return this.ajaxDoneSuccess("添加成功", "productManage", "closeCurrent");
		}
	}
	
	@RequestMapping("/toUpdateProduct.do")
	public String toUpdateProduct(String productId,ModelMap map){
		
		Product product = productService.findProductById(productId);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(product.getIssueDate());
		
		StringBuffer sb = new StringBuffer();
		
		if(calendar.get(Calendar.MONTH) < 10){
			sb.append(calendar.get(Calendar.YEAR)).append("-0");
		} else {
			sb.append(calendar.get(Calendar.YEAR)).append("-");
		}
		
		sb.append(calendar.get(Calendar.MONTH) + 1).append("-");
		sb.append(calendar.get(Calendar.DATE));
		
		map.addAttribute("issueDate", sb);
		map.addAttribute("product",product);
		return "product/updateProduct.jsp";
	}
	
	/**
	 * 修改产品
	 * @param product
	 * @param productIssueDate
	 * @return
	 */
	@RequestMapping("/updateProduct.do")
	public ModelAndView updateProduct(Product product,String productIssueDate){
		
		product.setIssueDate(Date.valueOf(productIssueDate));
		
		Product oldProduct = productService.findProductById(product.getProductId());
		
		if(product.getDelStatus() == null || "".equals(product.getDelStatus())){
			product.setDelStatus(oldProduct.getDelStatus());
		}
		
		if(product.getImagePath() == null || "".equals(product.getImagePath())){
			product.setImagePath(oldProduct.getImagePath());
		}
		
		if(product.getUserId() == null || "".equals(product.getUserId())){
			product.setUserId(oldProduct.getUserId());
		}
		
		Product result = productService.saveProduct(product);
		
		if(result != null){
			return this.ajaxDoneSuccess("修改成功", "productManage", "closeCurrent");
		} 
		
		return this.ajaxDoneError("修改失败，请输入正确的信息！");
	}
	
	/**
	 * 删除产品
	 * @param productId
	 * @return
	 */
	@RequestMapping("/deleteProduct.do")
	public ModelAndView deleteProduct(String productId){
		
		try {
			productService.deleteProduct(productId);
			return this.ajaxDoneSuccess("删除成功", "productManage", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.ajaxDoneError("删除失败");
	}
	
	@RequestMapping("/toProductDetail.do")
	public String toProductDetail(String productId,ModelMap map){
		
		Product product = productService.findProductById(productId);
		map.addAttribute("product", product);
		
		return "product/productDetail.jsp";
	}
}
