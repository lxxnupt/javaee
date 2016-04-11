package bbs.entity;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table
public class Person extends BaseBean{
	@NotEmpty(message="NotEmpty.user.account")
	private String account;			//账号
	@NotEmpty(message="NotEmpty.user.password")
	private String password;		//密码
	private String sex;				//性别
	@Pattern(regexp="\\S{6,30}",message="Pattern.user.name")
	private String name;				//姓名
	private String birthday;			//生日
	private String email;				//电子邮件
	@Temporal(value = TemporalType.TIMESTAMP)		
	private Date dateLastActived;					//最后登录时间
	private String identity;								//身份
	
	// targetEntity 类似 <one-to-many class="">
	// mappedBy 作用 inverse=true
	@OneToMany(targetEntity = Board_Person.class, cascade=CascadeType.ALL,mappedBy = "person")//person对应Board_Person的person属性
	private Set<Board_Person> board_person = new HashSet<Board_Person>();			//管理的版面中间表

	@OneToMany(targetEntity = Post.class, cascade=CascadeType.ALL,mappedBy = "author")
	private Set<Post> post = new HashSet<Post>();		

	@OneToMany(targetEntity = Post.class, cascade=CascadeType.ALL,mappedBy = "authorLastReplied")
	private Set<Post> lastpost = new HashSet<Post>();		
	
	@OneToMany(targetEntity = Replay.class, cascade=CascadeType.ALL,mappedBy = "author")
	private Set<Replay> replay = new HashSet<Replay>();

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateLastActived() {
		return dateLastActived;
	}

	public void setDateLastActived(Date dateLastActived) {
		this.dateLastActived = dateLastActived;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public Set<Board_Person> getBoard_person() {
		return board_person;
	}

	public void setBoard_person(Set<Board_Person> board_person) {
		this.board_person = board_person;
	}

	public Set<Post> getPost() {
		return post;
	}

	public void setPost(Set<Post> post) {
		this.post = post;
	}

	public Set<Post> getLastpost() {
		return lastpost;
	}

	public void setLastpost(Set<Post> lastpost) {
		this.lastpost = lastpost;
	}

	public Set<Replay> getReplay() {
		return replay;
	}

	public void setReplay(Set<Replay> replay) {
		this.replay = replay;
	}			

}
