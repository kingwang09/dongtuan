package org.gradle.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.gradle.util.HibernateUtil;
import org.hejin.newapp.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;


public class UserTest {

	
	//@Test
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
	@Test
	public void dateTest(){
		Calendar current = Calendar.getInstance();
		Calendar target = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		int currentMin = current.get(Calendar.MINUTE);
		current.set(Calendar.MINUTE, currentMin-5);
		
		boolean result = target.after(current);
		System.out.println(df.format(current.getTime())+" - "+df.format(target.getTime())+" : "+result);
	}
}
