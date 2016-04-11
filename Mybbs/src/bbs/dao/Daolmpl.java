package bbs.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;

public class Daolmpl<T> implements IDao<T>{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@Override
	public void create(T basebean) {//����ʵ��
		this.sessionFactory.getCurrentSession().save(basebean);
	}
	@Override
	public void save(T basebean) {//����ʵ��
		create(basebean);
	}

	@Override
	public void delete(T basebean) {//ɾ��ʵ��
		this.sessionFactory.getCurrentSession().delete(basebean);
	}
	@Override
	@SuppressWarnings("unchecked")
	public void deleteById(Class<T> clazz,int id) {//����idɾ��ʵ��
		T t = find(clazz, id);
		delete(t);
	}
	@Override
	public void update(T basebean) {//����ʵ��
		this.sessionFactory.getCurrentSession().update(basebean);
	}
	@Override
	@SuppressWarnings("unchecked")
	public T find(Class<T> clazz, int id) {//����ID��ѯʵ�壬���clazz��ʵ�ε���ʽ����Board.class
		
		return (T) sessionFactory.getCurrentSession().load(clazz, id);
	}
	@Override
	public List<T> findMany(Class<T> clazz, List<Integer> ids) {	//���ݶ��ID��ѯ���ʵ��
		Iterator<Integer> it = ids.iterator();
		List<T> ts = new ArrayList<T>();
		while(it.hasNext())
		{
			T t = (T)sessionFactory.getCurrentSession().load(clazz,(Integer)it.next());
			ts.add(t);
		}
		return ts;
	}
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> clazz){ //��ѯ����ʵ��
		String tablename = clazz.getName();		
		String hql = "from "+tablename;
		Query query = createquery(hql);
		List<T> table =  query.list();
		return table;
	}
	@Override
	@SuppressWarnings("unchecked")
	public Long getTotalCount(Class<T> clazz) {//��ѯ�������ݱ��ж�����
		String tablename = clazz.getName();
		String hql = "select count(*) from "+tablename;
		Long totalCount = (Long)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();  
		return totalCount;
	}

	@Override
	public Long getPageCount(Long totalCount, Long perCountPage) {//���ҳ��
			Long pageCount;
			if(totalCount ==0){
				pageCount = (long) 1;}
			else{
				pageCount =(long)Math.floor((totalCount-1)/perCountPage)+1;}
			return pageCount;
		}
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findnEntity(Class<T> clazz, Long currentPage,Long perCountPage) {//��ѯĳһҳ�ض�������ʵ��
		String tablename = clazz.getName();		
		String hql = "from "+tablename;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(perCountPage.intValue()*(currentPage.intValue()-1));
		query.setMaxResults(perCountPage.intValue());
		List<T> table = query.list();
		return table;
	}
	@Override
	public Query createquery(String hql) {	//����query
		return sessionFactory.getCurrentSession().createQuery(hql);
	}


	
}