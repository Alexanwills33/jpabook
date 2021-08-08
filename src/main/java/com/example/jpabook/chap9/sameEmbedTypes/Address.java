package com.example.jpabook.chap9.sameEmbedTypes;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Address {

    private String street;
    private String city;
    private String state;
    private String zipcode; // 임베디드 타입 포함
}