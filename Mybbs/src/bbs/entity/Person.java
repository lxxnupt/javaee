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
	private String account;			//�˺�
	@NotEmpty(message="NotEmpty.user.password")
	private String password;		//����
	private String sex;				//�Ա�
	@Pattern(regexp="\\S{6,30}",message="Pattern.user.name")
	private String name;				//����
	private String birthday;			//����
	private String email;				//�����ʼ�
	@Temporal(value = TemporalType.TIMESTAMP)		
	private Date dateLastActived;					//����¼ʱ��
	private String identity;								//���
	
	// targetEntity ���� <one-to-many class="">
	// mappedBy ���� inverse=true
	@OneToMany(targetEntity = Board_Person.class, cascade=CascadeType.ALL,mappedBy = "person")//person��ӦBoard_Person��person����
	private Set<Board_Person> board_person = new HashSet<Board_Person>();			//����İ����м��

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
