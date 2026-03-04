package com.klu.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.klu.entity.Product;
import com.klu.loader.ProductDataLoader;
import com.klu.util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args) {

        SessionFactory factory = HibernateUtil.getSessionFactory();
        Session session = factory.openSession();

        try {

            // ✅ FIRST TIME ONLY — RUN ONCE, THEN COMMENT
            ProductDataLoader.loadSampleProducts(session);

            // HQL operations
            sortProductsByPriceAscending(session);
            sortProductsByPriceDescending(session);
            sortProductsByQuantityDescending(session);
            getFirstThreeProducts(session);
            getNextThreeProducts(session);
            countTotalProducts(session);

        } finally {
            session.close();
            factory.close();
        }
    }

    // 🔹 Sort by price ASC
    public static void sortProductsByPriceAscending(Session session) {
        String hql = "FROM Product p ORDER BY p.price ASC";
        List<Product> list = session.createQuery(hql, Product.class).list();

        System.out.println("\n=== Price ASC ===");
        list.forEach(System.out::println);
    }

    // 🔹 Sort by price DESC
    public static void sortProductsByPriceDescending(Session session) {
        String hql = "FROM Product p ORDER BY p.price DESC";
        List<Product> list = session.createQuery(hql, Product.class).list();

        System.out.println("\n=== Price DESC ===");
        list.forEach(System.out::println);
    }

    // 🔹 Sort by quantity DESC
    public static void sortProductsByQuantityDescending(Session session) {
        String hql = "FROM Product p ORDER BY p.quantity DESC";
        List<Product> list = session.createQuery(hql, Product.class).list();

        System.out.println("\n=== Quantity DESC ===");
        list.forEach(p ->
                System.out.println(p.getName() + " - Qty: " + p.getQuantity()));
    }

    // 🔹 Pagination — first 3
    public static void getFirstThreeProducts(Session session) {
        Query<Product> q = session.createQuery("FROM Product", Product.class);
        q.setFirstResult(0);
        q.setMaxResults(3);

        System.out.println("\n=== First 3 Products ===");
        q.list().forEach(System.out::println);
    }

    // 🔹 Pagination — next 3
    public static void getNextThreeProducts(Session session) {
        Query<Product> q = session.createQuery("FROM Product", Product.class);
        q.setFirstResult(3);
        q.setMaxResults(3);

        System.out.println("\n=== Next 3 Products ===");
        q.list().forEach(System.out::println);
    }

    // 🔹 Count
    public static void countTotalProducts(Session session) {
        Long count = session
                .createQuery("SELECT COUNT(p) FROM Product p", Long.class)
                .uniqueResult();

        System.out.println("\nTotal Products: " + count);
    }
}