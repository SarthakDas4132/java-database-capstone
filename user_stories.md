# User Stories

---

## 👨‍💼 Admin User Stories

### 1. Admin Login
**Title:**
_As an admin, I want to log into the portal with my username and password, so that I can manage the platform securely._

**Acceptance Criteria:**
1. Admin can enter valid credentials
2. System authenticates admin successfully
3. Admin dashboard is displayed after login

**Priority:** High  
**Story Points:** 3  
**Notes:**
- Show error on invalid login

---

### 2. Admin Logout
**Title:**
_As an admin, I want to log out of the portal, so that I can protect system access._

**Acceptance Criteria:**
1. Admin can click logout button
2. Session is terminated
3. Redirect to login page

**Priority:** High  
**Story Points:** 2  

---

### 3. Add Doctor
**Title:**
_As an admin, I want to add doctors to the portal, so that patients can book appointments._

**Acceptance Criteria:**
1. Admin can enter doctor details
2. Doctor is saved in database
3. Doctor appears in doctor list

**Priority:** High  
**Story Points:** 5  

---

### 4. Delete Doctor
**Title:**
_As an admin, I want to delete a doctor's profile, so that outdated profiles are removed._

**Acceptance Criteria:**
1. Admin selects doctor
2. Doctor is removed from database
3. Confirmation message is shown

**Priority:** Medium  
**Story Points:** 4  

---

### 5. View Monthly Appointments (Stored Procedure)
**Title:**
_As an admin, I want to run a stored procedure to get monthly appointment counts, so that I can track usage._

**Acceptance Criteria:**
1. Stored procedure executes successfully
2. Monthly appointment count is displayed
3. Data is accurate

**Priority:** Medium  
**Story Points:** 6  

---

## 🧑‍🤝‍🧑 Patient User Stories

### 6. View Doctors
**Title:**
_As a patient, I want to view a list of doctors without logging in, so that I can explore options._

**Acceptance Criteria:**
1. Doctor list is visible
2. No login required
3. Basic doctor info is shown

**Priority:** High  
**Story Points:** 3  

---

### 7. Patient Sign Up
**Title:**
_As a patient, I want to sign up using email and password, so that I can book appointments._

**Acceptance Criteria:**
1. User enters valid email and password
2. Account is created
3. Confirmation is shown

**Priority:** High  
**Story Points:** 4  

---

### 8. Patient Login
**Title:**
_As a patient, I want to log in to manage my bookings._

**Acceptance Criteria:**
1. Valid login credentials required
2. Redirect to dashboard
3. Error shown on failure

**Priority:** High  
**Story Points:** 3  

---

### 9. Book Appointment
**Title:**
_As a patient, I want to book an hour-long appointment, so that I can consult a doctor._

**Acceptance Criteria:**
1. Patient selects doctor and time slot
2. Appointment is saved
3. Confirmation is shown

**Priority:** High  
**Story Points:** 5  

---

### 10. View Upcoming Appointments
**Title:**
_As a patient, I want to view my upcoming appointments, so that I can prepare._

**Acceptance Criteria:**
1. Upcoming appointments list is displayed
2. Shows date, time, doctor
3. Data is accurate

**Priority:** Medium  
**Story Points:** 3  

---

## 👨‍⚕️ Doctor User Stories

### 11. Doctor Login
**Title:**
_As a doctor, I want to log into the portal, so that I can manage appointments._

**Acceptance Criteria:**
1. Doctor enters valid credentials
2. Access dashboard
3. Error on invalid login

**Priority:** High  
**Story Points:** 3  

---

### 12. Doctor Logout
**Title:**
_As a doctor, I want to log out, so that my data remains secure._

**Acceptance Criteria:**
1. Logout button available
2. Session ends
3. Redirect to login

**Priority:** High  
**Story Points:** 2  

---

### 13. View Appointment Calendar
**Title:**
_As a doctor, I want to view my appointment calendar, so that I stay organized._

**Acceptance Criteria:**
1. Calendar view is available
2. Shows all appointments
3. Dates are accurate

**Priority:** High  
**Story Points:** 5  

---

### 14. Mark Unavailability
**Title:**
_As a doctor, I want to mark my unavailable time slots, so that patients cannot book those slots._

**Acceptance Criteria:**
1. Doctor selects unavailable slots
2. Slots are blocked
3. Patients cannot book those times

**Priority:** Medium  
**Story Points:** 4  

---

### 15. Update Profile
**Title:**
_As a doctor, I want to update my profile, so that patients see correct information._

**Acceptance Criteria:**
1. Doctor edits profile
2. Changes are saved
3. Updated info is visible

**Priority:** Medium  
**Story Points:** 3  

---

### 16. View Patient Details
**Title:**
_As a doctor, I want to view patient details for appointments, so that I can prepare._

**Acceptance Criteria:**
1. Patient info is accessible
2. Shows history and details
3. Data is secure

**Priority:** High  
**Story Points:** 4  
