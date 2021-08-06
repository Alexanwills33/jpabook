package com.example.jpabook.chap8.loading.eager;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

//    @ManyToOne(fetch = FetchType.EAGER) // left outer join 수행
    @ManyToOne(fetch = FetchType.EAGER, optional = false) // inner join 수행
//    @JoinColumn(name = "TEAM_ID") // left outer join 수행
    @JoinColumn(name = "TEAM_ID", nullable = false) // inner join 수행
    // nullable = false 의미: NULL 허용하지 않음, 내부 조인 사용
    private Team team;
}