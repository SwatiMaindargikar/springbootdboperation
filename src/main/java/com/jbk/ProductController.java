package com.jbk;

import java.util.List;
import java.util.Scanner;

import com.jbk.dao.ProductDao;
import com.jbk.entity.Product;
import com.jbk.entity.Product2;
import com.jbk.utility.ProductUtility;

public class ProductController {

	public static void main(String[] args) {
		ProductDao dao = new ProductDao();
		Scanner scanner = new Scanner(System.in);
		int choice;
		char c;
		do {
			System.out.println("Press 1 For Save Product");
			System.out.println("Press 2 For get Product By Id");
			System.out.println("Press 3 for delete product");
			System.out.println("Press 4 for update product");

			System.out.println("Press 5 for getAllProducts");
			System.out.println("Press 6 for sort product");

			System.out.println("Press 7 for Restriction Ex");
			System.out.println("press 8 for Restriction ex2");
			System.out.println("press 9 for Productname start with f");
			System.out.println("press 10 for product price between 20000 to 60000");
			System.out.println("press 11 for product quntity lessthan 3");
			System.out.println("press 12 for Projection max value example");
			System.out.println("press 13 for minmum price");
			System.out.println("press 14 for avg price");
			System.out.println("press 15 for sum price");
			System.out.println("press 16 for row count");
			System.out.println("press 17 for HQL query");
			System.out.println("press 18 for queryex with pId,pname,pPrice");

			choice = scanner.nextInt();

			switch (choice) {
			case 1: {
				Product product = ProductUtility.prepareProductData();

				if (product != null) {
					String msg = dao.addProduct(product);
					System.out.println(msg);
				} else {
					System.out.println("Product Not Valid");
				}

				break;
			}

			case 2: {
				System.out.println("Enter Product Id");
				long productId = scanner.nextLong();
				Product product = dao.getProductById(productId);
				if (product != null) {
					System.out.println(product);
				} else {
					System.out.println("Product Not Found With Id =" + productId);
				}
				break;
			}

			case 3: {

				System.out.println("Enter Product Id");
				long productId = scanner.nextLong();
				String msg = dao.deleteProductById(productId);
				System.out.println(msg);
				break;
			}

			case 4: {
				Product product = ProductUtility.prepareProductData();
				if (product != null) {
					String msg = dao.updateProduct(product);
					System.out.println(msg);
				} else {
					System.out.println("Product Not Valid");
				}

				break;

			}
			case 5: {

				List<Product> list = dao.getAllProducts();

				if (list.isEmpty() || list == null) {
					System.out.println("Product Not Founds");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}

				break;
			}

			case 6: {

				System.out.println("Enter order type asc/desc");
				String orderType = scanner.next();
				System.out.println("Enter property name");
				String propertyName = scanner.next();
				List<Product> list = dao.sortProduct(orderType, propertyName);

				if (list.isEmpty() || list == null) {
					System.out.println("Product Not Founds");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}

				break;
			}

			case 7: {
				List<Product> list = dao.restrictionEx();
				if (list.isEmpty() || list == null) {
					System.out.println("Product Not Founds");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}
			case 8: {
				List<Product> list = dao.restrictionEx2();
				if (list.isEmpty() || list == null) {
					System.out.println("Product Not Founds");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}
			case 9: {
				List<Product> list = dao.productnamestartwithf();
				if (list.isEmpty() || list == null) {
					System.out.println("Product Not Founds");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}
			case 10: {
				List<Product> list = dao.productpricebetween20000();
				if (list.isEmpty() || list == null) {
					System.out.println("Product Not Founds");
				} else {
					for (Product product : list) {
						System.out.println(product);
					}
				}
				break;
			}
			case 11: {
				List<Product> list = dao.productquntitylessthan3();
				if (list.isEmpty() || list == null) {
					System.out.println("product not found");
				} else {
					for (Product product : list) {
						System.out.println(product);

					}
				}
				break;
			}
			case 12: {
				List<Product> list = dao.Projectionex();

				for (Product product : list) {
					System.out.println(product);
				}
				break;
			}
			case 13: {
				List<Product> list = dao.Projectionex1();

				for (Product product : list) {
					System.out.println(product);
				}
				break;
			}
			case 14: {
				List<Product> list = dao.Projectionavg();
				System.out.println(list);
				break;
			}
			case 15: {
				List<Product> list = dao.projectionsum();
				System.out.println(list);
				break;
			}
			case 16: {
				List<Product> list = dao.projectionrowcount();
				System.out.println(list);
				break;
			}
			case 17: {
				List<Product> list = dao.HQLqueryex();
				for (Product product : list) {
					System.out.println(product);
				}
				break;
			}
			case 18:{
				List<Product2>list=dao.qureryex();
				for (Product2 product2 : list) {
					System.out.println(product2);
				}
				break;
			}

			default:
				System.out.println("Invalid Choice");
				break;
			}
			System.out.println("Do you want to continue y/n ");
			c = scanner.next().charAt(0);

		} while (c == 'y' || c == 'Y');

		System.out.println("App Terminated");

	}

}
