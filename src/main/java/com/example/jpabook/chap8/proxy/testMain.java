package com.example.jpabook.chap8.proxy;

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
            printUserAndTeam(em, 1L);
            printUser(em,1L);
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
        // 팀 저장
        Team team = new Team();
        team.setName("팀1");
        em.persist(team);

        // 회원 저장
        Member member = new Member();
        member.setUsername("회원1");
        member.setTeam(team);
        em.persist(member);
        em.clear();
    }

    private static void printUserAndTeam(EntityManager em, Long memberId) {
        // 회원과 팀 정보를 출력하는 비즈니스 로직
        Member member = em.find(Member.class, memberId);
        Team team = member.getTeam();
        System.out.println("회원 이름: " + member.getUsername());
        System.out.println("소속팀: " + team.getName());
        em.flush();
        em.clear();
    }

    private static void printUser(EntityManager em, Long memberId) {
        // 회원 정보만 출력하는 비즈니스 로직
        Member member = em.find(Member.class, memberId); // 바로 불러옴
        boolean isLoaded = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member);
        System.out.println("Member isLoaded = " + isLoaded);
        em.clear(); // 영속성 비워주기
        // 이미 실제 엔티티 조회해서 영속성 컨텍스트에 엔티티가 존재할경우 프록시 호출해도 엔티티 가져온다
        Member member1 = em.getReference(Member.class, memberId); // 프록시 객체
//        System.out.println(member.getClass().getName());
        System.out.println("Proxy: " + member1.getClass().getName());
//        System.out.println("회원 이름: " + member.getUsername());
        System.out.println("회원 이름: " + member1.getUsername());
        boolean isLoaded1 = em.getEntityManagerFactory().getPersistenceUnitUtil().isLoaded(member);
        System.out.println("Member1 isLoaded = " + isLoaded1);
        em.flush();
        em.clear();
    }
}