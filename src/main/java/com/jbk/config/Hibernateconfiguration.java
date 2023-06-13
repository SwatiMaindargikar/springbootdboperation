package com.jbk.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.jbk.entity.Product;

public class Hibernateconfiguration {
	public static  SessionFactory getSesstionFactory()
	{
		Configuration cfg=new Configuration();
		cfg.configure().addAnnotatedClass(Product.class);
		SessionFactory sessionfactory=cfg.buildSessionFactory();
		return sessionfactory;
	}

}
