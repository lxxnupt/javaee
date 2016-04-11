package bbs.entity;
import  java.util.*;
import javax.persistence.*;

@Entity
@Table
public class Board extends BaseBean{
	private String name;					//�������
	private String description;		//�������
	private Integer psodCount = 0;		//�������
	private Integer replayCount = 0;		//���ظ���
	
	@OneToOne(cascade=CascadeType.ALL,targetEntity = Post.class)
	@JoinColumn(name="lastPost_id",unique=true,nullable=true)
	private Post lastPost ;				//�������

	@OneToOne(cascade=CascadeType.ALL,targetEntity = Replay.class)
	@JoinColumn(name="lastReplay_id",unique=true,nullable=true)
	private Replay lastReplay;			//������	
	// targetEntity ���� <one-to-many class="">
	// mappedBy ���� inverse=true
	@OneToMany(targetEntity = Board_Person.class, cascade=CascadeType.ALL,mappedBy = "board")//board��ӦBoard_Person��board����
	private Set<Board_Person> board_person = new HashSet<Board_Person>();			//����İ����м��
	
	@OneToMany(targetEntity = Post.class, cascade=CascadeType.ALL,mappedBy = "board")
	private Set<Post> posts = new HashSet<Post>();			
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getPsodCount() {
		return psodCount;
	}
	public void setPsodCount(Integer psodCount) {
		this.psodCount = psodCount;
	}
	public Integer getReplayCount() {
		return replayCount;
	}
	public void setReplayCount(Integer replayCount) {
		this.replayCount = replayCount;
	}
	public Post getLastPost() {
		return lastPost;
	}
	public void setLastPost(Post lastPost) {
		this.lastPost = lastPost;
	}
	public Replay getLastReplay() {
		return lastReplay;
	}
	public void setLastReplay(Replay lastReplay) {
		this.lastReplay = lastReplay;
	}
	public Set<Board_Person> getBoard_person() {
		return board_person;
	}
	public void setBoard_person(Set<Board_Person> board_person) {
		this.board_person = board_person;
	}
	public Set<Post> getPosts() {
		return posts;
	}
	public void setPosts(Set<Post> posts) {
		this.posts = posts;
	}
	
}
