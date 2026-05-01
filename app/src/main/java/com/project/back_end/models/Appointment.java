package com.project.back_end.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    @NotNull(message = "doctor cannot be null")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull(message = "patient cannot be null")
    private Patient patient;

    @NotNull(message = "appointment time cannot be null")
    @Future(message = "Appointment time must be in the future")
    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @NotNull(message = "status cannot be null")
    @Column(nullable = false)
    private Integer status; // 0 = Scheduled, 1 = Completed

    // Default Constructor
    public Appointment() {
    }

    // Parameterized Constructor
    public Appointment(Doctor doctor, Patient patient, LocalDateTime appointmentTime, Integer status) {
        this.doctor = doctor;
        this.patient = patient;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    // ---------------- Getters & Setters ----------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    // ---------------- Helper Methods ----------------

    // Returns end time (1 hour after start)
    @Transient
    public LocalDateTime getEndTime() {
        return appointmentTime.plusHours(1);
    }

    // Returns only date
    @Transient
    public LocalDate getAppointmentDate() {
        return appointmentTime.toLocalDate();
    }

    // Returns only time
    @Transient
    public LocalTime getAppointmentTimeOnly() {
        return appointmentTime.toLocalTime();
    }
}