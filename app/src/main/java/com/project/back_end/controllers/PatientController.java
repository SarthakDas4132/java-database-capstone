package com.project.back_end.controllers;

import com.project.back_end.models.Patient;
import com.project.back_end.dto.Login;
import com.project.back_end.services.PatientService;
import com.project.back_end.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private MainService service;

    // 1. GET PATIENT DETAILS
    @GetMapping("/{token}")
    public ResponseEntity<Map<String, Object>> getDetails(@PathVariable String token) {

        if (!service.validateToken(token, "patient").getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(401).body(Map.of("message", "Unauthorized"));
        }

        return patientService.getPatientDetails(token);
    }

    // 2. SIGNUP
    @PostMapping
    public ResponseEntity<Map<String, String>> create(@RequestBody Patient patient) {

        if (!service.validatePatient(patient)) {
            return ResponseEntity.status(409)
                    .body(Map.of("message", "Patient already exists"));
        }

        int res = patientService.createPatient(patient);

        if (res == 1)
            return ResponseEntity.ok(Map.of("message", "Signup successful"));

        return ResponseEntity.status(500)
                .body(Map.of("message", "Internal server error"));
    }

    // 3. LOGIN
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Login login) {
        return service.validatePatientLogin(login);
    }

    // 4. GET APPOINTMENTS
    @GetMapping("/{id}/{token}")
    public ResponseEntity<Map<String, Object>> getAppointments(
            @PathVariable Long id,
            @PathVariable String token) {

        if (!service.validateToken(token, "patient").getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(401).body(Map.of("message", "Unauthorized"));
        }

        return patientService.getPatientAppointment(id, token);
    }

    // 5. FILTER
    @GetMapping("/filter/{condition}/{name}/{token}")
    public ResponseEntity<Map<String, Object>> filter(
            @PathVariable String condition,
            @PathVariable String name,
            @PathVariable String token) {

        if (!service.validateToken(token, "patient").getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(401).body(Map.of("message", "Unauthorized"));
        }

        return service.filterPatient(condition, name, token);
    }
}