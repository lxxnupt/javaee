package bbs.service;

import java.util.List;
import org.hibernate.Query;

public interface IService<T> {

	public boolean create(T basebean);								//����ʵ��
	public void delete(T basebean);									//ɾ��ʵ��
	public void deleteById(Class<T> clazz,int id);				//����idɾ��ĳ���ض����ʵ��
	public void update(T basebean);									//����ʵ��
	public T find(Class<T> clazz,int id); 								//����ID��ѯʵ��
	public List<T> findMany(Class<T> clazz,List<Integer> ids); 			//���ݶ��ID��ѯ���ʵ��
	public List<T> findAll(Class<T> clazz);							//��ѯ����ʵ��
	public Long getTotalCount(Class<T> clazz);					//��ѯĳ�ű��ʵ������
	public Long getPageCount(Long totalCount,Long perCountPage);				//���ҳ��
	public List<T> findnEntity(Class<T> clazz, Long currentPage,Long perCountPage);			//��ҳ��ѯ�ض�������ʵ��
}
