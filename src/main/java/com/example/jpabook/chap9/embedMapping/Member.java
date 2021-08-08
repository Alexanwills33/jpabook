package com.example.jpabook.chap9.embedMapping;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address; // 임베디드 타입 포함

    @Embedded
    private PhoneNumber phoneNumber; // 임베디드 타입 포함
}