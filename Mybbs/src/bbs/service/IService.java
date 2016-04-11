package bbs.service;

import java.util.List;
import org.hibernate.Query;

public interface IService<T> {

	public boolean create(T basebean);								//创建实体
	public void delete(T basebean);									//删除实体
	public void deleteById(Class<T> clazz,int id);				//根据id删除某个特定类的实体
	public void update(T basebean);									//更改实体
	public T find(Class<T> clazz,int id); 								//根据ID查询实体
	public List<T> findMany(Class<T> clazz,List<Integer> ids); 			//根据多个ID查询多个实体
	public List<T> findAll(Class<T> clazz);							//查询所有实体
	public Long getTotalCount(Class<T> clazz);					//查询某张表的实体总数
	public Long getPageCount(Long totalCount,Long perCountPage);				//算出页数
	public List<T> findnEntity(Class<T> clazz, Long currentPage,Long perCountPage);			//分页查询特定数量的实体
}
