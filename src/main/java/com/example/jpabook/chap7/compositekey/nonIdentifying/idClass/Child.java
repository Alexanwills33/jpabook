package com.example.jpabook.chap7.compositekey.nonIdentifying.idClass;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

@Entity
public class Child {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "PARENT_ID1",
        referencedColumnName = "PARENT_ID1"),
        @JoinColumn(name = "PARENT_ID2",
        referencedColumnName = "PARENT_ID2")
// 예제처럼 @JoinColumn의 name 속성과 referencedColumnName 속성값이 같으면 생략 가능
    })
    private Parent parent;

    public Child() {
    }

    public Long getId() {
        return id;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }
}