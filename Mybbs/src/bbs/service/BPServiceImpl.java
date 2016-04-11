package bbs.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.hibernate.Query;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import bbs.entity.Board;
import bbs.entity.Board_Person;
import bbs.entity.Person;

public class BPServiceImpl <T extends Board_Person>extends ServiceImpl<T> 
implements IBPService<T>{
	@Override
	public void cannelManager(Integer bid,Integer pid)//取消用户pid的bid板块管理员身份
	{
		/*String hql = "from Board b left join fetch b.board_person bp "+
		"where b.id = ?";
		Query query = dao.createquery(hql);
		query.setInteger(0, bid);
		Board board = (Board)query.uniqueResult();
		Set<Board_Person> bps = board.getBoard_person();
		for(Iterator it = bps.iterator();it.hasNext();)
		{
			Board_Person bp = (Board_Person) it.next();
			Integer  managerId = bp.getPerson().getId();
			if(managerId == pid)
				dao.update(bp);//将游离态转化为持久态
				bp.setBoard(null);
				bp.setPerson(null);
				delete(bp);
				break;
			}
		}
		*/
		String hql = "from Board_Person bp where bp.board.id=? and bp.person.id=?";
		Query query = dao.createquery(hql);
		query.setInteger(0, bid);
		query.setInteger(1, pid);
		T bp = (T)query.uniqueResult();
	//	Board_Person bp = (Board_Person)query.uniqueResult();
		dao.delete(bp);
	//	return bp;
	}

	@Override
	public void setManager(Integer bid, Integer pid) {//设置用户pid的bid板块管理员身份
		String hql1 = "from Board b where b.id = ?";
				Query query1 = dao.createquery(hql1);
				query1.setInteger(0, bid);
				Board board = (Board)query1.uniqueResult();
		String hql2 = "from Person p where p.id = ?";
				Query query2 = dao.createquery(hql2);
				query2.setInteger(0, pid);
				Person person = (Person)query2.uniqueResult();
				Board_Person bp = new Board_Person();
				bp.setBoard(board);
				bp.setPerson(person);
				bp.setDateCreated(new Date());
				dao.save((T)bp);
	}

	@Override
	public boolean create(T basebean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(T basebean) {
		// TODO Auto-generated method stub
		
	}
}
