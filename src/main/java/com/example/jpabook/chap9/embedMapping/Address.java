package com.example.jpabook.chap9.embedMapping;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    @Embedded
    private Zipcode zipcode; // 임베디드 타입 포함
}