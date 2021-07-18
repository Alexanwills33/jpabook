package com.example.jpabook.chap7.mappedsuperclass;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@AttributeOverrides({
    @AttributeOverride(name = "id", column = @Column(name = "SELLER_ID")),
    @AttributeOverride(name = "number", column = @Column(name = "PHONE_NUMBER"))
})
public class Seller extends BaseEntity{
    // ID 상속 => SELLER_ID
    // NAME 상속
    // createdDate 상속
    // modifiedDate 상속
    // number 상속 => PHONE_NUMBER
    private String shopName;
}