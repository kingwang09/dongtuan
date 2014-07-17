package org.hejin.newapp.model.soccer;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name="SOCCER_USER")
@Table(name="CMM_USER")
public class User {

	//@GeneratedValue(strategy=GenerationType.AUTO)
	@Id
	@Getter @Setter
	private String userid;
	
	@Getter @Setter
	private String name;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userid) {
		// TODO Auto-generated constructor stub
		this.userid = userid;
	}
	
	public User(String userid, String name) {
		// TODO Auto-generated constructor stub
		this.userid = userid;
		this.name = name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return "userid="+userid+", name="+name;
	}
}
