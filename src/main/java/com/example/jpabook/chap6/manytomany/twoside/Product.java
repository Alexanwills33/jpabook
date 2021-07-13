package com.example.jpabook.chap6.manytomany.twoside;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @Column(name = "PRODUCT_ID")
    private String id;

    private String name;

    @ManyToMany(mappedBy = "products") // 역방향 추가
    private List<Member> members = new ArrayList<>(); // 초기화를 해줘야 널포인터 익셉션을 피할 수 있다.
}