package com.todayCourse.server.course.entity;

import com.todayCourse.server.audit.Auditable;
import com.todayCourse.server.travel.entity.Travel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column
    private String placeName;

    @Column
    private String address;

    @Column
    private String lon;

    @Column
    private String lat;

    @Column
    private String openTime;

    @Column
    private String closeTime;

    @Column
    private String regUserId;

    @Column
    private String mdfcUserId;

    // Course : Travel = N : 1 관계 매핑
    @ManyToOne
    @JoinColumn(name = "travel_id", nullable = false)
    private Travel travel;

    public void setTravel(Travel travel) {
        this.travel = travel;
    }
}
