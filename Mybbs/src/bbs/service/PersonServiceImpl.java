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
	public boolean create(T person)   {	//�����û�������ע��
				
		return false;
	}

	@Override
	public String register(Person person, String confirmpassword) {
		String message =new String();
		if(!(confirmpassword.equals(person.getPassword())))
		{
			message = "ע��ʧ�ܣ��������벻һ��!";
		}
		else
		{
			person.setPassword(MD5Util.MD5(confirmpassword));
			if(findPersonByAccount(person.getAccount())!=null)
			{
				message="ע��ʧ�ܣ��Ѵ���ͬ���˺�!";	
			}
			else
			{
				person.setIdentity("��ͨ");
				person.setDateCreated(new Date());
				person.setDateLastActived(new Date());
				dao.create((T)person);
			}
		}
		return message;
		
	}
	@Override
	public T findPersonByAccount(String account) {	//�����˺ŵõ��û���Ϣ,���ڲ�ѯ��Ϣ
		String hql = "from Person p where p.account=? and p.deleted = false";
		Query query = dao.createquery(hql);
		query.setString(0, account);
		T person= (T)query.uniqueResult();
		return person;	
	}

	@Override
	public T loginPerson(String account, String password) {	//�����˺ź�����õ��û���Ϣ�����ڵ�¼
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
	public void update(T basebean) {//���ĸ�����Ϣ
		basebean.setPassword(MD5Util.MD5(basebean.getPassword()));
		dao.update(basebean);
	}
	
	@Override
	public List<Integer> findManagerId(int id) {//����ض�id���Ĺ���Աid
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
	public List<Integer> findCommonId(int id) {//�����ض����id��������������ͨ�û�id
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