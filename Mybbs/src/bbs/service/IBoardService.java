package bbs.service;

import bbs.entity.Board;
import bbs.entity.Person;

import java.util.List;

public interface IBoardService<T extends Board>extends IService<T>{
	public T findBoardByName(String name);				//�������Ƶõ�������Ϣ,���ڲ�ѯ����
	public void updateBoard(T board,String oldname);				//����oldname����¼��İ����Ϣ���°��
}
