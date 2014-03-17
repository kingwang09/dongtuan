package org.hejin.newapp.model;


public interface PluggableBlock {
	public String getPageName();

	public void setPageName(String pageName);

	public String getBlockName();

	public void setBlockName(String blockName);
	
	public String getId();
}
