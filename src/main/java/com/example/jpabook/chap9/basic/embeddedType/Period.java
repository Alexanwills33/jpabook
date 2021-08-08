package com.example.jpabook.chap9.basic.embeddedType;

import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class Period {
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    public boolean isWork(Date date) {
        //.. 값 타입을 위한 메소드를 정의할 수 있다.
        return true;
    }
}