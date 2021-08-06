package com.example.jpabook.chap8.orphan;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Parent {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "parent", orphanRemoval = true, cascade = ALL)
//    @OneToMany(mappedBy = "parent", cascade = ALL)
//    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Child> children = new ArrayList<>();
}