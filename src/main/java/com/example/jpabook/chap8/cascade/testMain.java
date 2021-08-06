package com.example.jpabook.chap8.cascade;

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
//            saveNoCascade(em);
            saveWithCascade(em);
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
        Child findChild1 = em.find(Child.class, 2L);
        Child findChild2 = em.find(Child.class, 3L);

//        em.remove(findChild1);
//        em.remove(findChild2);
        em.remove(parent);
    }

    private static void saveNoCascade(EntityManager em) {
        // 부모 저장
        Parent parent = new Parent();
        em.persist(parent);

        // 1번 자식 저장
        Child child1 = new Child();
        child1.setParent(parent); // 자식 -> 부모 연관관계 설정
        parent.getChildren().add(child1); // 부모 -> 자식
        em.persist(child1);

        // 2번 자식 저장
        Child child2 = new Child();
        child2.setParent(parent); // 자식 -> 부모 연관관계 설정
        parent.getChildren().add(child2); // 부모 -> 자식
        em.persist(child2);
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