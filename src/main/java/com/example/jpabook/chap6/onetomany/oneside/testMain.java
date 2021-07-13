package com.example.jpabook.chap6.onetomany.oneside;

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
            testSave(em);
            tx.commit(); // [트랜잭션] - 커밋(여기에서 INSERT SQL문을 날린다)
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void testSave(EntityManager em) {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        Team team1 = new Team("team1");
        team1.getMembers().add(member1);
        team1.getMembers().add(member2);

        em.persist(member1); // INSERT-member1
        em.persist(member2); // INSERT-member2
        em.persist(team1); // INSERT-team1, UPDATE-member1.fk, UPDATE-member2.fk
        // 발생하는 쿼리가 5번이며
        // 추가로 update쿼리는 member_id=?를 통해서 발생한다.
        // => 일대다 단방향 매핑보다는 다대일 양방향 매핑을 사용하자!
    }
}