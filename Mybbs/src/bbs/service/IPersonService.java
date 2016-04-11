package bbs.service;

import java.util.List;

import bbs.entity.Person;

public interface IPersonService<T extends Person>extends IService<T> {
	public String register(Person person,String confirmpassword);//注册账号
	public T findPersonByAccount(String account);				//根据账号得到用户信息,用于查询信息
	public T loginPerson(String account,String password);		//根据账号和密码得到用户信息，用于登录
	public List<Integer>  findManagerId(int id);		//根据特定板块id，获得这个板块的管理员id
	public List<Integer>  findCommonId(int id);	//根据特定板块id，获得这个板块的普通用户id
}
