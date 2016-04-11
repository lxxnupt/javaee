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
	//"/" ��ͷ��ֱ�Ӿ��Ƿ�������·���� ���� "/" ��ͷ�ģ������·��
	@Resource(name="PersonManager")
	private IPersonService<Person> personServiceImpl;
	
	@RequestMapping("/index")					//��ʼҳ�棬�������а���浽session��
	public String index(){
		return	"index";
	}
	@RequestMapping("/addperson")//ע�����û�@RequestParamע�ⲻ���ϸ�Ҫ��ģ�Ĭ��ǰ̨ע��ͬ�����
	public ModelAndView addPerson(@Valid @ModelAttribute("user")Person person,BindingResult bindingresult,String confirmpassword){
		ModelAndView mav = new ModelAndView();
		if(bindingresult.hasErrors()) {  
			System.out.println("У�����");  
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
	@RequestMapping(value="/login", method=RequestMethod.POST)//��¼���
	public ModelAndView login(Person p){
		Person person = personServiceImpl.loginPerson(p.getAccount(), p.getPassword());
		ModelAndView mav = new ModelAndView();
		if(person!=null)
		{
			mav.setViewName("main");
			mav.addObject(person);
			//model.addAttribute(person);//addAttribute������ӿ�ֵ�����ԣ�put����
														//���Person����(������������ʲô)��ģ���У�Ĭ�ϵ���������person
			return mav;
		}
			mav.setViewName("loginfail");
			return mav;
	}
	@RequestMapping("/userinfo")//�鿴�û���Ϣ
	public String userinfo(){
		return "userinformation";
	}
	
	@RequestMapping("/back")//����main.jsp
	public String back(){
		return "main";
	}
	
	@RequestMapping("/register")//ָ��ע�����
	public String reigster(String account,ModelMap model){
		System.out.println("ʹ����springmvc��ת��ע�����");
		return "register";
	}
	@RequestMapping("/todeletemanager")//��ѯĳ�����Ĺ���Ա��׼��ɾ��
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

	@RequestMapping("/tosetmanager")//��ѯĳ��������ͨ�û���׼�����ù���Ա
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
	
	@RequestMapping("/toupdateperson")//׼�������û�����Ϣ
	public ModelAndView toupdateperson(){
		ModelAndView mav = new ModelAndView();
		mav.setViewName("updateperson");
		return mav;
	}
	
	@RequestMapping("/updateperson")//�����û�����Ϣ
	public ModelAndView updateperson(Person person){
		ModelAndView mav = new ModelAndView();
		personServiceImpl.update(person);
		mav.addObject("person", person);
		mav.setViewName("userinformation");
		return mav;
	}
}
