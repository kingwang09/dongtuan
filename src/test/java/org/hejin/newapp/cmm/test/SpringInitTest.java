package org.hejin.newapp.cmm.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hejin.newapp.model.BillingInfo;
import org.hejin.newapp.model.Item;
import org.hejin.newapp.model.Seller;
import org.hejin.newapp.model.User;
import org.hibernate.Criteria;
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
	
	/*@Test
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
	}*/
	
	/*@Test
	public void subUserTest(){
		Seller u = new Seller();
		u.setName("진형은");
		u.setUserId("형은-userid");
		u.setAddress("상대원임22");
		u.setSellerName("hejin 셀러리맨~");
		u.setTrasientTest("Trasient Test");
		BillingInfo b = new BillingInfo("Sample");
		u.setBillingInfo(b);
		
		
		
		Session s = sessionFactory.openSession();
		Transaction t = s.beginTransaction();
		
		Set<Item> list = new HashSet<Item>();
		list.add(new Item("item1-title","item1-content",u));
		list.add(new Item("item2-title","item2-content",u));
		u.setItem(list);
		
		s.save(u);
		t.commit();
		System.out.println("Success and End!!");
	}*/
	
	@Test
	public void selectUser(){
		Session s = sessionFactory.openSession();
		Criteria c = s.createCriteria(User.class);
		List<User> uList = c.list();
		for(User u : uList){
			System.out.println(u);
			System.out.println(u.getBillingInfo());
			System.out.println(u.getItem());
		}
	}
}
