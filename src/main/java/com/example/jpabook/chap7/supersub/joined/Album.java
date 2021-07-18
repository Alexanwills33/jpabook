package com.example.jpabook.chap7.supersub.joined;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

@Entity
@DiscriminatorColumn(name = "A")
public class Album extends Item{
    private String artist;
}