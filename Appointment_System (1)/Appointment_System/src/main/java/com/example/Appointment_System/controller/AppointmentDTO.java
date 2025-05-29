package com.example.Appointment_System.controller;

import java.time.LocalDateTime;

public class AppointmentDTO {

    private Long patientId;
    private Long doctorId;
    private LocalDateTime appointmentTime;
    private String status;

    // Default constructor
    public AppointmentDTO() {}

    // Parameterized constructor
    public AppointmentDTO(Long patientId, Long doctorId, LocalDateTime appointmentTime, String status) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentTime = appointmentTime;
        this.status = status;
    }

    // Getters and Setters
    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalDateTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}