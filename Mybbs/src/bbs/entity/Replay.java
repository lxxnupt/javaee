package bbs.entity;
import  java.util.*;
import javax.persistence.*;

@Entity
@Table
public class Replay extends BaseBean{
	private String title;			//??�������⣬��ʱ����
	
	@Basic(fetch= FetchType.LAZY )
	@Column(columnDefinition= "longtext")
	private String content;		//����
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "author_id")
	private Person author;		//��������
	private int floor;				//¥����
	
	@ManyToOne(targetEntity = Post.class)
	@JoinColumn(name = "post_id")
	private Post post;				//�ظ�������

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
