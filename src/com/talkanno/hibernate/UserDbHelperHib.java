package com.talkanno.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.talkanno.pojos.UserPojo;

public class UserDbHelperHib {
	public static void createUser(Session ses, UserPojo user) {
		ses.save(user);
		ses.beginTransaction().commit();
		
	}

	public static void main(String[] args) {
		Configuration con = new  Configuration();
		con.configure("com/talkanno/res/hibernate.cfg.xml");
		SessionFactory sef = con.buildSessionFactory();
		Session ses = sef.openSession();
		
		UserPojo user = new UserPojo();
		user.setEmail("email");
		user.setPassword("pass");
		user.setUserName("sdasd");
		user.setSerial(11);
		
		
		
		createUser(ses, user);
		
		ses.evict(user);

		ses.close();
		
	}
	
}
