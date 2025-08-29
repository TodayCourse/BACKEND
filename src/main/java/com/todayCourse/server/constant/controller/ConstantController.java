package com.todayCourse.server.constant.controller;

import com.todayCourse.server.constant.type.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/constants")
public class ConstantController {

    @GetMapping
    public Map<String, List<Map<String, String>>> getAllConstants() {
        Map<String, List<Map<String, String>>> result = new HashMap<>();

        // 경비
        result.put("costTypes",
                Arrays.stream(CostType.values())
                        .map(s -> Map.of("key", s.name(), "label", s.getCostType()))
                        .toList()
        );

        // 지역
        result.put("regions",
                Arrays.stream(Region.values())
                        .map(s -> Map.of("key", s.name(), "label", s.getRegion()))
                        .toList()
        );

        // 계절
        result.put("seasons",
                Arrays.stream(Season.values())
                        .map(s -> Map.of("key", s.name(), "label", s.getSeason()))
                        .toList()
        );

        // 이동수단
        result.put("vehicles",
                Arrays.stream(Vehicle.values())
                        .map(s -> Map.of("key", s.name(), "label", s.getVehicle()))
                        .toList()
        );

        return result;
    }
}
