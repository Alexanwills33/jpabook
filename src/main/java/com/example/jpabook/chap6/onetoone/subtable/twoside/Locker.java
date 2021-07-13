package com.example.jpabook.chap6.onetoone.subtable.twoside;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

// Locker.member는 지연 로딩할 수 있지만(연관관계의 주인)
// Member.locker는 지연 로딩이 불가능하다.
// 프록시를 사용할 때 외래키를 직접 관리하지 않는 일대일 관계는 지연 로딩으로 설정해도 즉시 로딩된다
@Entity
public class Locker {
    @Id
    @GeneratedValue
    @Column(name = "LOCKER_ID")
    private Long id;

    private String name;

    @OneToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}