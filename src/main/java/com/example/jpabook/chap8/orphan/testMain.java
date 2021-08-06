package com.example.jpabook.chap8.orphan;

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
            saveNoCascade(em);
            delete(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void delete(EntityManager em) {
        Parent parent = em.find(Parent.class, 1L);
//        parent.getChildren().clear();
        parent.getChildren().remove(0);
//        parent.getChildren().remove(1);
        em.flush();
        em.clear();
    }

    private static void saveNoCascade(EntityManager em) {
        // 부모 저장
        Parent parent = new Parent();
        em.persist(parent);

        // 1번 자식 저장
        Child child1 = new Child();
        child1.setParent(parent); // 자식 -> 부모 연관관계 설정
        child1.setName("자식1");
        parent.getChildren().add(child1); // 부모 -> 자식
        em.persist(child1);

        // 2번 자식 저장
        Child child2 = new Child();
        child2.setParent(parent); // 자식 -> 부모 연관관계 설정
        child2.setName("자식2");
        parent.getChildren().add(child2); // 부모 -> 자식
        em.persist(child2);

        em.flush();
        em.clear();
    }

    private static void saveWithCascade(EntityManager em) {
        Child child1 = new Child();
        Child child2 = new Child();

        Parent parent = new Parent();
        child1.setParent(parent);
        child2.setParent(parent);
        parent.getChildren().add(child1);
        parent.getChildren().add(child2);

        // 부모 저장, 연관된 자식들도 저장
        em.persist(parent);
    }


}