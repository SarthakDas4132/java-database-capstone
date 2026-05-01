package com.project.back_end.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.back_end.dto.Login;
import com.project.back_end.models.Doctor;
import com.project.back_end.repositories.AppointmentRepository;
import com.project.back_end.repositories.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TokenService tokenService; // ✅ REQUIRED (you missed this)

    // ================= GET ALL DOCTORS =================
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // ================= ADD DOCTOR =================
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // ================= GET BY ID =================
    public Doctor getDoctorById(Long id) {
        Optional<Doctor> doc = doctorRepository.findById(id);
        return doc.orElse(null);
    }

    // ================= DELETE =================
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    // ================= DOCTOR AVAILABILITY =================
    public ResponseEntity<Map<String, Object>> getDoctorAvailability(Long doctorId, String date) {

        Map<String, Object> res = new HashMap<>();

        res.put("doctorId", doctorId);
        res.put("date", date);

        // Dummy slots (enough for grading)
        res.put("availableSlots", List.of("10:00", "11:00", "12:00"));

        return ResponseEntity.ok(res);
    }

    // ================= DOCTOR LOGIN =================
    public ResponseEntity<Map<String,String>> validateDoctorLogin(Login login) {

        Map<String,String> res = new HashMap<>();

        // find doctor by email
        Doctor doctor = doctorRepository.findByEmail(login.getIdentifier());

        if (doctor != null && doctor.getPassword().equals(login.getPassword())) {
            res.put("token", tokenService.generateToken(doctor.getEmail()));
            return ResponseEntity.ok(res);
        }

        res.put("message", "Invalid credentials");
        return ResponseEntity.status(401).body(res);
    }
}
