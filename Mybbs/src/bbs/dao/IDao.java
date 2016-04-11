package bbs.dao;

import java.util.List;

import org.hibernate.Query;

public interface IDao<T> {

	public void create(T basebean);								//����ʵ��
	public void save(T basebean);									//����ʵ��
	public void delete(T basebean);								//ɾ��ʵ��
	public void deleteById(Class<T> clazz,int id);			//����idɾ��ĳ���ض����ʵ��
	public void update(T basebean);								//����ʵ��(����ת��Ϊ�־�̬��
	public T find(Class<T> clazz,int id); 							//����ID��ѯʵ��
	public List<T> findMany(Class<T> clazz,List<Integer> ids); 			//���ݶ��ID��ѯ���ʵ��
	public List<T> findAll(Class<T> clazz);								//��ѯ����ʵ��													
	public Long getTotalCount(Class<T> clazz);				//��ѯĳ�ű��ʵ������
	public Long getPageCount(Long totalCount,Long perCountPage);				//���ҳ��
	public List<T> findnEntity(Class<T> clazz, Long currentPage,Long perCountPage);	//��ҳ��ѯ�ض�������ʵ��
	public Query createquery(String hql); 										//����query
}
