package bbs.entity;
import  java.util.*;
import javax.persistence.*;

@Entity
@Table
public class Replay extends BaseBean{
	private String title;			//??回帖主题，暂时不用
	
	@Basic(fetch= FetchType.LAZY )
	@Column(columnDefinition= "longtext")
	private String content;		//内容
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "author_id")
	private Person author;		//回帖作者
	private int floor;				//楼层数
	
	@ManyToOne(targetEntity = Post.class)
	@JoinColumn(name = "post_id")
	private Post post;				//回复的帖子

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
