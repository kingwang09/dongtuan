package org.hejin.newapp.web.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PluggableBlockImpl implements PluggableBlock {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private String pageName;
	private String blockName;
	private String id;
	
	public PluggableBlockImpl() {
		log.debug("[PluggableBlockImpl] Initialize.");
	}
	
	public PluggableBlockImpl(String id,String pageName, String blockName) {
		log.debug("[PluggableBlockImpl] PluggableBlockImpl(String pageName, String blockName).");
		this.id = id;
		this.pageName = pageName;
		this.blockName = blockName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
