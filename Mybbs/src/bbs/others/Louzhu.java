package bbs.others;

import java.util.Date;

public class Louzhu {
	private String title; 
	private String louzhucontent; 
	private String account; 
	private Date date;
	private Integer postid;
	public Louzhu() {
		super();
	}
	public Louzhu(String title, String louzhucontent, String account, Date date, Integer postid) {
		super();
		this.title = title;
		this.louzhucontent = louzhucontent;
		this.account = account;
		this.date = date;
		this.postid = postid;
	}


	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLouzhucontent() {
		return louzhucontent;
	}
	public void setLouzhucontent(String louzhucontent) {
		this.louzhucontent = louzhucontent;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getPostid() {
		return postid;
	}
	public void setPostid(Integer postid) {
		this.postid = postid;
	}
}
