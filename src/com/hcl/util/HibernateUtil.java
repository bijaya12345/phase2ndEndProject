package com.hcl.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.hcl.model.User;

//import javax.security.auth.login.Configuration;

public class HibernateUtil {

	public static void main(String[] args) {
		Transaction t = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			t = session.beginTransaction(); // begin the session
			User u = new User("Bijaya Khanal", "bijaya.khanal@hcl.com", "bkh", "123");
			session.save(u);// save the session
			t.commit();// commit the session
			session.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		configuration.addAnnotatedClass(com.hcl.model.User.class);
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory factory = configuration.buildSessionFactory(builder.build());
		return factory;

	}

}
