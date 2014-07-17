package org.hejin.newapp.model.soccer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CMM_APPLY")
public class Applicant {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private long id;
	
	@Getter @Setter
	private boolean isAttend;
	
	@ManyToOne
	@Getter @Setter
	private Recruit recruit;
	
	@OneToOne
	@Getter @Setter
	private User user;
	
	public Applicant() {
		// TODO Auto-generated constructor stub
	}
	
	public Applicant(User user, Recruit recruit) {
		// TODO Auto-generated constructor stub
		this.user = user; 
		this.recruit = recruit;
		this.isAttend = true;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return "id="+id;
	}
}
