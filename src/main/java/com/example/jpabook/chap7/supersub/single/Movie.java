package com.example.jpabook.chap7.supersub.single;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "M")
public class Movie extends Item {
    private String director;
    private String actor;
}