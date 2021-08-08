package com.example.jpabook.chap9.embedMapping;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class PhoneNumber {
    private String areaCode;
    private String localNumber;
    @ManyToOne
    PhoneServiceProvider provider; // 엔티티 참조
}