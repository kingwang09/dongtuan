package org.hejin.newapp.web.pages;

import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModalDemo {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Property
	String firstName = "Hello World!!";
	
	@InjectComponent
	Zone modalZone;
	
	@Inject
	Request request;
	
	Object onSuccess(){
		log.debug("[ModalDemo] onSuccess. firstName = "+firstName);
		log.debug("[ModalDemo] request.isXHR() = "+request.isXHR());
		return request.isXHR()? modalZone.getBody() : null;
	}
}
