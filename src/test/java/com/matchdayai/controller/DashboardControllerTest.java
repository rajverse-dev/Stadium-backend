package com.matchdayai.controller;

import com.matchdayai.dto.DashboardStats;
import com.matchdayai.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DashboardController.class)
@AutoConfigureMockMvc(addFilters = false)
class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DashboardService dashboardService;

    @Test
    void getDashboardStats_ReturnsStats() throws Exception {
        DashboardStats mockStats = new DashboardStats();
        mockStats.setVisitors(50000);
        mockStats.setEmergencyAlerts(2);
        mockStats.setAnnouncements(5);

        Mockito.when(dashboardService.getDashboardStats()).thenReturn(mockStats);

        mockMvc.perform(get("/api/dashboard/stats"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.visitors").value(50000))
                .andExpect(jsonPath("$.emergencyAlerts").value(2))
                .andExpect(jsonPath("$.announcements").value(5));
    }
}
