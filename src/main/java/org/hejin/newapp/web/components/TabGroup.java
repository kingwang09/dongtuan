/*
 * @(#)TabGroup.java	2013. 7. 19.
 *
 * Copyright 2013 Nkia.com, Inc. All rights reserved.
 * NKIA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.hejin.newapp.web.components;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.Block;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.CleanupRender;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.runtime.RenderCommand;
import org.apache.tapestry5.runtime.RenderQueue;
import org.apache.tapestry5.services.Environment;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.hejin.newapp.web.model.TabTracker;
import org.hejin.newapp.web.model.TabGroupModel;


/**
 * TabGroup
 *  
 * @version <tt>Revision: 1.0</tt> 2013. 7. 19.
 * @author <a href="mailto:sungjae@nkia.co.kr">Park Sung Jae</a>
 */
public class TabGroup {
	   public final Logger log = Logger.getLogger(getClass());
	   public static final String ATTRIBUTE_MODEL = TabGroup.class.getName() + ".model";
	   public static final String ATTRIBUTE_RENDER = TabGroup.class.getName() + ".render";
	   
	   static class TabModel {
	      String name;
	      String label;
	      Block body;
	      boolean disabled;
	      
	      TabModel(String name, String label, Block body, boolean disabled) {
	         super();
	         this.name = name;
	         this.label = label;
	         this.body = body;
	         this.disabled = disabled;
	      }
	   }
	   
	   @Property
	   private Map<String, TabModel> tabModels;
	   
	   @Inject
	   private Request request;
	   
	   @InjectComponent
	   private Zone tabsZone;

	   @Parameter(defaultPrefix=BindingConstants.LITERAL, value = "literal:tabs")
	   @Property
	   private String option;
	   
	   @Parameter
	   private String active;
	   
	   @Property
	   private String currentName;

	   
	   void setup() {
	      tabModels = new LinkedHashMap<String, TabModel>();
	      TabGroupModel tabGroupModel = new TabGroupModel() {
	         public void addTab(String name, String label, Block body, boolean disabled) {
	            tabModels.put(name, new TabModel(name, label, body, disabled));
	         }
	      };
	      request.setAttribute(ATTRIBUTE_MODEL, tabGroupModel);
	      request.setAttribute(ATTRIBUTE_RENDER, preRender);
	   }

	   TabModel getActiveTabModel() {
	      TabModel activeModel = null;
	      if (active != null) {
	         activeModel = tabModels.get(active);
	         if (activeModel != null) {
	        	 return activeModel;
	         }
	      }
	      if (!tabModels.isEmpty()) {
	         // assume first tab is active if active parameter not specified
	         activeModel = tabModels.values().iterator().next();
	      }
	      return activeModel;
	   }

	   @SetupRender
	   void setupRender() {
	      setup();
	   }
	   
	   @CleanupRender
	   void cleanupRender() {
	      request.setAttribute(ATTRIBUTE_MODEL, null);
	   }
	   
	   Object onTabChange(String tabName) {
	      active = tabName;
	      setup();
	      return request.isXHR() ? tabsZone.getBody() : null;
	   }
	   
	   public String getTabClass() {
	      TabModel activeModel = getActiveTabModel();
	      boolean isActive = activeModel != null && activeModel.name.equals(currentName);
	      
	      if(isActive) {
	    	  return "active";
	      }
	      if(activeModel.disabled) {
	    	  return "disabled";
	      }
	      return null;
	   }
	   
	   public Block getActiveTabBody() {
	      TabModel activeModel = getActiveTabModel();
	      return activeModel == null ? null : activeModel.body;
	   }
	   
	   public String getCurrentLabel() {
	      return tabModels.get(currentName).label;
	   }
	   
	   /**
	    * PreRender Option Add by hejin.
	    */
	   @Property
	   @Parameter(value="false")
	   private boolean preRender;
	   @Property
	   private List<String> tabIds;
	   @Property
	   private String tabId;
	   @Property
	   private int tabNum;
	   private List<String> tabLabels;
	   private List<String> tabMarkups;
	   @Inject
	   private JavaScriptSupport javaScriptSupport;
	   @Inject
	   private ComponentResources componentResources;
	   @Inject
	   private Environment environment;
	   
	   void beginRender(){
		   log.debug("[TabGroup] beginRender.");
		   if(preRender){
			   environment.push(TabTracker.class, new TabTracker());
		   }
	   }
	   void afterRenderBody(MarkupWriter markupWriter){
		   log.debug("[TabGroup] afterRender. preRender = "+preRender);
		   if(preRender){
			   TabTracker tabTracker = environment.pop(TabTracker.class);
			   tabLabels = tabTracker.getLabels();
			   tabMarkups = tabTracker.getMarkups();
			   
			   tabIds = new ArrayList(); 
			   for(int i=0; i<tabLabels.size();i++){
				   String id = javaScriptSupport.allocateClientId(componentResources);
				   tabIds.add(id);
			   }
		   }
	   }
	   
	   void afterRender(){
		   if(preRender){
			   javaScriptSupport.require("bootstrap/tab");
		   }
	   }
	   
	   public String getActive(){
		   return tabNum == 0 ? "active" : "";
	   }
	   
	   public String getTabLabel(){
		   return tabLabels.get(tabNum);
	   }
	   
	   public RenderCommand getTabMarkup(){
		   return new RenderCommand(){
			public void render(MarkupWriter writer, RenderQueue queue) {
				writer.writeRaw(tabMarkups.get(tabNum));
			}
		   };
	   }
}
