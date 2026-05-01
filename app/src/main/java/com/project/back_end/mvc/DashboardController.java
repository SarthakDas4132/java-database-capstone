package com.project.back_end.mvc;
import com.project.back_end.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import com.project.back_end.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class DashboardController {
    @Autowired
    private MainService service;

    @Autowired
    private TokenService tokenService;

    // ---------- ADMIN ----------
    @GetMapping("/adminDashboard/{token}")
    public String adminDashboard(@PathVariable String token) {

    if (service.validateToken(token, "admin").getStatusCode().is2xxSuccessful()) {
        return "admin/adminDashboard";
    }

    return "redirect:/";
}


    // ---------- DOCTOR ----------
    @GetMapping("/doctorDashboard/{token}")
    public String doctorDashboard(@PathVariable String token) {

    if (service.validateToken(token, "doctor").getStatusCode().is2xxSuccessful()) {
        return "doctor/doctorDashboard";
    }

    return "redirect:/";
}
}