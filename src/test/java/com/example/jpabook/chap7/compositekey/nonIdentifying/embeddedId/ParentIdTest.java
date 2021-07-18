package com.example.jpabook.chap7.compositekey.nonIdentifying.embeddedId;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParentIdTest {
    @Test
    @DisplayName("Equals And HashCode가 있는 클래스")
    void test1() {
        ParentId id1 = new ParentId();
        id1.setId1("myId1");
        id1.setId2("myId2");

        ParentId id2 = new ParentId();
        id2.setId1("myId1");
        id2.setId2("myId2");
        assertEquals(id1,id2);
    }

    @Test
    @DisplayName("Equals And HashCode가 없는 클래스")
    void test2() {
        ParentIdNonEH id1 = new ParentIdNonEH();
        id1.setId1("myId1");
        id1.setId2("myId2");

        ParentIdNonEH id2 = new ParentIdNonEH();
        id2.setId1("myId1");
        id2.setId2("myId2");

        assertNotEquals(id1,id2);
    }
}