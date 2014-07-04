package org.hejin.newapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CMM_ITEM")
public class Item {

	@Id
	@Column(name="ITEM_ID")
	@GeneratedValue
	private Integer id;
	
	@Getter @Setter
	private String title;
	
	@Getter @Setter
	private String content;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@Getter @Setter
	//@JoinColumn(name="ITEM_USER_ID")//,referencedColumnName="TB_USER_ID")
	private User ownUser;
	
	protected Item() {
		// TODO Auto-generated constructor stub
	}
	
	public Item(String title, String content,User ou) {
		this.title = title;
		this.content = content;
		this.ownUser = ou;
	}
}
