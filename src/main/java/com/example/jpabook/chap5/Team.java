package com.example.jpabook.chap5;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TEAM")
//@ToString // 무한루프가 일어난다
public class Team {

    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;

    // == 추가 == //
    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<>();

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Team{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}