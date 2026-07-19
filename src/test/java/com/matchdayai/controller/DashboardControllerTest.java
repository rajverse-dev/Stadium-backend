package com.matchdayai.controller;

import com.matchdayai.dto.DashboardStats;
import com.matchdayai.service.DashboardService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DashboardControllerTest {

    @InjectMocks
    private DashboardController dashboardController;

    @Mock
    private DashboardService dashboardService;

    @Test
    void getDashboardStats_ReturnsStats() throws Exception {
        DashboardStats mockStats = new DashboardStats();
        mockStats.setVisitors(50000);
        mockStats.setEmergencyAlerts(2);
        mockStats.setAnnouncements(5);

        Mockito.when(dashboardService.getDashboardStats()).thenReturn(mockStats);

        ResponseEntity<DashboardStats> response = dashboardController.getDashboardStats();
        
        assertEquals(200, response.getStatusCode().value());
        assertEquals(50000, response.getBody().getVisitors());
        assertEquals(2, response.getBody().getEmergencyAlerts());
    }
}
