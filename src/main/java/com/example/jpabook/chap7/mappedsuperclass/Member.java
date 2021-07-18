package com.example.jpabook.chap7.mappedsuperclass;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverride(
    name="number", column = @Column(name = "PHONE_NUMBER")
)
public class Member extends BaseEntity{
    // ID 상속
    // NAME 상속
    // createdDate 상속
    // modifiedDate 상속
    // number 상속 => PHONE_NUMBER
    private String email;
}