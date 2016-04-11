package bbs.service;

import bbs.entity.Board;
import bbs.entity.Person;

import java.util.List;

public interface IBoardService<T extends Board>extends IService<T>{
	public T findBoardByName(String name);				//根据名称得到版面信息,用于查询版面
	public void updateBoard(T board,String oldname);				//根据oldname和新录入的板块信息更新板块
}
