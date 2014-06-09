package org.gradle.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	private static EntityManagerFactory entityManagerFactory;
	
	static{
		
		try{
			entityManagerFactory = Persistence.createEntityManagerFactory("jpQuickStart");
			
		}catch(Throwable t){
			new ExceptionInInitializerError(t);
		}
	}
	
	public static EntityManagerFactory getEntityManagerFactory(){
		return entityManagerFactory;
	}
	
	public static void shutdown(){
		getEntityManagerFactory().close();
	}
}
