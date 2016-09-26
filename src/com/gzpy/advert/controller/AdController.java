package com.gzpy.advert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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
			ModelMap map) {

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

		int totalPage = adService.findAdByCurrentPage(currentPage, pageSize)
				.getTotalPages();
		long totalCount = adService.findAdByCurrentPage(currentPage, pageSize)
				.getTotalElements();
		List<Ad> list_ad = adService.findAdByCurrentPage(currentPage, pageSize)
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
	public ModelAndView addAd(Ad ad, String adWidth, String adHeight,
			String adOrder) {
		ad.setAdHeight(Integer.parseInt(adHeight));
		ad.setAdWidth(Integer.parseInt(adWidth));
		ad.setAdOrder(Integer.parseInt(adOrder));
		ad.setId(GenerateGUID.getGuid());
		ad.setDelStatus("N");
		Ad result = adService.saveAd(ad);
		if (result == null || "".equals(result)) {
			return this.ajaxDoneError("添加失败,请重新添加！");
		} else {
			return this
					.ajaxDoneSuccess("添加成功", "adManage", "closeCurrent");
		}
	}

	// 删除广告
	@RequestMapping("/deleteAd.do")
	public ModelAndView deleteAd(String id) {
		try {
			adService.deleteAd(id);
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
	public ModelAndView updateAd(Ad ad, String adWidth, String adHeight,
			String adOrder) {
		ad.setAdHeight(Integer.parseInt(adHeight));
		ad.setAdWidth(Integer.parseInt(adWidth));
		ad.setAdOrder(Integer.parseInt(adOrder));

		Ad oldAd = adService.findAdById(ad.getId());

		if (ad.getDelStatus() == null || "".equals(ad.getDelStatus())) {
			ad.setDelStatus(oldAd.getDelStatus());
		}
		if (ad.getAdLink() == null || "".equals(ad.getAdLink())) {
			ad.setAdLink(oldAd.getAdLink());
		}
		if (ad.getImagePath() == null || "".equals(ad.getImagePath())) {
			ad.setImagePath(oldAd.getImagePath());
		}

		Ad result = adService.saveAd(ad);

		if (result == null || "".equals(result)) {
			return this.ajaxDoneError("修改失败,请重新输入正确的信息！");
		} else {
			return this.ajaxDoneSuccess("修改成功", "adManage", "closeCurrent");
		}

	}

	// 查看广告详情
	@RequestMapping("/toAdDetail.do")
	public String toAdDetail(String id ,ModelMap map) {
		Ad ad=adService.findAdById(id);
		map.addAttribute("ad",ad);
		return "advert/adDetail.jsp";
	}
	//根据广告名称查询广告
	@RequestMapping("/findAdByName.do")
	public String findAdByName(String pageNum, String numPerPage,String inputName,ModelMap map){
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
		int totalPage = adService.findAdByName(inputName,currentPage, pageSize)
				.getTotalPages();
		long totalCount = adService.findAdByName(inputName,currentPage, pageSize)
				.getTotalElements();
		List<Ad> list_ad = adService.findAdByName(inputName,currentPage, pageSize)
				.getContent();
		map.addAttribute("list_ad",list_ad);
		  map.addAttribute("totalPage",totalPage);
		  map.addAttribute("totalCount",totalCount);
		  map.addAttribute("numPerPage", numPerPage);
		  map.addAttribute("currentPage", currentPage);
		  return "advert/adList.jsp";
	}
	//批量删除广告
	@RequestMapping("/deleteSelectUser.do")
	public ModelAndView deleteSelectUser(String[] ids){
		for(int i=0;i<=ids.length;i++){
			String id=ids[i];
			System.out.println(id);
		}
		return null;
		
	}
}
