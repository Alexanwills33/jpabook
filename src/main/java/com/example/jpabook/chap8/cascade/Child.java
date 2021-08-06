package com.example.jpabook.chap8.cascade;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Child {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Parent parent;
}