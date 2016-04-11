package bbs.action;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import bbs.entity.Person;
import bbs.service.IPersonService;

@Controller
@SessionAttributes("person")  
@RequestMapping("/user")
public class PersonController {
	//"/" 开头的直接就是服务器根路径， 不带 "/" 开头的，是相对路径
	@Resource(name="PersonManager")
	private IPersonService<Person> personServiceImpl;
	
	@RequestMapping("/index")					//初始页面，搜索所有版面存到session中
	public String index(){
		return	"index";
	}
	@RequestMapping("/addperson")//注册新用户@RequestParam注解不是严格要求的，默认前台注入同名入参
	public ModelAndView addPerson(@Valid @ModelAttribute("user")Person person,BindingResult bindingresult,String confirmpassword){
		ModelAndView mav = new ModelAndView();
		if(bindingresult.hasErrors()) {  
			System.out.println("校验错误");  
			mav.addObject("user",person);
			mav.setViewName("registerfail");
   			return mav;
		} 
		String message = personServiceImpl.register(person,confirmpassword);
		if(message.equals(""))
		{
			mav.addObject(person);
			mav.setViewName("registersuccess");
			return mav;
		}
		else
		{
		mav.addObject("message",message);
		mav.setViewName("registerfail");
		return mav;
		}
	}
	@RequestMapping(value="/login", method=RequestMethod.POST)//登录检测
	public ModelAndView login(Person p){
		Person person = personServiceImpl.loginPerson(p.getAccount(), p.getPassword());
		ModelAndView mav = new ModelAndView();
		if(person!=null)
		{
			mav.setViewName("main");
			mav.addObject(person);
			//model.addAttribute(person);//addAttribute可以添加空值的属性，put不行
														//添加Person对象(不管这个对象叫什么)到模型中，默认的属性名是person
			return mav;
		}
			mav.setViewName("loginfail");
			return mav;
	}
	@RequestMapping("/userinfo")//查看用户信息
	public String userinfo(){
		return "userinformation";
	}
	
	@RequestMapping("/back")//返回main.jsp
	public String back(){
		return "main";
	}
	
	@RequestMapping("/register")//指向注册界面
	public String reigster(String account,ModelMap model){
		System.out.println("使用了springmvc跳转到注册界面");
		return "register";
	}
	@RequestMapping("/todeletemanager")//查询某个板块的管理员，准备删除
	public ModelAndView todeletemanager(String oldname,Integer bid){
		List<Integer> ids = personServiceImpl.findManagerId(bid);
		List<Person> managers = personServiceImpl.findMany(Person.class, ids);
		ModelAndView mav = new ModelAndView();
		mav.addObject("managers",managers);
		mav.addObject("oldname",oldname);
		mav.addObject("bid",bid);
		mav.setViewName("deletemanager");
		return mav;
	}

	@RequestMapping("/tosetmanager")//查询某个板块的普通用户，准备设置管理员
	public ModelAndView tosetmanager(String oldname,Integer bid){
		List<Integer> ids = personServiceImpl.findCommonId(bid);
		List<Person> commons = personServiceImpl.findMany(Person.class, ids);		
		ModelAndView mav = new ModelAndView();
		mav.addObject("commons",commons);
		mav.addObject("oldname",oldname);
		mav.addObject("bid",bid);
		mav.setViewName("setmanager");
		return mav;
	}
	
	@RequestMapping("/toupdateperson")//准备更改用户的信息
	public ModelAndView toupdateperson(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("updateperson");
		return mav;
	}
	
	@RequestMapping("/updateperson")//更改用户的信息
	public ModelAndView updateperson(Person person){
		ModelAndView mav = new ModelAndView();
		personServiceImpl.update(person);
		mav.addObject("person", person);
		mav.setViewName("userinformation");
		return mav;
	}
}
