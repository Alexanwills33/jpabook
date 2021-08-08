package com.example.jpabook.chap9.basic.embeddedType;

import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 근무 기간
    @Embedded
    private Period workPeriod;

    // 집 주소
    @Embedded
    private Address homeAddress;
}