package com.example.jpabook.chap6.manytomany.newentity;

import java.util.Date;
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

        // 회원상품 저장
        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMember(member1); // 주문회원 - 연관관계 설정
        memberProduct.setProduct(productA); // 주문상품 - 연관관계 설정
        memberProduct.setOrderAmount(2); // 주문수량
        memberProduct.setOrderDate(new Date());

        em.persist(memberProduct);
    }

    public static void find(EntityManager em) {
        // 기본 키 값 생성
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMember("member1");
        memberProductId.setProduct("productA");

        MemberProduct memberProduct = em.find(MemberProduct.class, memberProductId);
        Member member = memberProduct.getMember();
        Product product = memberProduct.getProduct();

        System.out.println("member.getUsername() = " + member.getUsername());
        System.out.println("product.getName() = " + product.getName());
        System.out.println("memberProduct.getOrderAmount() = " + memberProduct.getOrderAmount());
    }
}