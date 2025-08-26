package com.todayCourse.server.travel.entity;

import com.todayCourse.server.audit.Auditable;
import com.todayCourse.server.category.entity.TravelCategory;
import com.todayCourse.server.constant.CostType;
import com.todayCourse.server.constant.Region;
import com.todayCourse.server.constant.Season;
import com.todayCourse.server.constant.Vehicle;
import com.todayCourse.server.course.entity.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Travel extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Region region;

    @Column(nullable = false)
    private String travelStartDt;

    @Column
    private String travelEndDt;

    @Column
    @Enumerated(EnumType.STRING)
    private CostType costType;

    @Column
    @Enumerated(EnumType.STRING)
    private Season season;

    @Column
    @Enumerated(EnumType.STRING)
    private Vehicle vehicle;

    @Column
    private String contents;

    @Column
    private String regUserId;

    @Column
    private String mdfcUserId;

    // Travel : Course = 1 : N 관계 매핑
    @OneToMany(mappedBy = "travel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courseList = new ArrayList<>();

    public void addCourse(Course course) {
        courseList.add(course);
        course.setTravel(this);
    }

    // Travel : Category = N : 1 관계 매핑
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private TravelCategory travelCategory;

    public void setTravelCategory(TravelCategory travelCategory) {
        this.travelCategory = travelCategory;
    }
}
