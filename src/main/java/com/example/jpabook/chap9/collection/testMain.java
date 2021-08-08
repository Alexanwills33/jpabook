package com.example.jpabook.chap9.collection;

import java.util.List;
import java.util.Set;
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
            update(em);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void save(EntityManager em) {
        Member member = new Member();

        // 임베디드 값 타입
        member.setHomeAddress(new Address("통영","몽돌해수욕장","660-123"));

        // 기본값 타입 컬렉션
        member.getFavoriteFoods().add("짬뽕");
        member.getFavoriteFoods().add("짜장");
        member.getFavoriteFoods().add("탕수육");

        // 임베디드 값 타입 컬렉션
        member.getAddressHistory().add(new Address("서울", "강남", "123-123"));
        member.getAddressHistory().add(new Address("서울", "강북", "000-000"));

        em.persist(member);
        em.flush();
        em.clear();
    }

    private static void find(EntityManager em) {
        // SQL: SELECT ID, CITY, STREET, ZIPCODE FROM MEMBER WHERE ID = 1
        Member member = em.find(Member.class,1L);

        // 2. member.homeAddress
        Address homeAddress = member.getHomeAddress();

        // 3. member.favoriteFoods
        Set<String> favoriteFoods = member.getFavoriteFoods(); // LAZY

        // SQL: SELECT MEMBER_ID, FOOD_NAME FROM FAVORITE_FOODS WHERE MEMBER_ID = 1
        for (String favoriteFood : favoriteFoods) {
            System.out.println("favoriteFood = " + favoriteFood);
        }

        // 4. member.addressHistory
        List<Address> addressHistory = member.getAddressHistory(); // LAZY

        //SQL: SELECT MEMBER_ID, CITY, STREET, ZIPCODE FROM ADDRESS WHERE MEMBER_ID = 1
        addressHistory.get(0);
        em.flush();
        em.clear();
    }

    private static void update(EntityManager em) {
        Member member = em.find(Member.class, 1L);

        // 1. 임베디드 값 타입 수정
        member.setHomeAddress(new Address("새로운도시","신도시1","123456"));

        // 2. 기본값 타입 컬렉션 수정
        Set<String> favoriteFoods = member.getFavoriteFoods();
        favoriteFoods.remove("탕수육");
        favoriteFoods.add("치킨");

        // 3. 임베디드 값 타입 컬렉션 수정
        // 이런 값 타입은 Equals and Hashcode 를 꼭 구현해야한다
        List<Address> addressHistory = member.getAddressHistory();
        addressHistory.remove(new Address("서울", "강남", "123-123"));
        addressHistory.add(new Address("새로운 도시", "새로운 주소", "123-456"));
    }
}