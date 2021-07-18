package com.example.jpabook.chap7.supersub.single;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@DiscriminatorColumn(name = "B")
public class Book extends Item {
    private String author;
    private String isbn;
}