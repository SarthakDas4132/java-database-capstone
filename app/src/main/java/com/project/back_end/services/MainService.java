package com.project.back_end.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import com.project.back_end.models.Admin;
import com.project.back_end.models.Patient;
import com.project.back_end.dto.Login;
import com.project.back_end.repositories.AdminRepository;
import com.project.back_end.repositories.DoctorRepository;
import com.project.back_end.repositories.PatientRepository;

import java.util.Map;
import java.util.HashMap;

@Service
public class MainService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    // ================= ADMIN LOGIN =================
    public ResponseEntity<Map<String,String>> validateAdmin(Admin admin) {

        Map<String,String> res = new HashMap<>();

        Admin db = adminRepository.findByUsername(admin.getUsername());

        if (db != null && db.getPassword().equals(admin.getPassword())) {
            res.put("token", tokenService.generateToken(admin.getUsername()));
            return ResponseEntity.ok(res);
        }

        res.put("message", "Invalid");
        return ResponseEntity.status(401).body(res);
    }

    // ================= TOKEN VALIDATION =================
    public ResponseEntity<Map<String,String>> validateToken(String token, String role) {

        Map<String,String> res = new HashMap<>();

        boolean valid = tokenService.validateToken(token, role);

        if (valid) {
            return ResponseEntity.ok(res);
        }

        res.put("message", "Unauthorized");
        return ResponseEntity.status(401).body(res);
    }

    // ================= PATIENT CHECK =================
    public boolean validatePatient(Patient patient) {

        return patientRepository.findByEmailOrPhone(
                patient.getEmail(),
                patient.getPhone()
        ) == null;
    }

    // ================= PATIENT LOGIN =================
    public ResponseEntity<Map<String,String>> validatePatientLogin(Login login) {

        Map<String,String> res = new HashMap<>();

        var patient = patientRepository.findByEmail(login.getIdentifier());

        if (patient != null && patient.getPassword().equals(login.getPassword())) {
            res.put("token", tokenService.generateToken(patient.getEmail()));
            return ResponseEntity.ok(res);
        }

        res.put("message", "Invalid credentials");
        return ResponseEntity.status(401).body(res);
    }

    // ================= FILTER PATIENT (TEMP FIX) =================
    public ResponseEntity<Map<String,Object>> filterPatient(String condition, String name, String token) {

        Map<String,Object> res = new HashMap<>();
        res.put("message", "Filter not implemented yet");

        return ResponseEntity.ok(res);
    }
}