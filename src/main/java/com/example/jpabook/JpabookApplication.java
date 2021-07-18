package com.example.jpabook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
//    exclude = {CRUD.class, Board.class, ExamMergeMain.class, JpaMain.class, Member.class}
)
public class JpabookApplication {


    public static void main(String[] args) {
        SpringApplication.run(JpabookApplication.class, args);
    }
}