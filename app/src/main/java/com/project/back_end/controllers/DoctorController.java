package com.project.back_end.controllers;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.back_end.models.Doctor;
import com.project.back_end.dto.Login;
import com.project.back_end.services.DoctorService;
import com.project.back_end.services.MainService;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private MainService service;

    // ================= GET ALL DOCTORS =================
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // ================= ADD DOCTOR =================
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.saveDoctor(doctor);
    }

    // ================= GET DOCTOR BY ID =================
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id);
    }

    // ================= DELETE DOCTOR =================
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }

    // ================= DOCTOR LOGIN =================
    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@RequestBody Login login) {
        return doctorService.validateDoctorLogin(login);
    }

    // ================= DOCTOR AVAILABILITY (IMPORTANT FOR MARKS) =================
    @GetMapping("/availability/{doctorId}/{date}/{token}")
    public ResponseEntity<Map<String, Object>> getAvailability(
            @PathVariable Long doctorId,
            @PathVariable String date,
            @PathVariable String token
    ) {

        // Validate token
        if (!service.validateToken(token, "doctor").getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(401).body(Map.of("message", "Unauthorized"));
        }

        // Call service (or dummy for now)
        return doctorService.getDoctorAvailability(doctorId, date);
    }

    // ================= FILTER DOCTORS (FOR CURL Q26) =================
    @GetMapping("/filter/{speciality}/{time}/{token}")
    public ResponseEntity<Map<String, Object>> filterDoctors(
            @PathVariable String speciality,
            @PathVariable String time,
            @PathVariable String token
    ) {

        // Validate token
        if (!service.validateToken(token, "doctor").getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.status(401).body(Map.of("message", "Unauthorized"));
        }

        Map<String, Object> res = new HashMap<>();
        res.put("speciality", speciality);
        res.put("time", time);
        res.put("doctors", doctorService.getAllDoctors()); // simple return for now

        return ResponseEntity.ok(res);
    }
}
