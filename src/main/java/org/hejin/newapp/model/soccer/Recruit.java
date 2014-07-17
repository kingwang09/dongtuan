package org.hejin.newapp.model.soccer;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CMM_RECUIT")
public class Recruit {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Getter
	private long id;
	
	@Column(length=1000)
	@Getter @Setter
	private String title;
	
	@Lob
	@Getter @Setter
	private String content;
	
	@Getter @Setter
	private int requireCount;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Getter @Setter
	private Date gameDate;
	
	@Getter @Setter
	private String location;
	
	@OneToMany(mappedBy="recruit")
	@Getter @Setter
	private List<Applicant> applicants;
	
	@OneToOne
	@Getter @Setter
	private User master; 
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		//return super.toString();
		return "id="+id+", title="+title;
	}
}
