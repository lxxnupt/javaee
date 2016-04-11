package bbs.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;


import bbs.entity.*;
import bbs.service.IBPService;
import bbs.service.IPersonService;

@Controller  
@RequestMapping("/bp")
public class BPController {
	@Resource(name="BPManager")
	private IBPService<Board_Person> bpServiceImpl;
	
	@RequestMapping("/deletemanager")//取消某个板块的管理员身份
	public ModelAndView deletemanager(Integer bid,Integer pid,String oldname){
		bpServiceImpl.cannelManager(bid, pid);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/todeletemanager.do");
		mav.addObject("bid",bid);
		mav.addObject("oldname",oldname);
		return mav;//返回准备取消的界面
	}

	@RequestMapping("/setmanager")//设置某个板块的管理员身份
	public ModelAndView setmanager(Integer bid,Integer pid,String oldname){
		bpServiceImpl.setManager(bid, pid);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("redirect:/user/tosetmanager.do");
		mav.addObject("bid",bid);
		mav.addObject("oldname",oldname);
		return mav;//返回准备取消的界面
	}
}
