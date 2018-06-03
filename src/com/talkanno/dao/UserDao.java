package com.talkanno.dao;

import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.talkanno.pojos.Message;
import com.talkanno.pojos.UserPojo;
import com.talkanno.sessionutils.SessionUtils;

public class UserDao {

	@SuppressWarnings("unchecked")
	public UserPojo getUserByName(String name) {
		Session sc = null;
		List<UserPojo> user = null;
		UserPojo userobj = null;
		try {
			sc = SessionUtils.getSession();
			Query q = sc.createQuery("from UserPojo where userName = :userName ");
			q.setParameter("userName", name);

			user = (List<UserPojo>) q.list();
			if (user != null && !user.isEmpty()) {
				userobj = user.get(0);
			}

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			SessionUtils.closeSession(sc);
		}
		System.out.println(user);
		return userobj;
	}

	public UserPojo getUserBySerial(int serial) {
		Session sc = null;
		UserPojo user = null;
		try {
			sc = SessionUtils.getSession();
			user = (UserPojo) sc.get(UserPojo.class, serial);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			SessionUtils.closeSession(sc);
		}
		System.out.println(user);
		return user;
	}

	public boolean isUserExist(String name) {
		UserPojo user = getUserByName(name);
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	public int getUserSerial(String name) {
		UserPojo user = getUserByName(name);
		if (user != null) {
			return user.getSerial();
		} else {
			return 0;
		}
	}

	public int createUser(String name, String pass, String email) {
		Session session = null;
		int serial = 0;
		try {
			session = SessionUtils.getSession();
			session.getTransaction().begin();

			// transisent state
			UserPojo userpojo = new UserPojo();
			userpojo.setEmail(email);
			userpojo.setPassword(pass);
			userpojo.setUserName(name);

			// Here the object is in persistant state
			System.out.println("Before calling save");
			serial = (int) session.save(userpojo);

			System.out.println("After save function --------- You could see no Query called");
			System.out.println(
					"Now object is in persitant state -- hibenate know that this object exist and will update in database");
			System.out.println("-----------------------");
			System.out.println("Calling commit transaction ---------- Now query will be called");
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("Calling close session ------ object is now in detached state");
			SessionUtils.closeSession(session);
		}
		return serial;
	}

	/*
	 * public void update(Account acc) { Session session = null; try { session =
	 * SessionUtils.getSession(); session.getTransaction().begin();
	 * 
	 * System.out.println("Before calling update"); session.update(acc); System.out.
	 * println("After update function --------- You could see no Query called");
	 * System.out.
	 * println("Calling commit transaction ---------- Now query will be called");
	 * 
	 * session.getTransaction().commit(); } catch (HibernateException e) {
	 * session.getTransaction().rollback(); e.printStackTrace(); } finally {
	 * SessionUtils.closeSession(session); } }
	 * 
	 * public void delete(Account acc) { Session session = null; try { session =
	 * SessionUtils.getSession(); session.getTransaction().begin();
	 * 
	 * System.out.println("Before calling delete"); session.delete(acc);
	 * System.out.println(
	 * "After delete function --------- You could see only select query is called to get current status of object"
	 * ); System.out.
	 * println("Calling commit transaction ---------- Now delect query query will be called"
	 * );
	 * 
	 * session.getTransaction().commit(); } catch (HibernateException e) {
	 * session.getTransaction().rollback(); e.printStackTrace(); } finally {
	 * SessionUtils.closeSession(session); } }
	 */

	@SuppressWarnings("unchecked")
	public List<UserPojo> getUsers(String name) {
		Session sc = null;
		List<UserPojo> users = null;
		try {
			sc = SessionUtils.getSession();
			Query q = sc.createQuery("from UserPojo where  userName LIKE '%" +name +"%' ");

			users = q.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			SessionUtils.closeSession(sc);
		}
		System.out.println(users);
		return users;
	}

	@SuppressWarnings("unchecked")
	public boolean isCorrectPassword(String pass, String name) {
		Session sc = null;
		UserPojo user = new UserPojo();
		try {
			user.setUserName(name);
			user.setPassword(pass);
			sc = SessionUtils.getSession();
			List<UserPojo> li = sc
					.createQuery("from UserPojo u where u.userName = :userName and u.password = :password")
					.setProperties(user).list();
			if (li == null || li.isEmpty()) {
				return false;
			} else {
				return true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			SessionUtils.closeSession(sc);
		}
		System.out.println(user);
		return false;
	}

	public int createMessageEntry(int userserial, String msg) {
		Session session = null;
		int serial = 0;
		try {
			session = SessionUtils.getSession();
			session.getTransaction().begin();

			Message msgObj = new Message();
			// transisent state
			msgObj.setUserSerial(userserial);
			msgObj.setMessage(msg);

			// Here the object is in persistant state
			System.out.println("Before calling save");
			serial = (int) session.save(msgObj);

			System.out.println("After save function --------- You could see no Query called");
			System.out.println(
					"Now object is in persitant state -- hibenate know that this object exist and will update in database");
			System.out.println("--------s---------------");
			System.out.println("Calling commit transaction ---------- Now query will be called");
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			System.out.println("Calling close session ------ object is now in detached state");
			SessionUtils.closeSession(session);
		}
		return serial;
	}

	@SuppressWarnings("unchecked")
	public List<Message> getMessages(String username) {
		Session sc = null;
		int serial = getUserSerial(username);

		List<Message> msg = new ArrayList<>();
		try {
			sc = SessionUtils.getSession();
			Query q = sc.createQuery("select a from Message a where a.userSerial = :userSerial");
			q.setParameter("userSerial", serial);
			msg = q.list();

		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			SessionUtils.closeSession(sc);
		}
		System.out.println(msg);
		return msg;
	}

}
