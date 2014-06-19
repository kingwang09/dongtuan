package org.hejin.newapp.web.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class ModalLink {
	@Property
	@Parameter(required=true,defaultPrefix = BindingConstants.LITERAL)
	String dialogId;
	
	@Property
	@Parameter(defaultPrefix = BindingConstants.LITERAL,value="btn btn-default")
	String className;
}
