package org.hejin.newapp.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;


public class Seller extends User {

	@Getter @Setter
	private String sellerName;
	
	@Transient
	@Getter @Setter
	private String trasientTest;
}
