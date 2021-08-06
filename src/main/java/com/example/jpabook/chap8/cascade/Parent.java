package com.example.jpabook.chap8.cascade;

import static javax.persistence.CascadeType.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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

    @OneToMany(mappedBy = "parent", cascade = {PERSIST, REMOVE})
    // em.persist(), em.remove()를 실행할 때 바로 전이가 발생하지 않고
    // 플러시를 호출할 때 전이가 발생한다.
    private List<Child> children = new ArrayList<>();
}