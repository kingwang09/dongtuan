package org.hejin.newapp.web.components.board;

import java.util.Collection;

import org.apache.tapestry5.Block;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hejin.newapp.model.Board;
import org.hejin.newapp.service.BoardService;

public class SimpleBoard {
	
	@Inject
	private BoardService boardService;
	
	@Inject
	private Block list, detail, write;
	
	@Persist
	int mode;
	
	public Block getActiveBlock(){
		if(mode==1){
			return detail;
		}else if(mode==2){
			return write;
		}
		return list;
	}
	
	public Collection<Board> getBoards(){
		return boardService.getBoards();
	}
	
	@Property
	Board board;
	
}
