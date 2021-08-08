package com.example.jpabook.chap9.sameEmbedTypes;

import javax.persistence.Embeddable;

@Embeddable
public class Zipcode {
    private String zip;
    private String plusFour;
}