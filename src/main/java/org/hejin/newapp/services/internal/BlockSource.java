package org.hejin.newapp.services.internal;

import java.util.Map;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.ioc.annotations.UsesConfiguration;
import org.hejin.newapp.model.PluggableBlock;


@UsesConfiguration(PluggableBlock.class)
public interface BlockSource {
	public Map<String,PluggableBlock> getPluggableBlocksMap();
	public Block getBlock(String id);
}
