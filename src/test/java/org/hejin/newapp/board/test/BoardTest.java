package org.hejin.newapp.board.test;

import java.util.Collection;
import java.util.Date;

import org.hejin.newapp.model.Board;
import org.hejin.newapp.service.BoardService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
@ActiveProfiles("development")
@Transactional
public class BoardTest {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	BoardService boardService;
	
	private Logger log  = LoggerFactory.getLogger(getClass());
	
	private Board getMockBoard(){
		Board b = new Board();
			b.setTitle("테스트 데이터 타이틀");
			b.setContent("테스트 데이터 입니다..\n It's a love Thang...");
			b.setWDate(new Date());
		
		return b;
	}
	
	//@Test
	public void insertTest(){
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		Board b = getMockBoard();
		s.save(b);
		t.commit();
		
		log.debug("after Save : "+b);
		
		Board b2 = (Board) s.get(Board.class, b.getId());
		log.debug("after select : "+b2);
		s.close();
	}
	
	@Test
	public void getListTest(){
		Collection<Board> boards = boardService.getBoards();
		for(Board board : boards){
			log.debug("board : "+board);
		}
	}
}
