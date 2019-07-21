package com.hpe.enterprise.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.hpe.enterprise.bl.impl.EnterpriseServiceImpl;

public class Testd {

	public static void main(String[] args) {
		SessionFactory sessionFactory = null;
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure() // configures
																									// settings
																									// from
																									// hibernate.cfg.xml
				.build();
		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception ex) {
			StandardServiceRegistryBuilder.destroy(registry);
		}

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		BuisnessUser buisnessUser = new BuisnessUser();

		buisnessUser.setUserDetails("cscas");
		buisnessUser.setUserName("Mojit");

		session.save(buisnessUser);
		session.getTransaction().commit();
		session.close();
		
		EnterpriseServiceImpl enterpriseServiceImpl = new EnterpriseServiceImpl();
		Comment comment = new Comment();
		comment.setOrderId(1);

	}
}
