package com.project.back_end.services;

import com.project.back_end.models.Doctor;
import com.project.back_end.repositories.DoctorRepository;
import com.project.back_end.repositories.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // ✅ GET ALL DOCTORS
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // ✅ ADD DOCTOR
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // ✅ GET BY ID
    public Doctor getDoctorById(Long id) {
        Optional<Doctor> doc = doctorRepository.findById(id);
        return doc.orElse(null);
    }

    // ✅ DELETE
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}