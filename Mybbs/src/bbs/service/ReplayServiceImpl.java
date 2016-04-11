package bbs.service;

import java.util.*;
import org.hibernate.Query;
import bbs.entity.*;
import bbs.others.Louzhu;
import bbs.others.Page;

public class ReplayServiceImpl <T extends Replay>extends ServiceImpl<T> 
implements IReplayService<T>{

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
	public Louzhu louzhu(Integer id) {
		String hql = "from Post post where post.id="+id;
		Post post = (Post)dao.createquery(hql).uniqueResult();
		String title = post.getTitle();
		String louzhucontent = post.getContent();
		String account = post.getAuthor().getAccount();
		Date date = post.getDateCreated();
		Integer postid = post.getId();		
		Louzhu louzhu =new Louzhu(title,louzhucontent,account,date,postid);
		return louzhu;
	}
	@Override
	public Page page(Integer id, Long currentPage) {
		String hql = "select count(*) from Replay replay where replay.post.id=?";
		Query query = dao.createquery(hql);
		query.setLong(0, id);
		Long totalCount = (Long)query.uniqueResult()+1;  //得到这个帖子的回帖数量(加1是加上原来帖子)
		Long pageCount = getPageCount(totalCount,(long) 3);			//算出页数
		String biaozhi =new String();
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
	public List<Replay> findReplay(Integer id, Long currentPage) {
		Integer maxresult;
		String hql = "from  Replay replay where replay.post.id=? order by replay.id ";
		Query query = dao.createquery(hql);
		query.setInteger(0, id);
		if(currentPage==1){
			maxresult=2;
			query.setFirstResult(0);
			query.setMaxResults(maxresult);
		}
		else{
			maxresult=3;
			query.setFirstResult(3*(currentPage.intValue()-1)-1);
			query.setMaxResults(maxresult);
		}
		List<Replay> replays = query.list();
		return replays;
	}

	@Override
	public List<Person> find23Author(List<Replay> replays) {
		List<Person> authors = new ArrayList<Person>();
		for(Iterator<Replay> it =  replays.iterator();it.hasNext();)
		{
				Replay replay = it.next();
				Person author = replay.getAuthor(); 
				authors.add(author);
		}
		return authors;
	}

	@Override
	public void addreplay(Replay replay,Integer postid,Integer authorid){		//回帖
		String hql1 = "from Post post where post.id="+postid;
		Post post = (Post)dao.createquery(hql1).uniqueResult();
		String hql2 = "from Person person where person.id="+authorid;
		Person author = (Person)dao.createquery(hql2).uniqueResult();		
		replay.setAuthor(author);
		replay.setPost(post);
		replay.setDateCreated(new Date());
		//author.setDateLastActived(new Date());
		String hql3 = "select max(replay.floor) from Replay replay where replay.post.id="+postid;
		Integer maxfloor = (Integer)dao.createquery(hql3).uniqueResult();
		if(maxfloor==null){
			replay.setFloor(2);
		}
		else{
			replay.setFloor(maxfloor+1);
		}
		dao.save((T)replay);
	}
	
	@Override
	public Long addreplayyema(Long currentPage, Long replaynumber) {
		if(currentPage==1&&replaynumber==2){
			currentPage=(long)2;
		}
		else{
					if(currentPage>=2&&replaynumber==3){
						currentPage+=1;
					}
			}
		return currentPage;
	}
	@Override
	public Long deletereplay(Integer id,Long replaynumber,Long currentPage) {
		if(replaynumber==1&&currentPage!=1){
			currentPage = (long)1;
		}
		Replay replay = (Replay)dao.find((Class<T>) Replay.class, id);
		dao.delete((T)replay);
		return currentPage;
	}

}