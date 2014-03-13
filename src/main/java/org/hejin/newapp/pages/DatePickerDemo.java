package org.hejin.newapp.pages;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatePickerDemo {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Property
	String textfield;
	
	@SetupRender
	void init(){
		log.debug("DatePicker Demo!!");
	}
}
