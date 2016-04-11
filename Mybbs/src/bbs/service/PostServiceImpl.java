package bbs.service;

import java.util.*;
import java.util.Iterator;
import org.hibernate.Query;
import bbs.entity.*;
import bbs.others.Page;

public class PostServiceImpl <T extends Post>extends ServiceImpl<T> 
implements IPostService<T>{

	@Override
	public boolean create(T basebean) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void update(T basebean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Post> findPostByBoardId(Integer id,Long currentPage) {//根据板块id,当前页数返回这个板块的这一页的帖子
		String hql = "from Post post where post.board.id=? order by post.id desc";
		Query query = dao.createquery(hql);
		query.setInteger(0, id);
		query.setFirstResult(5*(currentPage.intValue()-1));
		query.setMaxResults(5);
		List<Post> posts = query.list();
		return posts;
	}
	
	@Override
	public Page page(Integer id,Long currentPage) {
		//	String hql = "select count(*) from Post post ";
		String hql = "select count(*) from Post post where post.board.id=?";
		Query query = dao.createquery(hql);
		query.setLong(0, id);
		Long totalCount = (Long)query.uniqueResult();  //得到这个板块的帖子数量
		Long pageCount = getPageCount(totalCount,(long) 5);			//算出页数
		String biaozhi =new String();
		if(totalCount == 0)
		{
			totalCount =(long)1;
		}
		if(currentPage == 1&& pageCount==1)
		{
			biaozhi = "onlyone";
		}
		else
		{
			if(currentPage==1&&pageCount!=1)
			{
				biaozhi = "first";
			}
			else
			{
				if(currentPage==pageCount&&currentPage!=1)
				{
					biaozhi ="last";
				}
				else
				{
					biaozhi = "middle";
				}
			}
		}
		Page page = new Page(currentPage,pageCount,biaozhi);
		return page;
	}

	@Override
	public List<Person> find5Author(List<Post> posts) {//根据5个帖子返回这5个帖子的作者
		List<Person> authors = new ArrayList<Person>();
		for(Iterator<Post> it =  posts.iterator();it.hasNext();)
		{
				Post post = it.next();
				Person author = post.getAuthor(); 
				authors.add(author);
		}
		return authors;
	}

	@Override
	public List<Person> find5LastPerson(List<Post> posts) {//根据5帖子得到这5个帖子的最后回帖人
		List<Person> authorslast = new ArrayList<Person>();
		for(Iterator<Post> it =  posts.iterator();it.hasNext();)
		{
				Post post = it.next();
				Person authorlast = post.getAuthorLastReplied(); 
				authorslast.add(authorlast);
		}
		return authorslast;
	}

	@Override
	public void addpost(Integer bid,Integer pid, Post post) {
		String hql1 = "from Board board where board.id=?";
		Query query1 = dao.createquery(hql1);
		Board board = (Board)query1.setInteger(0, bid).uniqueResult();
		String hql2 = "from Person person where person.id=?";
		Query query2 = dao.createquery(hql2);
		Person author = (Person)query2.setInteger(0, pid).uniqueResult();		
		post.setBoard(board);
		post.setAuthor(author);
		post.setDateCreated(new Date());
		dao.save((T)post);
	}

	@Override
	public void setLastPerson(Integer postid, Integer authorid) {
		Post post = dao.find((Class<T>) Post.class,postid);
		String hql = "from Person p where p.id="+authorid;
		Person lastperson = (Person)dao.createquery(hql).uniqueResult();		
		post.setAuthorLastReplied(lastperson);
		post.setReplayCount(post.getReplayCount()+1);
	}

	@Override
	public void addPostHit(Integer id, Long currentPage) {
		Post post =dao.find((Class<T>)Post.class, id);
		post.setHit(post.getHit()+1);
	}

	@Override
	public void lastreplaytime(Integer postid, Integer replayid) {
		Post post =dao.find((Class<T>)Post.class, postid);		
		String hql = "from Replay replay where replay.id="+replayid;
		Replay replay = (Replay)dao.createquery(hql).uniqueResult();
		post.setDateLastReplied(replay.getDateCreated());
	}

	@Override
	public void deletereplaycount(Integer postid) {
		Post post = dao.find((Class<T>)Post.class, postid);
		post.setReplayCount(post.getReplayCount()-1);
	}

	@Override
	public Long deletepost(Integer id, Long currentPage, Long postnumber) {
		if(postnumber==1&&currentPage!=1){
			currentPage=(long)1;
		}
		Post post = dao.find((Class<T>)Post.class,id);
		dao.delete((T)post);
		return currentPage;
	}

	@Override
	public Integer findboardid(Integer id) {
		String hql ="from Post p left join fetch p.board b where p.id="+id;
		Post post =(Post)dao.createquery(hql).uniqueResult();
		Integer boardid = post.getBoard().getId();
		return 	boardid;
	}
}