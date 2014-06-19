package org.hejin.newapp.web.services.internal;

import java.util.Collection;
import java.util.Map;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ioc.util.CaseInsensitiveMap;
import org.apache.tapestry5.services.ComponentSource;
import org.hejin.newapp.web.model.PluggableBlock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BlockSourceImpl implements BlockSource {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	public BlockSourceImpl() {
		log.debug("[BlockSource] Default Initialize.");
	}
	
	private Map<String, PluggableBlock> pluggableBlocksMap;
	private ComponentSource componentSource;

	public BlockSourceImpl(final ComponentSource componentSource,
			Collection<PluggableBlock> contribution) {
		log.debug("[BlockSource] BlockSourceImpl Initialize.");
		pluggableBlocksMap = new CaseInsensitiveMap<PluggableBlock>(contribution.size());
		for(PluggableBlock pluggableBlock : contribution) {
			pluggableBlocksMap.put(pluggableBlock.getId(), pluggableBlock);
		}
		log.debug("[BlockSource] pluggableBlocksMap = "+pluggableBlocksMap);
		this.componentSource = componentSource;
	}
	
	public Map<String,PluggableBlock> getPluggableBlocksMap(){
		return pluggableBlocksMap;
	}

	public Block getBlock(String id) {
		PluggableBlock block = pluggableBlocksMap.get(id);
		return componentSource.getPage(block.getPageName()).getComponentResources().getBlock(block.getBlockName());
	}
	
}
