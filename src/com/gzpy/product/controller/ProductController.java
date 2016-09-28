package com.gzpy.product.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gzpy.common.BaseController;
import com.gzpy.product.entity.Product;
import com.gzpy.product.service.ProductService;
import com.gzpy.user.entity.User;
import com.gzpy.util.GenerateGUID;

/**
 * 产品管理
 * 
 * @author 品韵科技
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	private int currentPage;// 当前页码

	private int pageSize;// 单个页面显示数量

	@RequestMapping("/toProductManage.do")
	public String toProductManage(String pageNum, String numPerPage,
			String productTitle, String delStatus, ModelMap map) {

		if (pageNum == null || "".equals(pageNum)) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(pageNum);
		}

		if (numPerPage == null || "".equals(numPerPage)) {
			pageSize = 5;
		} else {
			pageSize = Integer.parseInt(numPerPage);
		}

		if (productTitle == null || "".equals(productTitle)) {
			productTitle = "%";
		} else {
			map.addAttribute("productTitle", productTitle);
			productTitle = "%" + productTitle + "%";
		}

		if (delStatus == null) {
			delStatus = "%N%";
		} else if ("".equals(delStatus)) {
			delStatus = "%";
		} else {
			delStatus = "%" + delStatus + "%";
		}

		map.addAttribute("delStatus", delStatus);

		int totalPage = productService.findProductBySearch(currentPage,
				pageSize, productTitle, delStatus).getTotalPages();
		long totalCount = productService.findProductBySearch(currentPage,
				pageSize, productTitle, delStatus).getTotalElements();
		List<Product> list_product = productService.findProductBySearch(
				currentPage, pageSize, productTitle, delStatus).getContent();

		map.addAttribute("currentPage", currentPage);
		map.addAttribute("pageSize", pageSize);
		map.addAttribute("totalPage", totalPage);
		map.addAttribute("totalCount", totalCount);
		map.addAttribute("list_product", list_product);

		return "product/productList.jsp";
	}

	@RequestMapping("/toAddProduct.do")
	public String toAddProduct() {

		return "product/addProduct.jsp";
	}

	/**
	 * 添加产品
	 * 
	 * @param product
	 * @param productIssueDate
	 * @return
	 */
	@RequestMapping("/addProduct.do")
	public ModelAndView addProduct(@RequestParam MultipartFile file,
			Product product, String productIssueDate, HttpServletRequest request) {

		List<String> allowTypes = new ArrayList<String>();
		allowTypes.add(".jpg");
		allowTypes.add(".jepg");
		allowTypes.add(".bmp");
		allowTypes.add(".png");

		if (file != null && file.getSize() > 0) {
			String fileOriginName = file.getOriginalFilename();
			String ext = fileOriginName.substring(
					fileOriginName.lastIndexOf("."), fileOriginName.length());

			boolean flag = false;

			for (String allowType : allowTypes) {
				if (ext.equals(allowType)) {
					flag = true;
				}
			}

			if (!flag) {
				return this.ajaxDoneError("请选择图片格式文件!");
			}

			if (file.getSize() > 10485760) {
				return this.ajaxDoneError("选择文件不能超过10M");
			}

			String filePath = request.getSession().getServletContext()
					.getRealPath("/upload/product");

			File fileUpload = new File(filePath);
			if (!fileUpload.exists()) {
				fileUpload.mkdirs();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String fileName = sdf.format(new java.util.Date()) + ".jpg";

			try {
				file.transferTo(new File(filePath + "\\" + fileName));
				product.setImagePath("\\upload\\product\\" + fileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		User user = (User) request.getSession().getAttribute("currentUser");
		product.setUserId(user.getUserId());
		product.setIssueDate(Date.valueOf(productIssueDate));
		product.setProductId(GenerateGUID.getGuid());
		product.setDelStatus("N");

		Product result = productService.saveProduct(product);

		if (result == null || "".equals(result)) {
			return this.ajaxDoneError("添加失败,请重新添加！");
		} else {
			return this
					.ajaxDoneSuccess("添加成功", "productManage", "closeCurrent");
		}
	}

	@RequestMapping("/toUpdateProduct.do")
	public String toUpdateProduct(String productId, ModelMap map) {

		Product product = productService.findProductById(productId);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(product.getIssueDate());

		StringBuffer sb = new StringBuffer();

		if (calendar.get(Calendar.MONTH) < 10) {
			sb.append(calendar.get(Calendar.YEAR)).append("-0");
		} else {
			sb.append(calendar.get(Calendar.YEAR)).append("-");
		}

		sb.append(calendar.get(Calendar.MONTH) + 1).append("-");
		sb.append(calendar.get(Calendar.DATE));

		map.addAttribute("issueDate", sb);
		map.addAttribute("product", product);
		return "product/updateProduct.jsp";
	}

	/**
	 * 修改产品
	 * 
	 * @param product
	 * @param productIssueDate
	 * @return
	 */
	@RequestMapping("/updateProduct.do")
	public ModelAndView updateProduct(@RequestParam MultipartFile file,
			Product product, String productIssueDate, HttpServletRequest request) {

		List<String> allowTypes = new ArrayList<String>();
		allowTypes.add(".jpg");
		allowTypes.add(".jepg");
		allowTypes.add(".bmp");
		allowTypes.add(".png");

		product.setIssueDate(Date.valueOf(productIssueDate));

		Product oldProduct = productService.findProductById(product
				.getProductId());

		if (product.getImagePath() == null || "".equals(product.getImagePath())) {
			product.setImagePath(oldProduct.getImagePath());
		}

		if (product.getUserId() == null || "".equals(product.getUserId())) {
			product.setUserId(oldProduct.getUserId());
		}

		if (file != null && file.getSize() > 0) {

			String fileOriginName = file.getOriginalFilename();
			String ext = fileOriginName.substring(
					fileOriginName.lastIndexOf("."), fileOriginName.length());

			boolean flag = false;

			for (String allowType : allowTypes) {
				if (ext.equals(allowType)) {
					flag = true;
				}
			}

			if (!flag) {
				return this.ajaxDoneError("请选择图片格式文件!");
			}

			if (file.getSize() > 10485760) {
				return this.ajaxDoneError("选择文件不能超过10M");
			}

			String filePath = request.getSession().getServletContext()
					.getRealPath("/upload/product");

			File fileUpload = new File(filePath);
			if (!fileUpload.exists()) {
				fileUpload.mkdirs();
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
			String fileName = sdf.format(new java.util.Date()) + ".jpg";

			try {
				file.transferTo(new File(filePath + "\\" + fileName));
				product.setImagePath("\\upload\\product\\" + fileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (!(oldProduct.getImagePath() == null || "".equals(oldProduct
					.getImagePath()))) {
				File oldFile = new File(request.getSession()
						.getServletContext().getRealPath(oldProduct.getImagePath()));
				if (oldFile.exists()) {
					oldFile.delete();
				}
			}
		}

		Product result = productService.saveProduct(product);

		if (result != null) {
			return this
					.ajaxDoneSuccess("修改成功", "productManage", "closeCurrent");
		}

		return this.ajaxDoneError("修改失败，请输入正确的信息！");
	}

	/**
	 * 删除产品
	 * 
	 * @param productId
	 * @return
	 */
	@RequestMapping("/deleteProduct.do")
	public ModelAndView deleteProduct(String productId,HttpServletRequest request) {

		Product product = productService.findProductById(productId);
		
		if (!(product.getImagePath() == null || "".equals(product
				.getImagePath()))) {
			File oldFile = new File(request.getSession()
					.getServletContext().getRealPath(product.getImagePath()));
			if (oldFile.exists()) {
				oldFile.delete();
			}
		}
		
		try {
			productService.deleteProduct(productId);
			return this.ajaxDoneSuccess("删除成功", "productManage", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.ajaxDoneError("删除失败");
	}

	@RequestMapping("/toProductDetail.do")
	public String toProductDetail(String productId, ModelMap map) {

		Product product = productService.findProductById(productId);
		map.addAttribute("product", product);

		return "product/productDetail.jsp";
	}
}
