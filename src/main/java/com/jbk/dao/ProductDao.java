package com.jbk.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import com.jbk.config.Hibernateconfiguration;
import com.jbk.entity.Product;
import com.jbk.entity.Product2;

public class ProductDao {
	SessionFactory sessionFactory = Hibernateconfiguration.getSesstionFactory();

	public String addProduct(Product product) {

		String msg = null;
		Session session = null;
		try {
			session = sessionFactory.openSession();

			session.save(product);
			session.beginTransaction().commit();
			msg = "Added";

		} catch (Exception e) {
			msg = "Product already Exists with id= " + product.getProductId();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return msg;

	}

	public Product getProductById(long productId) {
		Session session = null;
		Product product = null;
		try {
			session = sessionFactory.openSession();

			product = session.get(Product.class, productId); // alt+shift+L

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return product;

	}

	public String deleteProductById(long productId) {
		Session session = null;
		String msg = null;
		try {
			session = sessionFactory.openSession();
			Product product = getProductById(productId);
			if (product != null) {
				session.delete(product);
				session.beginTransaction().commit();
				msg = "Product Deleted";
			} else {
				msg = "Product Not found With Id = " + productId;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;

	}

	public String updateProduct(Product product) {
		Session session = null;
		String msg = null;
		try {
			session = sessionFactory.openSession();
			session.update(product);
			session.beginTransaction().commit();
			msg = "Product Update";
		} catch (OptimisticLockException e) {
			msg = "Product Not Found To Update With Id = " + product.getProductId();
		} catch (Exception e) {
			e.printStackTrace();
			msg = "Somethig wrong while updating";

		} finally {
			if (session != null) {
				session.close();
			}
		}

		return msg;

	}

	public List<Product> getAllProducts() {
		Session session = null;
		List<Product> list = null;
		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Product.class);
			// criteria.setMaxResults(3);
			criteria.setFirstResult(2);
			criteria.setMaxResults(3);
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public List<Product> sortProduct(String orderType, String propertyName) {
		Session session = null;
		List<Product> list = null;
		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Product.class);

			if (orderType.equals("asc")) {
				criteria.addOrder(Order.asc(propertyName));
			} else {
				criteria.addOrder(Order.desc(propertyName));
			}
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public List<Product> restrictionEx() {
		Session session = null;
		List<Product> list = null;
		double val = 1000;
		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("productPrice"));
			criteria.add(Restrictions.gt("productPrice", val));
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public List<Product> restrictionEx2() {
		Session session = null;
		List<Product> list = null;
		double val = 2000;
		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("productPrice"));
			criteria.add(Restrictions.eq("productPrice", val));
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public List<Product> productnamestartwithf() {
		Session session = null;
		List<Product> list = null;

		try {
			session = sessionFactory.openSession();

			Criteria criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("productPrice"));
			criteria.add(Restrictions.like("productName", "f%"));
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public List<Product> productpricebetween20000() {
		Session session = null;
		List<Product> list = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);

			criteria.add(Restrictions.between("productPrice", 20000d, 60000d));
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Product> productquntitylessthan3() {
		Session session = null;
		List<Product> list = null;
		double price = 20;
		String name = "remote";
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			// criteria.add(Restrictions.lt("productQTY", 3));
			// criteria.add(Restrictions.ilike("productName", "re%"));
			// criteria.add(Restrictions.isNotNull("productPrice"));
			// criteria.add(Restrictions.isNull("productId"));
			Criterion proprice = Restrictions.eq("productPrice", price);
			Criterion proname = Restrictions.eq("productName", name);
			criteria.add(Restrictions.and(proprice, proname));

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Product> Projectionex() {
		Session session = null;
		List<Product> list = null;
		double maxprice;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.max("productPrice"));
			maxprice = (Double) criteria.list().get(0);
			// System.out.println(maxprice);
			Criteria criteria1 = session.createCriteria(Product.class);
			criteria1.add(Restrictions.eq("productPrice", maxprice));
			list = criteria1.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Product> Projectionex1() {
		Session session = null;
		List<Product> list = null;
		double minprice;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.min("productPrice"));
			minprice = (Double) criteria.list().get(0);
			// System.out.println(maxprice);
			Criteria criteria1 = session.createCriteria(Product.class);
			criteria1.add(Restrictions.eq("productPrice", minprice));
			list = criteria1.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Product> Projectionavg() {
		Session session = null;
		List<Product> list = null;
		double proprice;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.avg("productPrice"));
			proprice = (Double) criteria.list().get(0);
			// System.out.println(maxprice);
			//// Criteria criteria1 = session.createCriteria(Product.class);
			// criteria1.add(Restrictions.eq("productPrice", minprice));
			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Product> projectionsum() {
		Session session = null;
		List<Product> list = null;
		double sumprice;
		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.sum("productPrice"));
			sumprice = (Double) criteria.list().get(0);
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Product> projectionrowcount() {
		Session session = null;
		List<Product> list = null;

		try {
			session = sessionFactory.openSession();
			Criteria criteria = session.createCriteria(Product.class);
			criteria.setProjection(Projections.rowCount());
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Product> HQLqueryex() {
		Session session = null;
		List<Product> list = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("From Product");
			list = query.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;

	}

	public List<Product2> qureryex() {
		Session session = null;
		List<Object[]> list = null;
		List<Product2> products = new ArrayList();
		Product2 pr = null;
		try {
			session = sessionFactory.openSession();
			Query query = session.createQuery("Select p.productId,p.productName,p.productPrice from Product p");
			list = query.list();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				pr = new Product2();
				Object[] obj = (Object[]) it.next();
				pr.setProductId(obj[0]);
				pr.setProductName(obj[1]);
				pr.setProductPrice(obj[2]);
				products.add(pr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return products;
	}

}
