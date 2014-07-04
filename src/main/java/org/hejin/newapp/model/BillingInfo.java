package org.hejin.newapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class BillingInfo {
	@Id
	@GeneratedValue
	@Getter @Setter
	private Integer id;
	
	@Getter @Setter
	private String bills;
	
	public BillingInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public BillingInfo(String bills) {
		// TODO Auto-generated constructor stub
		this.bills = bills;
	}
}
