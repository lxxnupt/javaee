package bbs.service;

import bbs.entity.Board_Person;

public interface IBPService <T extends Board_Person>extends IService<T>  {
	public void cannelManager(Integer bid,Integer pid);			//ȡ���û�pid��bid������Ա���
	public void setManager(Integer bid,Integer pid);			//�����û�pid��bid������Ա���
}
