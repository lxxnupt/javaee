package bbs.entity;
import  java.util.*;
import javax.persistence.*;

@Entity
@Table
public class Board_Person extends BaseBean{
	
	@ManyToOne(targetEntity = Board.class)
	@JoinColumn(name = "board_id")
	private Board board;
	
	@ManyToOne(targetEntity = Person.class)
	@JoinColumn(name = "person_id")
	private Person person;
	
	private Date dateManaged;
	private String ContinuedTime;
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Date getDateManaged() {
		return dateManaged;
	}
	public void setDateManaged(Date dateManaged) {
		this.dateManaged = dateManaged;
	}
	public String getContinuedTime() {
		return ContinuedTime;
	}
	public void setContinuedTime(String continuedTime) {
		ContinuedTime = continuedTime;
	}
}
