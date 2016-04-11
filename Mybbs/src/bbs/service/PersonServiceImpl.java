package bbs.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;

import bbs.entity.Board;
import bbs.entity.Board_Person;
import bbs.entity.Person;
import bbs.others.MD5Util;

public class PersonServiceImpl <T extends Person>extends ServiceImpl<T> 
implements IPersonService<T>{

	@Override
	public boolean create(T person)   {	//创建用户，用于注册
				
		return false;
	}

	@Override
	public String register(Person person, String confirmpassword) {
		String message =new String();
		if(!(confirmpassword.equals(person.getPassword())))
		{
			message = "注册失败，两次密码不一样!";
		}
		else
		{
			person.setPassword(MD5Util.MD5(confirmpassword));
			if(findPersonByAccount(person.getAccount())!=null)
			{
				message="注册失败，已存在同名账号!";	
			}
			else
			{
				person.setIdentity("普通");
				person.setDateCreated(new Date());
				person.setDateLastActived(new Date());
				dao.create((T)person);
			}
		}
		return message;
		
	}
	@Override
	public T findPersonByAccount(String account) {	//根据账号得到用户信息,用于查询信息
		String hql = "from Person p where p.account=? and p.deleted = false";
		Query query = dao.createquery(hql);
		query.setString(0, account);
		T person= (T)query.uniqueResult();
		return person;	
	}

	@Override
	public T loginPerson(String account, String password) {	//根据账号和密码得到用户信息，用于登录
		String hql = "from Person p where p.account=? and p.password=? and p.deleted = false";
		Query query = dao.createquery(hql);
		MD5Util jiami = new MD5Util();
		String encodedpassword =jiami.MD5(password);
		query.setString(0, account);
		query.setString(1, encodedpassword );
		T person= (T)query.uniqueResult();
		if(person!=null)
		{
		person.setDateLastActived(new Date());
		dao.update(person);
		}
		return person;
	}
	
	@Override
	public void update(T basebean) {//更改个人信息
		basebean.setPassword(MD5Util.MD5(basebean.getPassword()));
		dao.update(basebean);
	}
	
	@Override
	public List<Integer> findManagerId(int id) {//获得特定id板块的管理员id
		//String hql = "from Board_Person bp where bp.person.id =24";
		String hql = "from Board b left join fetch b.board_person bp "+
		"where b.id = ?";
		Query query = dao.createquery(hql);
		query.setInteger(0, id);
		Board board = (Board)query.uniqueResult();
		Set<Board_Person> bps = board.getBoard_person();
		List <Integer> managerIds = new ArrayList<Integer>();
		for(Iterator it = bps.iterator();it.hasNext();)
		{
			Board_Person bp = (Board_Person) it.next();
			Integer managerId = bp.getPerson().getId();
			managerIds.add(managerId);
		}
		return managerIds;
	}

	@Override
	public List<Integer> findCommonId(int id) {//根据特定板块id，获得这个板块的普通用户id
		List<Integer> managerIds =  findManagerId(id);
		List<T> allPersons = findAll((Class<T>) Person.class);
		List<Integer> commonIds = new ArrayList<Integer>();
		for(Iterator<T> allit = allPersons.iterator();allit.hasNext();)
		{
			Person person = (Person)allit.next();
			commonIds.add(person.getId());
		}
		for(Iterator<Integer> managerit = managerIds.iterator();managerit.hasNext();)
		{
			Integer managerid =(Integer) managerit.next();
			if(commonIds.contains(managerid))
			{
				commonIds.remove(managerid);
			}
		}
		return commonIds;
	}


}