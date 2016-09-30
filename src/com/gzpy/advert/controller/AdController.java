package com.gzpy.advert.controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.gzpy.advert.entity.Ad;
import com.gzpy.advert.service.AdService;
import com.gzpy.common.BaseController;
import com.gzpy.util.GenerateGUID;

@Controller
@RequestMapping("/advert")
public class AdController extends BaseController {
	@Autowired
	private AdService adService;

	private int currentPage;// 当前页码

	private int pageSize;// 单个页面显示数量

	// 查找全部广告
	@RequestMapping("/toAdManage.do")
	public String toProductManage(String pageNum, String numPerPage,
			ModelMap map,String inputName,String delStatus) {
		
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
		//对输入的广告名称操作
		if(inputName==null || "".equals(inputName))
		{
			inputName="%";
		}else{
			map.addAttribute("inputName", inputName);
			inputName="%"+inputName+"%";
		}
		
		
		
		//对输入的delStatus进行判断
		if(delStatus==null || "".equals(delStatus)){
			delStatus="%";
		}else{
			delStatus="%"+delStatus+"%";
		}
		map.addAttribute("delStatus",delStatus);

		int totalPage = adService.findAdByCurrentPage(currentPage, pageSize,inputName,delStatus)
				.getTotalPages();
		long totalCount = adService.findAdByCurrentPage(currentPage, pageSize,inputName,delStatus)
				.getTotalElements();
		List<Ad> list_ad = adService.findAdByCurrentPage(currentPage, pageSize,inputName,delStatus)
				.getContent();

		map.addAttribute("currentPage", currentPage);
		map.addAttribute("pageSize", pageSize);
		map.addAttribute("totalPage", totalPage);
		map.addAttribute("totalCount", totalCount);
		map.addAttribute("list_ad", list_ad);

		return "advert/adList.jsp";
	}

	// 返回添加广告页面
	@RequestMapping("/toAddAd.do")
	public String toAddAd() {
		return "advert/addAd.jsp";
	}

	// 在数据库中添加广告
	@RequestMapping("/addAd.do")
	public ModelAndView addAd(HttpServletRequest request,MultipartFile ad_pic,
			Ad ad) throws Exception {
		// 转型为MultipartHttpRequest：
		// MultipartHttpServletRequest multipartRequest =
		// (MultipartHttpServletRequest) request;
		// 获得文件：
		// MultipartFile ad_pic = multipartRequest.getFile("ad_pic");
		
	
		if (ad_pic != null) {
			// 图片原始名称
			String originalFilename = ad_pic.getOriginalFilename();

			// 获得项目的路径
			ServletContext sc = request.getSession().getServletContext();
			// 上传图片
			if (originalFilename != null && originalFilename.length() > 0) {

				// 存储图片的路径
				String pic_path = sc.getRealPath("/upload/advert") + "/";
				// System.out.println(pic_path);
				// 新的图片名称
				String newFileName = UUID.randomUUID()
						+ originalFilename.substring(originalFilename
								.lastIndexOf("."));
				// 新图片

				File newFile = new File(pic_path + newFileName);
				if (!newFile.exists()) {
					newFile.mkdirs();
				}

				// 将内存中的数据写入磁盘
				ad_pic.transferTo(newFile);

				// 将新图片名称写到ad中
				ad.setImagePath("/upload/advert/" + newFileName);
			}
		}
		ad.setId(GenerateGUID.getGuid());
		ad.setDelStatus("N");
		Ad result = adService.saveAd(ad);
		if (result == null || "".equals(result)) {
			return this.ajaxDoneError("添加失败,请重新添加！");
		} else {
			return this.ajaxDoneSuccess("添加成功", "adManage", "closeCurrent");
		}
	}

	// 删除广告
	@RequestMapping("/deleteAd.do")
	public ModelAndView deleteAd(String id, HttpServletRequest request) {
		try {
			adService.deleteAd(id, request);
			return this.ajaxDoneSuccess("删除成功", "adManage", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.ajaxDoneError("删除失败");
	}

	// 返回编辑广告页面
	@RequestMapping("/toUpdateAd.do")
	public String toUpdateAd(String id, ModelMap map) {
		Ad ad = adService.findAdById(id);
		map.addAttribute("ad", ad);
		return "advert/updateAd.jsp";
	}

	// 提交编辑后的广告数据
	@RequestMapping("/updateAd.do")
	public ModelAndView updateAd(HttpServletRequest request,Ad ad,MultipartFile ad_pic) throws Exception {

		Ad oldAd = adService.findAdById(ad.getId());
		//获取图片原始名称
		String originalFilename=ad_pic.getOriginalFilename();
		System.out.println(originalFilename.length());
		if(originalFilename!=null && originalFilename.length()>0){
			
			//获取项目的路径
			ServletContext sc=request.getSession().getServletContext();
			if(oldAd.getImagePath()==null || "".equals(oldAd.getImagePath())){
				// 存储图片的路径
				String pic_path = sc.getRealPath("/upload/advert") + "/";
				// System.out.println(pic_path);
				// 新的图片名称
				String newFileName = UUID.randomUUID()
						+ originalFilename.substring(originalFilename
								.lastIndexOf("."));
				// 新图片

				File newFile = new File(pic_path + newFileName);
				if (!newFile.exists()) {
					newFile.mkdirs();	
			}
				// 将内存中的数据写入磁盘
				ad_pic.transferTo(newFile);

				// 将新图片名称写到ad中
				ad.setImagePath("/upload/advert/" + newFileName);
		}
			else{
				String path=oldAd.getImagePath();
				File newFile=new File(sc.getRealPath(path));
				// 将新图片的数据写入磁盘
				ad_pic.transferTo(newFile);
				//获取旧图片路径并保存为新图片路径
				ad.setImagePath(oldAd.getImagePath());
			}
		}
		ad.setAdDescription(oldAd.getAdDescription());
		Ad result = adService.saveAd(ad);

		if (result == null || "".equals(result)) {
			return this.ajaxDoneError("修改失败,请重新输入正确的信息！");
		} else {
			return this.ajaxDoneSuccess("修改成功", "adManage", "closeCurrent");
		}

	}

	// 查看广告详情
	@RequestMapping("/toAdDetail.do")
	public String toAdDetail(String id, ModelMap map) {
		Ad ad = adService.findAdById(id);
		map.addAttribute("ad", ad);
		return "advert/adDetail.jsp";
	}

	// 根据广告名称查询广告
	@RequestMapping("/findAdByName.do")
	public String findAdByName(String pageNum, String numPerPage,
			String inputName, ModelMap map) {
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
		int totalPage = adService
				.findAdByName(inputName, currentPage, pageSize).getTotalPages();
		long totalCount = adService.findAdByName(inputName, currentPage,
				pageSize).getTotalElements();
		List<Ad> list_ad = adService.findAdByName(inputName, currentPage,
				pageSize).getContent();
		map.addAttribute("list_ad", list_ad);
		map.addAttribute("totalPage", totalPage);
		map.addAttribute("totalCount", totalCount);
		map.addAttribute("numPerPage", numPerPage);
		map.addAttribute("currentPage", currentPage);
		return "advert/adList.jsp";
	}

	// 批量修改删除状态
	@RequestMapping("/deleteAds.do")
	public ModelAndView deleteSelectUser(HttpServletRequest request) {
		String[] ids = request.getParameterValues("ids");
		try {
			for (String id : ids) {
				Ad ad = adService.findAdById(id);
				ad.setDelStatus("Y");
				adService.saveAd(ad);
			}
			return this.ajaxDoneSuccess("修改删除状态成功", "adManage", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.ajaxDoneError("修改删除状态失败");
	}
}
