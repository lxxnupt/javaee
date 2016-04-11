package bbs.service;

import java.util.List;

import bbs.entity.Person;
import bbs.entity.Post;
import bbs.others.Page;

public interface IPostService <T extends Post>extends IService<T>{
	public List<Post> findPostByBoardId(Integer id,Long currentPage);	//���ݰ��id����������ĳһҳ��5������ 
	public Page page(Integer id,Long currentPage);								//��õ�ǰҳ��page����,idΪ���id
	public List<Person> find5Author(List<Post> posts);							//����5�����ӷ�����5�����ӵ�����
	public List<Person> find5LastPerson(List<Post> posts) ;					//����5�����ӵõ���5�����ӵ���������
	public void addpost(Integer bid,Integer pid,Post post);						//����
	public void setLastPerson(Integer postid,Integer authorid);				//��postid�����ӵ���������Ϊauthorid����,ͬʱ������ӵĻ�����+1
	public void addPostHit(Integer id,Long currentPage);						//����֪id�����ӵĵ��������1
	public void lastreplaytime(Integer postid,Integer replayid);				//������֪id���Լ��������󡣸������ӵ�������ʱ��
	public void deletereplaycount(Integer postid);									//���ӵĻ�������1
	public Long deletepost(Integer id,Long currentPage,Long postnumber);//ɾ������,����ɾ�����ҳ��
	public Integer findboardid(Integer id);												//��������id����������İ��id
}
