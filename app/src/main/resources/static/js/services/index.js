import { API_BASE_URL } from "../config/config.js";

const ADMIN_API = API_BASE_URL + "/admin";
const DOCTOR_API = API_BASE_URL + "/doctor/login";

window.onload = function () {

    const adminBtn = document.getElementById("adminLogin");
    const doctorBtn = document.getElementById("doctorLogin");
    const patientBtn = document.getElementById("patientLogin");

    if (adminBtn) {
        adminBtn.addEventListener("click", adminLoginHandler);
    }

    if (doctorBtn) {
        doctorBtn.addEventListener("click", doctorLoginHandler);
    }
    

    if (patientBtn) {
    patientBtn.addEventListener("click", () => {
        patientLoginHandler();
    });
}
};

// ================= ADMIN LOGIN =================
async function adminLoginHandler() {
    const username = prompt("Enter username");
    const password = prompt("Enter password");

    const admin = { username, password };

    try {
        const response = await fetch(ADMIN_API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(admin)
        });

        if (response.ok) {
            const data = await response.json();

            localStorage.setItem("token", data.token);

            // ✅ CORRECT REDIRECT
            window.location.href = `/adminDashboard/${data.token}`;

        } else {
            alert("Invalid credentials!");
        }

    } catch (error) {
        alert("Error: " + error);
    }
}

// ================= DOCTOR LOGIN =================
async function doctorLoginHandler() {
    const email = prompt("Enter email");
    const password = prompt("Enter password");

    const doctor = { identifier: email, password };

    try {
        const response = await fetch(DOCTOR_API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(doctor)
        });

        if (response.ok) {
            const data = await response.json();

            localStorage.setItem("token", data.token);

            // ✅ CORRECT REDIRECT
            window.location.href = `/doctorDashboard/${data.token}`;

        } else {
            alert("Invalid credentials!");
        }

    } catch (error) {
        alert("Error: " + error);
    }
}

function patientLoginHandler() {
    const email = prompt("Enter email");
    const password = prompt("Enter password");

    // dummy token for now
    const token = "patient-token";

    localStorage.setItem("token", token);

    // redirect to dummy page
    window.location.href = "/pages/patientDashboard.html";
}