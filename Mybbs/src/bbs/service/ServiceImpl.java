package bbs.service;

import java.util.Iterator;
import java.util.List;
import bbs.dao.*;
import bbs.entity.*;

public abstract class ServiceImpl<T extends BaseBean> implements IService<T> {

	protected IDao<T> dao;							//DAO����
	public IDao<T> getDao() {
		return dao;
	}
	public void setDao(IDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public abstract boolean create(T basebean); //�½�ʵ��
		
	@Override
	public void delete(T basebean) {//ɾ��ʵ��
		dao.delete(basebean);
	}
	@Override
	public void deleteById(Class<T> clazz,int id)			//����idɾ��ĳ���ض����ʵ��
	{
		dao.deleteById(clazz, id);
	}
	@Override
	public abstract void update(T basebean);
	@Override
	public T find(Class clazz, int id) {//����ID��ѯʵ��
		return (T)dao.find(clazz, id);	
	}
	
	@Override
	public List<T> findMany(Class<T> clazz,List<Integer> ids)
	{
		return dao.findMany(clazz, ids);
	}
	@Override
	public List<T> findAll(Class<T> clazz){ //��ѯ����ʵ��		
		return dao.findAll(clazz);
	}

	@Override
	public Long getTotalCount(Class clazz) {//��ѯĳ�ű��ʵ������
		return dao.getTotalCount(clazz);
	}
	
	@Override
	public Long getPageCount(Long totalCount, Long perCountPage) {//���ҳ��
		return dao.getPageCount(totalCount, perCountPage);
	}

	@Override
	public List<T> findnEntity(Class clazz, Long currentPage, Long perCountPage) {//��ҳ��ѯ�ض�������ʵ��
		return (List<T>)dao.findnEntity(clazz, currentPage, perCountPage);
	}
}