package bbs.service;

import java.util.List;
import bbs.entity.Person;
import bbs.entity.Replay;
import bbs.others.Louzhu;
import bbs.others.Page;

public interface IReplayService <T extends Replay>extends IService<T>{
	public Louzhu louzhu(Integer id);																//根据帖子id获得楼主信息
	public Page page(Integer id,Long currentPage);										//根据当前页和帖子id获得Page对象
	public  List<Replay> findReplay(Integer id,Long currentPage);					//根据帖子id，当前页获得这个帖子的2到3个回帖	
	public 	List<Person> find23Author(List<Replay> replays);							//得到这2到3个帖子的作者
	public 	void addreplay(Replay replay ,Integer postid,Integer authorid);		//回帖
	public Long addreplayyema(Long currentPage,Long replaynumber);			//返回回帖的页码
	public Long deletereplay(Integer id,Long replaynumber,Long currentPage);			//删除回帖，返回页码
}
