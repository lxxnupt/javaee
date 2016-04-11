package bbs.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bbs.entity.Person;
import bbs.entity.Post;
import bbs.entity.Replay;
import bbs.others.Louzhu;
import bbs.others.Page;
import bbs.service.IReplayService;

@Controller
@RequestMapping("/replay")
public class ReplayController {
	@Resource(name="ReplayManager")
	private IReplayService<Replay> replayServiceImpl;
	
	@RequestMapping("/findreplay")
	public ModelAndView findreplay(Integer id, Long currentPage)
	{
		ModelAndView mav = new ModelAndView();
		Page page = replayServiceImpl.page(id, currentPage);
		List<Replay> replays = replayServiceImpl.findReplay(id, currentPage);
		List<Person> authors = replayServiceImpl.find23Author(replays);
		Louzhu louzhu = replayServiceImpl.louzhu(id);
		mav.addObject("replays",replays);
		mav.addObject("page", page);
		mav.addObject("authors",authors);
		mav.addObject("louzhu", louzhu);
		mav.addObject("id", id);
		mav.setViewName("findreplay");
		return mav;
	}
	@RequestMapping("/addreplay")
	public ModelAndView addreplay(Replay replay,Integer postid,Integer authorid, Long currentPage,Long replaynumber)
	{
		ModelAndView mav = new ModelAndView();
		replayServiceImpl.addreplay(replay, postid, authorid);
		currentPage = replayServiceImpl.addreplayyema(currentPage, replaynumber);
		mav.addObject("currentPage",currentPage);
		mav.addObject("postid", postid);
		mav.addObject("authorid", authorid);
		mav.addObject("replayid",replay.getId());
		mav.setViewName("redirect:/post/setlastpersonandtime.do");
		return mav;
	}
	@RequestMapping("/deletereplay")
	public ModelAndView deletereplay(Integer postid,Long replaynumber,Long currentPage,Integer replayid)
	{
		ModelAndView mav = new ModelAndView();
		Long newcurrentPage = replayServiceImpl.deletereplay(replayid, replaynumber, currentPage);
		mav.addObject("currentPage", newcurrentPage);
		mav.addObject("id", postid);
		mav.setViewName("redirect:/post/deletereplaycount.do");
		return mav;
	}
}