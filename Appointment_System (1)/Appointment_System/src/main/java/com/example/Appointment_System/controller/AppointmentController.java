package com.example.Appointment_System.controller;

import com.example.Appointment_System.model.Appointment;
import com.example.Appointment_System.model.Doctor;
import com.example.Appointment_System.model.Patient;
import com.example.Appointment_System.repository.DoctorRepository;
import com.example.Appointment_System.repository.PatientRepository;
import com.example.Appointment_System.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        Optional<Patient> patient = patientRepository.findById(appointmentDTO.getPatientId());
        Optional<Doctor> doctor = doctorRepository.findById(appointmentDTO.getDoctorId());

        if (patient.isEmpty() || doctor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Appointment appointment = new Appointment(
                patient.get(),
                doctor.get(),
                appointmentDTO.getAppointmentTime(),
                appointmentDTO.getStatus()
        );

        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO appointmentDTO) {
        Optional<Patient> patient = patientRepository.findById(appointmentDTO.getPatientId());
        Optional<Doctor> doctor = doctorRepository.findById(appointmentDTO.getDoctorId());

        if (patient.isEmpty() || doctor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Appointment appointment = new Appointment(
                patient.get(),
                doctor.get(),
                appointmentDTO.getAppointmentTime(),
                appointmentDTO.getStatus()
        );

        Appointment updatedAppointment = appointmentService.updateAppointment(id, appointment);
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}