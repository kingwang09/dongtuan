package org.hejin.newapp.model.cygnus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="CMM_RESOURCE")
public class Resource {
	
	@Id
	@GeneratedValue
	@Getter @Setter
	private Integer id;
	
	/**
	 * ?
	 */
	@Version 
	private Integer optLock;
	
	@Column(nullable=false)
	@Getter @Setter
	private String name;
	
	@Column(nullable=false, name="RESOURCE_KEY")
	@Getter @Setter
	private String resourceKey;
	
	@Column(length=4000)
	@Getter @Setter
	private String description;
	
	@Getter @Setter
	private String modifiedBy;
	
	@Getter @Setter
	private Long cTime = System.currentTimeMillis();
	
	@Getter @Setter
	private Long mTime = System.currentTimeMillis();
	
	@Getter @Setter
	private Long dTime;
	
	
}
