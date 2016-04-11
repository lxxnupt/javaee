package bbs.service;

import java.util.List;

import bbs.entity.Person;

public interface IPersonService<T extends Person>extends IService<T> {
	public String register(Person person,String confirmpassword);//ע���˺�
	public T findPersonByAccount(String account);				//�����˺ŵõ��û���Ϣ,���ڲ�ѯ��Ϣ
	public T loginPerson(String account,String password);		//�����˺ź�����õ��û���Ϣ�����ڵ�¼
	public List<Integer>  findManagerId(int id);		//�����ض����id�����������Ĺ���Աid
	public List<Integer>  findCommonId(int id);	//�����ض����id��������������ͨ�û�id
}
