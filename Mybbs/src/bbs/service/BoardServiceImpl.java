package bbs.service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;

import bbs.entity.Board;
import bbs.entity.Board_Person;
import bbs.entity.Person;

public class BoardServiceImpl <T extends Board>extends ServiceImpl<T> 
implements IBoardService<T>{

	@Override
	public T findBoardByName(String name) {//�������Ƶõ�������Ϣ,���ڲ�ѯ����
		String hql = "from Board b where b.name=? and b.deleted = false";
		Query query = dao.createquery(hql);
		query.setString(0, name);
		T board= (T)query.uniqueResult();
		return board;	
	}

	@Override
	public boolean create(T board) {//��������
		String newname = board.getName();
		if(findBoardByName(newname)==null)//���ͬ������Ƿ����
		{
				board.setDateCreated(new Date());
				dao.create(board);
				return true;
		}
		else{
			System.out.println("����Ѿ����ڣ�");
			return false;
		}
	}

	@Override
	public void update(T basebean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard(T board, String oldname) {//����
		String name = board.getName();
		String description = board.getDescription();
		board = findBoardByName(oldname);
		board.setName(name);
		board.setDescription(description);
		dao.update(board);
	}


	
}
