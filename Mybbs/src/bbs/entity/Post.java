package bbs.entity;
import  java.util.*;
import javax.persistence.*;


@Entity
@Table
public class Post extends BaseBean{
	private String title;							//标题
	
	@Basic(fetch= FetchType.LAZY )
	@Column(columnDefinition= "longtext")
	private String content;						//内容
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "author_id")
	private Person author;						//作者
	private Integer hit =0;							//点击数
	private Integer replayCount =0;				//回帖数
	private Date dateLastReplied;			//最后回帖时间
	private boolean readonly;				//是否只读
	private boolean topped;					//是否置顶
	
	@ManyToOne(targetEntity = Board.class)
	@JoinColumn(name = "board_id")
	private Board board;						//属于哪个版面
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "authorLastReplied_id")
	private Person authorLastReplied;	//最后回帖人
	
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
