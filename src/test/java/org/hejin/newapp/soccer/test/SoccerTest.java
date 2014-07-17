package org.hejin.newapp.soccer.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hejin.newapp.model.soccer.Applicant;
import org.hejin.newapp.model.soccer.Recruit;
import org.hejin.newapp.model.soccer.User;
import org.hibernate.Criteria;
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
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
@ActiveProfiles("development")
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
public class SoccerTest {
private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	SessionFactory sessionFactory;
	
	private void makeUser(Session session){
		User u1 = new User("cisco1","진형은");
		session.saveOrUpdate(u1);
		
		User u2 = new User("gildongHong","홍길동");
		session.saveOrUpdate(u2);
		
		User u3 = new User("jmkim","김지민");
		session.saveOrUpdate(u3);
	}
	
	private void makeRoom(Session session){
		
		//make User
		User u1 = new User("cisco1","진형은");
		User u2 = new User("gildongHong","홍길동");
		User u3 = new User("jmkim","김지민");
		session.save(u1);
		session.save(u2);
		session.save(u3);
		
		//make Room
		Recruit room = new Recruit();
		room.setTitle("첫번째 방입니다.");
		room.setContent("첫번째 방인데 제대로 만들어질찌 모르겠네요 ㅎㅎ");
		room.setGameDate(new Date());
		room.setLocation("엔키아 회사");
		room.setMaster(u1);
		
		Applicant apply1 = new Applicant(u2,room);
		Applicant apply2 = new Applicant(u3,room);
		session.save(apply1);
		session.save(apply2);
		
		List<Applicant> applies = new ArrayList<Applicant>();
		applies.add(apply1);
		applies.add(apply2);
		
		room.setApplicants(applies);
		session.save(room);
	}
	
	//@Test
	public void sccerTest(){
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		makeRoom(s);
		t.commit();
	}
	
	@Test
	public void soccerSelectTest(){
		Session s = sessionFactory.openSession();
		//Session s = sessionFactory.getCurrentSession();
		Criteria crit = s.createCriteria(Recruit.class);
		List<Recruit> recruits = crit.list();
		for(Recruit r : recruits){
			log.debug("recruit : "+r.getTitle()+" : "+r.getContent());
			log.debug("applicants : "+r.getApplicants().size());
			
		}
	}
}
