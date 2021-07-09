package com.example.jpabook.chap5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
//@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBER")
//@ToString // 무한루프가 일어난다
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team; // 팀의 참조를 보관

    public void setTeam(Team team) { // 연관관계 편의 메소드
        // 기존 팀과의 관계를 제거
        if (this.team != null) {
            this.team.getMembers().remove(this);
        }
        this.team = team;
        team.getMembers().add(this);
    }

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public String toString() {
        return "Member{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            '}';
    }
}