package org.hejin.newapp.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

public class Panel {
	
	@Parameter
	@Property
	Block header;
	
	@Parameter
	@Property
	Block content;
	
	@Parameter
	@Property
	Block footer;
	
	@Parameter(value="default",defaultPrefix=BindingConstants.LITERAL)
	@Property
	String option;
}
