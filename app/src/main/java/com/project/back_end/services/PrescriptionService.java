package com.project.back_end.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.HashMap;

import com.project.back_end.models.Prescription;
import com.project.back_end.repositories.PrescriptionRepository;

@Service
public class PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public ResponseEntity<Map<String, String>> savePrescription(Prescription prescription) {

        Map<String, String> res = new HashMap<>();

        try {
            prescriptionRepository.save(prescription);
            res.put("message", "Prescription saved");
            return ResponseEntity.status(201).body(res);
        } catch (Exception e) {
            res.put("message", "Error saving");
            return ResponseEntity.status(500).body(res);
        }
    }

    public ResponseEntity<Map<String, Object>> getPrescription(Long appointmentId) {

        Map<String, Object> res = new HashMap<>();

        try {
            res.put("data", prescriptionRepository.findByAppointmentId(appointmentId));
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            res.put("message", "Error");
            return ResponseEntity.status(500).body(res);
        }
    }
}