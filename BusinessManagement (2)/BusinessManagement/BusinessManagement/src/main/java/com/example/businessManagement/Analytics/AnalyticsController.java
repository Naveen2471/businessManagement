package com.example.businessManagement.Analytics;

import com.example.businessManagement.Common.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/api/v1/analytics")
    public class AnalyticsController {
        @PreAuthorize("hasRole('ADMIN')")
        @GetMapping
        public ApiResponse<String> data() {
            return new ApiResponse<>(true, "Analytics data", "OK");
        }
    }


