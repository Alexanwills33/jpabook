package com.example.jpabook.chap7.compositekey.nonIdentifying.embeddedId;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class Parent {
    @EmbeddedId
    private ParentId id;

    private String name;

    public Parent(ParentId id, String name) {
        this.id = id;
        this.name = name;
    }

    public ParentId getId() {
        return id;
    }

    public void setId(ParentId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parent() {
    }
}