package org.hejin.newapp.pages;

import java.util.Set;

import javax.inject.Inject;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.alerts.AlertManager;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.services.Request;
import org.hejin.newapp.model.PluggableBlock;
import org.hejin.newapp.services.internal.BlockSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Start page of application newapp.
 */
public class BlockCompDemo
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	@Inject
	AlertManager alertManager;
	
	@Inject
	BlockSource blockSource;
	
	@Property
	PluggableBlock block;
	
	@Property
	String blockId;
	
	@InjectComponent
	Zone blockZone;
	
	private String activeBlockId;
	
	
	@OnEvent("alertMessage")
	void callAlertManager(String message){
		alertManager.info(message);
	}
	
	public Set<String> getBlocks(){
		return blockSource.getPluggableBlocksMap().keySet();
	}
	
	@Inject
	Request request;
	@OnEvent("loadBlock")
	Object loadBlock(String activeBlockId){
		log.debug("[BlockCompDemo - loadBlock] activeBlockId = "+activeBlockId);
		this.activeBlockId = activeBlockId;
		return request.isXHR()?blockZone.getBody():null;
	}
	
	public Block getActiveBlock(){
		log.debug("[BlockCompDemo - getActiveBlock] activeBlockId = "+activeBlockId);
		if(activeBlockId!=null){
			return blockSource.getBlock(activeBlockId);
		}
		return null;
	}
	
	public Block getActiveBlock2(){
		log.debug("[BlockCompDemo - getActiveBlock2] blockId = "+blockId);
		return blockSource.getBlock(blockId);
	}
}
