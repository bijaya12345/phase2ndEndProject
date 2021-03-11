
package com.hcl.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hcl.model.User;
import com.hcl.util.HibernateUtil;

public class UserDao {

	public void saveUser(User user) {
		Transaction t = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			t = session.beginTransaction();
			session.save(user);
			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		}
	}

	public boolean validate(String userName, String password) {

		Transaction t = null;
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			t = session.beginTransaction();

			user = (User) session.createQuery("FROM User U WHERE U.username = :userName")
					.setParameter("userName", userName).uniqueResult();

			if (user != null && user.getPassword().equals(password)) {
				return true;
			}

			t.commit();
		} catch (Exception e) {
			if (t != null) {
				t.rollback();
			}
			e.printStackTrace();
		}
		return false;
	}
}
