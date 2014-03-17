package org.hejin.newapp.components;


import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class Modal {
	@Property
	@Parameter(required=true,defaultPrefix = BindingConstants.LITERAL)
	String modalId;
	
	@Property
	@Parameter
	Block header;
	
	@Property
	@Parameter
	Block footer;
	
	@Property
	@Parameter(required=true,defaultPrefix = BindingConstants.LITERAL,value="Close")
	String close;
	
	@Inject
	JavaScriptSupport javaScriptSupport;
	void afterRender(){
		javaScriptSupport.require("bootstrap/modal");
   }
}
