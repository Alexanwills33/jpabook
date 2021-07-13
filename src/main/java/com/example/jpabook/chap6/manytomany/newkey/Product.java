package com.example.jpabook.chap6.manytomany.newkey;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "product")
    private List<Order> orders = new ArrayList<>();

}