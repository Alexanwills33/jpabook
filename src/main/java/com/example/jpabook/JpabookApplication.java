package com.example.jpabook;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
//    exclude = {CRUD.class, Board.class, ExamMergeMain.class, JpaMain.class, Member.class}
)
public class JpabookApplication {


    public static void main(String[] args) {
        SpringApplication.run(JpabookApplication.class, args);
    }
}