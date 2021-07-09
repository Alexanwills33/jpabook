package com.example.jpabook.chap5;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class MainTest {

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
//            save(em); // 비즈니스 로직 실행(여기까지는 SQL문을 데이터베이스에 보내지 않는다)
//            queryLogicJoin(em);
//            updateRelation(em);
//            deleteRelation(em);
//            deleteMember(em); // 자식을 삭제하는 것이기 때문에 에러 안남
//            deleteTeam(em); // 부모를 삭제하는 것이기 때문에 에러 발생
//            biDirection(em);
//            testSave(em);
//            testSaveNonOwner(em);
//            test순수한객체_단방향(em);
//            test순수한객체_양방향(em);
//            testORM_양방향(em);
//            testORM_양방향_리팩토링(em);
//            testORM_양방향_리팩토링주의점(em);
            toString무한루프(em);
            tx.commit(); // [트랜잭션] - 커밋(여기에서 INSERT SQL문을 날린다)
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }

    public static void save(EntityManager em) {
        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        // 회원1 저장
        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1); // 연관관계 설정 member1 -> team1
        em.persist(member1);

        // 회원2 저장
        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1); // 연관관계 설정 member2 -> team1
        em.persist(member2);

        em.flush();
    }

    public static void queryLogicJoin(EntityManager em) {
        String jpql = "select m from Member m join m.team t where " + "t.name=:teamName";

        List<Member> resultList = em.createQuery(jpql, Member.class)
            .setParameter("teamName", "팀1")
            .getResultList();

        for (Member member : resultList) {
            System.out.println("member.getUsername() = " + member.getUsername());
        }
    }

    public static void updateRelation(EntityManager em) {
        // 새로운 팀2
        Team team2 = new Team("team2","팀2");
        em.persist(team2);

        // 회원1에 새로운 팀2 설정
        Member findMember1 = em.find(Member.class, "member1");
        findMember1.setTeam(team2);

        em.flush();
    }

    public static void deleteRelation(EntityManager em) {
        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null); // 연관관계 제거
    }

    public static void deleteMember(EntityManager em) {
        Member member1 = em.find(Member.class, "member1");
        Member member2 = em.find(Member.class, "member2");
        em.remove(member1);
        em.remove(member2);
    }

    public static void deleteTeam(EntityManager em) {
        Team team = em.find(Team.class, "team1");
        em.remove(team);
    }

    public static void biDirection(EntityManager em) {
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers(); // 팀->회원 객체 그래프 탐색
        System.out.println("Size = " + members.size());
        Member member1 = em.find(Member.class, "member1");
        Team team1 = member1.getTeam();
        System.out.println("멤버 1의 team1 = " + team1.getName());
        members.forEach(member -> System.out.println("멤버 = " + member));
    }

    public static void testSave(EntityManager em) {
        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        // 회원1 저장
        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        // 회원2 저장
        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    public static void testSaveNonOwner(EntityManager em) {
        // 회원1 저장
        Member member1 = new Member("member1", "회원1");
        em.persist(member1);

        // 회원2 저장
        Member member2 = new Member("member2", "회원2");
        em.persist(member2);

        // 팀1 저장
        Team team1 = new Team("team1", "팀1");

        // 연관관계의 주인이 아니기 때문에 이런 작업을 할 수 없다
        // 주인이 아닌 곳만 연관관계 설정
        team1.getMembers().add(member1); // 무시되는 코드
        team1.getMembers().add(member2); // 무시되는 코드

        em.persist(team1);
    }

    public static void test순수한객체_단방향(EntityManager em) {
        // 팀1
        Team team1 = new Team("team1", "팀1");
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");

        member1.setTeam(team1); // 연관관계 설정 member1 -> team1
        member2.setTeam(team1); // 연관관계 설정 member2 -> team1

        List<Member> members = team1.getMembers();
        System.out.println("members.size() = " + members.size());
    }

    public static void test순수한객체_양방향(EntityManager em) {
        // 팀1
        Team team1 = new Team("team1", "팀1");
        Member member1 = new Member("member1", "회원1");
        Member member2 = new Member("member2", "회원2");

        member1.setTeam(team1); // 연관관계 설정 member1 -> team1
        team1.getMembers().add(member1); // 연관관계 설정 team1 -> member1
        member2.setTeam(team1); // 연관관계 설정 member2 -> team1
        team1.getMembers().add(member2); // 연관관계 설정 team1 -> member2

        List<Member> members = team1.getMembers();
        System.out.println("members.size() = " + members.size());
    }

    public static void testORM_양방향(EntityManager em) {
        // 팀1 저장
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1); // 연관관계 설정 member1 -> team1
        team1.getMembers().add(member1); // 연관관계 설정 team1 -> member1

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1); // 연관관계 설정 member2 -> team1
        team1.getMembers().add(member2); // 연관관계 설정 team1 -> member2

        em.persist(member1);
        em.persist(member2);
    }

    public static void testORM_양방향_리팩토링(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1); // 연관관계 설정 member1 -> team1

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1); // 연관관계 설정 member2 -> team1

        em.persist(member1);
        em.persist(member2);
    }

    public static void testORM_양방향_리팩토링주의점(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        Team team2 = new Team("team2", "팀2");
        em.persist(team1);
        em.persist(team2);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1); // 연관관계 설정 member1 -> team1
        //추가
        member1.setTeam(team2);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1); // 연관관계 설정 member2 -> team1

        em.persist(member1);
        em.persist(member2);
        System.out.println("member1.getTeam() = " + member1.getTeam());

        // 팀1에 member2만 있기를 기대햇으나 2명이 나옴 => 연관관계 변경시 값 삭제도 동시에 필요
        System.out.println("team1.getMembers().size() = " + team1.getMembers().size());
    }

    public static void toString무한루프(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        Team team2 = new Team("team2", "팀2");
        em.persist(team1);
        em.persist(team2);

        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1); // 연관관계 설정 member1 -> team1
        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1); // 연관관계 설정 member2 -> team1
        Member member3 = new Member("member3", "회원3");
        member3.setTeam(team1); // 연관관계 설정 member3 -> team1

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);

        // ToString Annotation 으로 인한 무한루프
        // 1. member1.toString()에서 member1.getTeam()을 호출하고
        // 2. member1.getTeam()에서는 member1.getTeam().getMember()를 호출하게된다
        // 1, 2과정 반복
        System.out.println("member1 = " + member1);
        // 해결방법: lombok의 toString을 쓰지 않던가, 재정의를 한다.
    }
}