package com.example.jpabook.chap9.collection;

public class valueDiff {

    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        boolean result = (a == b) ? true : false;
        System.out.println("result = " + result);

        Address c = new Address("서울시","종로구","1번지");
        Address d = new Address("서울시","종로구","1번지");
        result = (c == d) ? true : false;
        System.out.println("result = " + result);

        EqualsHashedAddress e = new EqualsHashedAddress("서울시","종로구","1번지");
        EqualsHashedAddress f = new EqualsHashedAddress("서울시","종로구","1번지");
        result = (e == f) ? true : false; // Hash 비교
        System.out.println("result = " + result);

        result = (e.equals(f)) ? true : false; // 내부 값 비교
        System.out.println("result = " + result);

    }
}