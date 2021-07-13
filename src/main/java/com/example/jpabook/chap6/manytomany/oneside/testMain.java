package com.example.jpabook.chap6.manytomany.oneside;

import java.util.List;
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

    /**
     * 코드가 실행되면 insert into Product, member, member_product 3개 쿼리가 날라간다
     */
    public static void save(EntityManager em) {
        Product productA = new Product();
        productA.setId("productA");
        productA.setName("상품A");
        em.persist(productA);

        Member member1 = new Member();
        member1.setId("member1");
        member1.setUsername("회원1");
        member1.getProducts().add(productA); // 연관관계 설정
        em.persist(member1);
    }

    public static void find(EntityManager em) {
        Member member = em.find(Member.class, "member1");
        List<Product> products = member.getProducts(); // 객체 그래프 탐색
        for (Product product : products) {
            System.out.println("product.getName() = " + product.getName());
        }
    }
}