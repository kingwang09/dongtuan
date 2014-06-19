package org.gradle;

import org.hejin.newapp.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:META-INF/spring/applicationContext.xml")
@ActiveProfiles("development")
@Transactional
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class})
public class SpringInitTest {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Test
	public void test1(){
		System.out.println("success!!");
	}
	
	@Test
	public void UserTableCreateTest(){
		User u = new User();
		u.setName("진형은");
		u.setAddress("경기도 성남시 중원구 상대원");
		u.setUserId("kingwang09");
		
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		s.save(u);
		t.commit();
		System.out.println("Success and End!!");
	}
}
