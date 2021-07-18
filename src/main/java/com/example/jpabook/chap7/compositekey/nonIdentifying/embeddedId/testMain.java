package com.example.jpabook.chap7.compositekey.nonIdentifying.embeddedId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class testMain {
    public static void main(String[] args) {
        // [엔티티 매니저 팩토리] - 생성
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("jpabook");
        // [엔티티 매니저] - 생성
        EntityManager em = emf.createEntityManager();
        // [트랜잭션] - 획득
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

    private static void save(EntityManager em) {
        Parent parent = new Parent();
        ParentId parentId = new ParentId("myId1","myId2");
//        ParentId parentId = new ParentId();
//        parentId.setId1("myId1");
        parent.setId(parentId);
        parent.setName("parentName");
        em.persist(parent);
    }

    private static void find(EntityManager em) {
        ParentId parentId = new ParentId("myId1", "myId2");
        Parent parent = em.find(Parent.class, parentId);
        System.out.println("parent.getName() = " + parent.getName());
    }

}