package com.example.jpabook.chap9.sameEmbedTypes;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    /* 매핑하는 컬럼명이 중복되서 안돌아감
    @Embedded
    private Address homeAddress;

    @Embedded
    private Address companyAddress;
     */

    @Embedded
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "COMPANY_CITY")),
        @AttributeOverride(name = "street", column = @Column(name = "COMPANY_STREET")),
        @AttributeOverride(name = "state", column = @Column(name = "COMPANY_STATE")),
        @AttributeOverride(name = "zipcode", column = @Column(name = "COMPANY_ZIPCODE"))
    })
    private Address companyAddress;
}