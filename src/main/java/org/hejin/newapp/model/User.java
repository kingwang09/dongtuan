package org.hejin.newapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_USER")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column
	private String userId;
	
	@Column
	private String name;
	
	@Column
	private String address;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String userId, String name, String address) {
		// TODO Auto-generated constructor stub
		this.userId = userId;
		this.name = name;
		this.address = address;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getId() {
		return id;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User[id = "+id+", ")
			.append("name = "+name+", ")
			.append("address = "+address+"]");
		return sb.toString();
	}
}
