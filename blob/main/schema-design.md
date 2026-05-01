# Smart Clinic Management System - Schema Design

---

## MySQL Database Design

### Table: admins
- id: BIGINT, Primary Key, Auto Increment
- username: VARCHAR(100), NOT NULL
- password: VARCHAR(255), NOT NULL

---

### Table: patients
- id: BIGINT, Primary Key, Auto Increment
- name: VARCHAR(100), NOT NULL
- email: VARCHAR(100), UNIQUE, NOT NULL
- password: VARCHAR(255), NOT NULL
- phone: VARCHAR(10), NOT NULL
- address: VARCHAR(255), NOT NULL

---

### Table: doctors
- id: BIGINT, Primary Key, Auto Increment
- name: VARCHAR(100), NOT NULL
- specialty: VARCHAR(50), NOT NULL
- email: VARCHAR(100), UNIQUE, NOT NULL
- password: VARCHAR(255), NOT NULL
- phone: VARCHAR(10), NOT NULL

---

### Table: appointments
- id: BIGINT, Primary Key, Auto Increment
- doctor_id: BIGINT, Foreign Key → doctors(id)
- patient_id: BIGINT, Foreign Key → patients(id)
- appointment_time: DATETIME, NOT NULL
- status: INT (0 = Scheduled, 1 = Completed), NOT NULL

---

### Relationships
- One doctor can have many appointments
- One patient can have many appointments
- Each appointment is linked to one doctor and one patient

---

## MongoDB Collection Design

### Collection: prescriptions

```json
{
  "_id": "ObjectId('64abc123456')",
  "patientName": "John Doe",
  "appointmentId": 101,
  "medication": "Paracetamol",
  "dosage": "500mg",
  "doctorNotes": "Take twice daily after meals"
}
