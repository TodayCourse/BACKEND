package com.todayCourse.server.category.entity;

import com.todayCourse.server.audit.Auditable;
import com.todayCourse.server.travel.entity.Travel;
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
public class TravelCategory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false)
    private String categoryName;

    @Column
    private int sortOrder;

    @Column
    private String regUserId;

    @Column
    private String mdfcUserId;

    // Category : Travel = 1 : N 관계 매핑
    @OneToMany(mappedBy = "travelCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Travel> travelList = new ArrayList<>();
    
    public void addTravel(Travel travel) {
        travelList.add(travel);
        travel.setTravelCategory(this);
    }
}
