package com.example.jpabook.chap9.basic.valueType;

import java.util.Date;
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
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    // 집 주소 표현
    private String city;
    private String street;
    private String zipcode;
}