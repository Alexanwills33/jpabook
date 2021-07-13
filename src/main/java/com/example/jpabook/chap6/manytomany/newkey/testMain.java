package com.example.jpabook.chap6.manytomany.newkey;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class testMain {

    public static void main(String[] args) {
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin(); // [트랜잭션] - 시작
            save(em);
            find(em);
            tx.commit(); // [트랜잭션] - 커밋(여기에서 INSERT SQL문을 날린다)
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void save(EntityManager em) {
        // 회원 저장
        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
        em.persist(member1);

        // 상품 저장
        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품1");
        em.persist(productA);

        // 주문 저장
        Order order = new Order();
        order.setMember(member1); // 주문회원 - 연관관계 설정
        order.setProduct(productA); // 주문상품 - 연관관계 설정
        order.setOrderAmount(2); // 주문수량
        order.setOrderDate(new Date());
        em.persist(order);
    }

    public static void find(EntityManager em) {

        Long orderId = 1L;
        Order order = em.find(Order.class, orderId);

        Member member = order.getMember();
        Product product = order.getProduct();

        System.out.println("member.getUsername() = " + member.getUsername());
        System.out.println("product.getName() = " + product.getName());
        System.out.println("order.getOrderAmount() = " + order.getOrderAmount());


    }
}