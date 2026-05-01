package com.project.back_end.services;

import com.project.back_end.models.Patient;
import com.project.back_end.models.Appointment;
import com.project.back_end.repositories.PatientRepository;
import com.project.back_end.repositories.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TokenService tokenService;

    // CREATE
    public int createPatient(Patient patient) {
        try {
            patientRepository.save(patient);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    // GET APPOINTMENTS
    public ResponseEntity<Map<String, Object>> getPatientAppointment(Long id, String token) {

        Map<String, Object> res = new HashMap<>();

        List<Appointment> list = appointmentRepository.findByPatientId(id);

        res.put("appointments", list);
        return ResponseEntity.ok(res);
    }

    // FILTER PAST/FUTURE
    public ResponseEntity<Map<String, Object>> filterByCondition(String condition, Long id) {

        Map<String, Object> res = new HashMap<>();

        int status = condition.equals("past") ? 1 : 0;

        List<Appointment> list =
                appointmentRepository.findByPatient_IdAndStatusOrderByAppointmentTimeAsc(id, status);

        res.put("appointments", list);
        return ResponseEntity.ok(res);
    }
    public ResponseEntity<Map<String, Object>> getPatientDetails(String token) {

    Map<String, Object> res = new HashMap<>();

    try {
        String email = tokenService.extractIdentifier(token);

        Patient patient = patientRepository.findByEmail(email);

        if (patient == null) {
            res.put("message", "Patient not found");
            return ResponseEntity.status(404).body(res);
        }

        res.put("patient", patient);
        return ResponseEntity.ok(res);

    } catch (Exception e) {
        res.put("message", "Error fetching details");
        return ResponseEntity.status(500).body(res);
    }
}
}