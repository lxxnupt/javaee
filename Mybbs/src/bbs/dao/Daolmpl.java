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
	public void create(T basebean) {//创建实体
		this.sessionFactory.getCurrentSession().save(basebean);
	}
	@Override
	public void save(T basebean) {//保存实体
		create(basebean);
	}

	@Override
	public void delete(T basebean) {//删除实体
		this.sessionFactory.getCurrentSession().delete(basebean);
	}
	@Override
	@SuppressWarnings("unchecked")
	public void deleteById(Class<T> clazz,int id) {//根据id删除实体
		T t = find(clazz, id);
		delete(t);
	}
	@Override
	public void update(T basebean) {//更新实体
		this.sessionFactory.getCurrentSession().update(basebean);
	}
	@Override
	@SuppressWarnings("unchecked")
	public T find(Class<T> clazz, int id) {//根据ID查询实体，这个clazz的实参的形式类似Board.class
		
		return (T) sessionFactory.getCurrentSession().load(clazz, id);
	}
	@Override
	public List<T> findMany(Class<T> clazz, List<Integer> ids) {	//根据多个ID查询多个实体
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
	public List<T> findAll(Class<T> clazz){ //查询所有实体
		String tablename = clazz.getName();		
		String hql = "from "+tablename;
		Query query = createquery(hql);
		List<T> table =  query.list();
		return table;
	}
	@Override
	@SuppressWarnings("unchecked")
	public Long getTotalCount(Class<T> clazz) {//查询这张数据表共有多少行
		String tablename = clazz.getName();
		String hql = "select count(*) from "+tablename;
		Long totalCount = (Long)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();  
		return totalCount;
	}

	@Override
	public Long getPageCount(Long totalCount, Long perCountPage) {//算出页数
			Long pageCount;
			if(totalCount ==0){
				pageCount = (long) 1;}
			else{
				pageCount =(long)Math.floor((totalCount-1)/perCountPage)+1;}
			return pageCount;
		}
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findnEntity(Class<T> clazz, Long currentPage,Long perCountPage) {//查询某一页特定数量的实体
		String tablename = clazz.getName();		
		String hql = "from "+tablename;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(perCountPage.intValue()*(currentPage.intValue()-1));
		query.setMaxResults(perCountPage.intValue());
		List<T> table = query.list();
		return table;
	}
	@Override
	public Query createquery(String hql) {	//创建query
		return sessionFactory.getCurrentSession().createQuery(hql);
	}


	
}