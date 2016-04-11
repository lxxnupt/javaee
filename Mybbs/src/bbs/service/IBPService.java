package bbs.service;

import bbs.entity.Board_Person;

public interface IBPService <T extends Board_Person>extends IService<T>  {
	public void cannelManager(Integer bid,Integer pid);			//取消用户pid的bid板块管理员身份
	public void setManager(Integer bid,Integer pid);			//设置用户pid的bid板块管理员身份
}
