/*
 * @(#)Tab.java	2013. 7. 19.
 *
 * Copyright 2013 Nkia.com, Inc. All rights reserved.
 * NKIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.hejin.newapp.components;

import org.apache.log4j.Logger;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.BeforeRenderBody;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.dom.Element;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.hejin.newapp.model.TabGroupModel;
import org.hejin.newapp.model.TabTracker;


/**
 * Tab
 * 
 * @version <tt>Revision: 1.0</tt> 2013. 7. 19.
 * @author <a href="mailto:sungjae@nkia.co.kr">Park Sung Jae</a>
 */
public class Tab {
	
	private final Logger log = Logger.getLogger(getClass());
	@Parameter(required = true, defaultPrefix = BindingConstants.LITERAL)
	@Property
	private String name;

	@Parameter(defaultPrefix = BindingConstants.LITERAL, value = "prop:name")
	private String label;
	
	@Parameter(defaultPrefix = BindingConstants.LITERAL)
	private boolean disabled = false;

	@Inject
	private Request request;

	@Inject
	private ComponentResources resources;
	
	@Environmental
    private TabTracker tabTracker;
	
	//@Parameter(value="false")
	private boolean preRender;
	
	
	@BeforeRenderBody
	boolean beforeRenderBody(MarkupWriter writer) {
		log.debug("[Tab] beforeRenderBody.");
		preRender = (Boolean)request.getAttribute(TabGroup.ATTRIBUTE_RENDER);
		log.debug("[Tab] preRender = "+preRender);
		if(preRender){
			writer.element("div");
			return true;
		}else{
			TabGroupModel tabModel = (TabGroupModel) request.getAttribute(TabGroup.ATTRIBUTE_MODEL);
			if (tabModel == null) {
				throw new IllegalStateException("Tab must be nested inside a TabGroup");
			}
			tabModel.addTab(name, label, resources.getBody(), disabled);
			// don't render the body, it will be rendered by the TabGroup
			return false;
		}
	}
	
	void afterRenderBody(MarkupWriter writer) {
        // End the container and record the label the body's markup in the TabTracker.
		if(preRender){
			Element bodyContainer = writer.getElement();
	        writer.end();
	        tabTracker.addTab(label, bodyContainer.getChildMarkup());

	        // Remove the container (and therefore the body's markup) from the DOM. TabGroup will render the markup later at
	        // its leisure.
	        bodyContainer.remove();
		}
    }
}
