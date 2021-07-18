package com.example.jpabook.chap7.supersub.perclass;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
public class Movie extends Item {
    private String director;
    private String actor;
}