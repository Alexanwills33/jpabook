package com.example.jpabook.chap7.compositekey.nonIdentifying.idClass;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hello {

    @Id
    private String id;

//    @Id // Error!!
    private String id2;

    /* JPA는 영속성 컨텍스트에 엔티티를 보관할 때 엔티티의 식별자를 키로 사용한다.
    그리고 식별자를 구분하기 위해 equals와 hashCode를 사용해서 동등성 비교를 한다.
    그런데 식별자 필드가 하나일 때는 보통 자바의 기본 타입을 사용하므로 문제가 없지만,
    식별자 필드가 2개 이상이면 별도의 식별자 클래스를 만들고 그곳에 equals/hashCode를 구현해야 한다.
     */
}