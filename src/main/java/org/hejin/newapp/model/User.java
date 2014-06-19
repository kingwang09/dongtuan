package org.hejin.newapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TB_USER")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private Integer id;
	
	@Getter @Setter
	private String userId;
	@Getter @Setter
	private String name;
	@Getter @Setter
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("User[id = "+id+", ")
			.append("name = "+name+", ")
			.append("address = "+address+"]");
		return sb.toString();
	}
}
