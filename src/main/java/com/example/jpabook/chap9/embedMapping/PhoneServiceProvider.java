package com.example.jpabook.chap9.embedMapping;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PhoneServiceProvider {
    @Id
    String name;
}