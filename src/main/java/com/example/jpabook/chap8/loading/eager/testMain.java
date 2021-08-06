package com.example.jpabook.chap8.loading.eager;

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
            eagerLoading(em);
//            printUserAndTeam(em, 1L);
//            printUser(em,1L);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    private static void eagerLoading(EntityManager em) {
//        Member member = em.find(Member.class, "member1");
        Member member = em.find(Member.class, 1L);
        Team team = member.getTeam(); // 객체 그래프 탐색
        /*
        select
            member0_.id as id1_0_0_,
            member0_.TEAM_ID as team_id3_0_0_,
            member0_.username as username2_0_0_,
            team1_.TEAM_ID as team_id1_1_1_,
            team1_.name as name2_1_1_
        from
            Member member0_
         */
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
        Member member = em.find(Member.class, "member1");
        Team team = member.getTeam();
        System.out.println("회원 이름: " + member.getUsername());
        System.out.println("소속팀: " + team.getName());
        em.flush();
        em.clear();
    }

}