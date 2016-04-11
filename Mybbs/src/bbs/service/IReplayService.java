package bbs.service;

import java.util.List;
import bbs.entity.Person;
import bbs.entity.Replay;
import bbs.others.Louzhu;
import bbs.others.Page;

public interface IReplayService <T extends Replay>extends IService<T>{
	public Louzhu louzhu(Integer id);																//��������id���¥����Ϣ
	public Page page(Integer id,Long currentPage);										//���ݵ�ǰҳ������id���Page����
	public  List<Replay> findReplay(Integer id,Long currentPage);					//��������id����ǰҳ���������ӵ�2��3������	
	public 	List<Person> find23Author(List<Replay> replays);							//�õ���2��3�����ӵ�����
	public 	void addreplay(Replay replay ,Integer postid,Integer authorid);		//����
	public Long addreplayyema(Long currentPage,Long replaynumber);			//���ػ�����ҳ��
	public Long deletereplay(Integer id,Long replaynumber,Long currentPage);			//ɾ������������ҳ��
}
