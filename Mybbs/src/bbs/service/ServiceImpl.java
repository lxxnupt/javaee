package bbs.service;

import java.util.Iterator;
import java.util.List;
import bbs.dao.*;
import bbs.entity.*;

public abstract class ServiceImpl<T extends BaseBean> implements IService<T> {

	protected IDao<T> dao;							//DAO对象
	public IDao<T> getDao() {
		return dao;
	}
	public void setDao(IDao<T> dao) {
		this.dao = dao;
	}

	@Override
	public abstract boolean create(T basebean); //新建实体
		
	@Override
	public void delete(T basebean) {//删除实体
		dao.delete(basebean);
	}
	@Override
	public void deleteById(Class<T> clazz,int id)			//根据id删除某个特定类的实体
	{
		dao.deleteById(clazz, id);
	}
	@Override
	public abstract void update(T basebean);
	@Override
	public T find(Class clazz, int id) {//根据ID查询实体
		return (T)dao.find(clazz, id);	
	}
	
	@Override
	public List<T> findMany(Class<T> clazz,List<Integer> ids)
	{
		return dao.findMany(clazz, ids);
	}
	@Override
	public List<T> findAll(Class<T> clazz){ //查询所有实体		
		return dao.findAll(clazz);
	}

	@Override
	public Long getTotalCount(Class clazz) {//查询某张表的实体总数
		return dao.getTotalCount(clazz);
	}
	
	@Override
	public Long getPageCount(Long totalCount, Long perCountPage) {//算出页数
		return dao.getPageCount(totalCount, perCountPage);
	}

	@Override
	public List<T> findnEntity(Class clazz, Long currentPage, Long perCountPage) {//分页查询特定数量的实体
		return (List<T>)dao.findnEntity(clazz, currentPage, perCountPage);
	}
}