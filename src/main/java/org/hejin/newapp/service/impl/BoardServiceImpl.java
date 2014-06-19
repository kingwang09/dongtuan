package org.hejin.newapp.service.impl;

import java.util.Collection;
import java.util.List;

import org.hejin.newapp.dao.BoardDao;
import org.hejin.newapp.model.Board;
import org.hejin.newapp.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BoardService")
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	public Collection<Board> getBoards() {
		return boardDao.getBoards();
	}

}
