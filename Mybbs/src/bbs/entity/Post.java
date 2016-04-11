package bbs.entity;
import  java.util.*;
import javax.persistence.*;


@Entity
@Table
public class Post extends BaseBean{
	private String title;							//����
	
	@Basic(fetch= FetchType.LAZY )
	@Column(columnDefinition= "longtext")
	private String content;						//����
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "author_id")
	private Person author;						//����
	private Integer hit =0;							//�����
	private Integer replayCount =0;				//������
	private Date dateLastReplied;			//������ʱ��
	private boolean readonly;				//�Ƿ�ֻ��
	private boolean topped;					//�Ƿ��ö�
	
	@ManyToOne(targetEntity = Board.class)
	@JoinColumn(name = "board_id")
	private Board board;						//�����ĸ�����
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "authorLastReplied_id")
	private Person authorLastReplied;	//��������
	
	@OneToMany(targetEntity = Replay.class, cascade=CascadeType.ALL,mappedBy = "post")
	private Set<Replay> replay = new HashSet<Replay>();			

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

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public Integer getReplayCount() {
		return replayCount;
	}

	public void setReplayCount(Integer replayCount) {
		this.replayCount = replayCount;
	}

	public Date getDateLastReplied() {
		return dateLastReplied;
	}

	public void setDateLastReplied(Date dateLastReplied) {
		this.dateLastReplied = dateLastReplied;
	}

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public boolean isTopped() {
		return topped;
	}

	public void setTopped(boolean topped) {
		this.topped = topped;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Person getAuthorLastReplied() {
		return authorLastReplied;
	}

	public void setAuthorLastReplied(Person authorLastReplied) {
		this.authorLastReplied = authorLastReplied;
	}
	
	
}
