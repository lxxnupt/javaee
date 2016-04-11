package bbs.service;

import java.util.List;

import bbs.entity.Person;
import bbs.entity.Post;
import bbs.others.Page;

public interface IPostService <T extends Post>extends IService<T>{
	public List<Post> findPostByBoardId(Integer id,Long currentPage);	//根据板块id返回这个板块某一页的5个帖子 
	public Page page(Integer id,Long currentPage);								//获得当前页的page对象,id为板块id
	public List<Person> find5Author(List<Post> posts);							//根据5个帖子返回这5个帖子的作者
	public List<Person> find5LastPerson(List<Post> posts) ;					//根据5个帖子得到这5个帖子的最后回帖人
	public void addpost(Integer bid,Integer pid,Post post);						//发帖
	public void setLastPerson(Integer postid,Integer authorid);				//将postid的帖子的作者设置为authorid的人,同时这个帖子的回帖数+1
	public void addPostHit(Integer id,Long currentPage);						//将已知id的帖子的点击量增加1
	public void lastreplaytime(Integer postid,Integer replayid);				//帖子已知id，以及回帖对象。更新帖子的最后回帖时间
	public void deletereplaycount(Integer postid);									//帖子的回帖数减1
	public Long deletepost(Integer id,Long currentPage,Long postnumber);//删除帖子,返回删除后的页码
	public Integer findboardid(Integer id);												//根据帖子id获得它所属的板块id
}
