package bbs.action;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import bbs.entity.Board;
import bbs.entity.Person;
import bbs.service.IBoardService;


@Controller
@SessionAttributes("allboards")  
@RequestMapping("/board")
public class BoardController {
	@Resource(name="BoardManager")
	private IBoardService<Board> boardServiceImpl;
	
	@RequestMapping("/index")					//��ʼҳ�棬�������а���浽session��
	public ModelAndView index(){
		ModelAndView mav = new ModelAndView();
		List <Board> allborads  = boardServiceImpl.findAll(Board.class);
		mav.addObject("allboards", allborads);
		mav.setViewName("redirect:/user/index.do");
		return mav;
	}
	@RequestMapping("/toaddboard")			//׼����Ӱ���
	public String toaddBoard(){
		return "addboard";
		}
	
	@RequestMapping("/addboard")			//��Ӱ���
	public ModelAndView addBoard(Board board){
		ModelAndView mav = new ModelAndView();
		if(boardServiceImpl.create(board))
		{
			mav.setViewName("redirect:/user/back.do");
			List <Board> allborads  = boardServiceImpl.findAll(Board.class);
			mav.addObject("allboards", allborads);
			//mav.addObject("message","�������ɹ�");��ת���Σ���ʱ�������ӳɹ�����ʾ
			return mav;
		}
		else{
			mav.setViewName("addboard");
			return mav;
		}
	}
	@RequestMapping("/toupdateboard")		//������İ���ҳ��
	public ModelAndView toupdateboard(@RequestParam("oldname") String oldname){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("updateboard");
		mav.addObject("oldname", oldname);
		return mav;
	}
	@RequestMapping("/updateboard")		//���İ���
	public ModelAndView updateboard(Board board,String oldname){
		ModelAndView mav = new ModelAndView();
		boardServiceImpl.updateBoard(board,oldname);
		List <Board> allborads  = boardServiceImpl.findAll(Board.class);
		mav.addObject("allboards", allborads);
		mav.setViewName("redirect:/user/back.do");
		return mav;
	}
	@RequestMapping("/deleteboard")		//ɾ������
	public ModelAndView deleteboard(Integer id){
		ModelAndView mav = new ModelAndView();
		boardServiceImpl.deleteById(Board.class, id);
		List <Board> allborads  = boardServiceImpl.findAll(Board.class);
		mav.addObject("allboards", allborads);
		mav.setViewName("redirect:/user/back.do");
		return mav;
	}
}