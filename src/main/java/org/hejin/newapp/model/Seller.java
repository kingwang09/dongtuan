package org.hejin.newapp.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Seller extends User {

	@Getter @Setter
	private String sellerName;
	
	@Transient
	@Getter @Setter
	private String trasientTest;
}
