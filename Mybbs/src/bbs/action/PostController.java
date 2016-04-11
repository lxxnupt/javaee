package bbs.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import bbs.entity.Board;
import bbs.entity.Person;
import bbs.entity.Post;
import bbs.others.Page;
import bbs.service.IPostService;

@Controller
@RequestMapping("/post")
public class PostController {
	@Resource(name="PostManager")
	private IPostService<Post> postServiceImpl;
	
	@RequestMapping("/findpost")
	public ModelAndView findpost(Integer id, Long currentPage)
	{
		ModelAndView mav = new ModelAndView();
		List<Post> posts = postServiceImpl.findPostByBoardId(id, currentPage);
		Page page = postServiceImpl.page(id, currentPage);
		List<Person> authors = postServiceImpl.find5Author(posts);
		List<Person> authorslast = postServiceImpl.find5LastPerson(posts);
		mav.addObject("id", id);
		mav.addObject("posts",posts);
		mav.addObject("page", page);
		mav.addObject("authors",authors);
		mav.addObject("authorslast",authorslast);
		mav.setViewName("findpost");
		return mav;
	}
	
	@RequestMapping("/toaddpost")
	public ModelAndView toaddpost(Integer id)
	{
		ModelAndView mav = new ModelAndView();		
		mav.addObject("id",id);		
		mav.setViewName("addpost");
		return mav;
	}
	
	@RequestMapping("/addpost")
	public ModelAndView addpost(Integer bid,Integer pid,Post post)
	{
		ModelAndView mav = new ModelAndView();
		postServiceImpl.addpost(bid, pid, post);
		mav.addObject("id",bid);
		mav.addObject("currentPage",1);
		mav.setViewName("redirect:/post/findpost.do");
		return mav;
	}
	
	@RequestMapping("/setlastpersonandtime")
	public ModelAndView setlastperson(Long currentPage,Integer postid,Integer authorid,Integer replayid)
	{
		ModelAndView mav = new ModelAndView();
		postServiceImpl.setLastPerson(postid, authorid);
		postServiceImpl.lastreplaytime(postid, replayid);
		mav.addObject("currentPage",currentPage );
		mav.addObject("id", postid);
		mav.setViewName("redirect:/replay/findreplay.do");
		return mav;		
	}
	
	@RequestMapping("/addposthit")
	public ModelAndView addposthit(Long currentPage,Integer id)
	{
		ModelAndView mav = new ModelAndView();
		postServiceImpl.addPostHit(id, currentPage);
		mav.addObject("currentPage",currentPage );
		mav.addObject("id", id);
		mav.setViewName("redirect:/replay/findreplay.do");
		return mav;		
	}
	
	@RequestMapping("/deletereplaycount")
	public ModelAndView deletereplaycount(Long currentPage,Integer id)
	{
		ModelAndView mav = new ModelAndView();
		postServiceImpl.deletereplaycount(id);
		mav.addObject("currentPage", currentPage);
		mav.addObject("id", id);
		mav.setViewName("redirect:/replay/findreplay.do");
		return mav;
	}
	
	@RequestMapping("/deletepost")
	public ModelAndView deletepost(Integer id,Long currentPage,Long postnumber)
	{
		ModelAndView mav = new ModelAndView();
		Integer boardid = postServiceImpl.findboardid(id);
		currentPage = postServiceImpl.deletepost(id, currentPage, postnumber);
		mav.addObject("currentPage", currentPage);
		mav.addObject("id", boardid);
		mav.setViewName("findpost");
		return mav;
	}
}