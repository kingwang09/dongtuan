package org.gradle.test;

import org.gradle.User;
import org.gradle.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;


public class UserTest {

	
	@Test
	public void testUserTest(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.getTransaction();
		t.begin();
		
		User u = new User();
			u.setUserId("hejin");
			u.setName("Hyung eun");
			u.setAddress("Address");
		session.save(u);
		t.commit();
		session.close();
		
		System.out.println("준영속 : "+u);
		
		Session session2 = HibernateUtil.getSessionFactory().openSession();
		User u2 = (User) session2.get(User.class, new Integer(1));
		System.out.println(u2);
		System.out.println(u == u2);
		session2.close();
		
		System.out.println("-------------------------");
		System.out.println("close Session..");
		System.out.println("-------------------------");
		System.out.println(u2);
		
	}
}
