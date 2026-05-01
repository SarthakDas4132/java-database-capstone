export const API_BASE_URL = "http://localhost:8080";
import { API_BASE_URL } from "../config/config.js";

// APIs
const ADMIN_API = API_BASE_URL + "/admin";
const DOCTOR_API = API_BASE_URL + "/doctor/login";

// Runs after page loads
window.onload = function () {
    const adminBtn = document.getElementById("adminLogin");
    const doctorBtn = document.getElementById("doctorLogin");

    if (adminBtn) {
        adminBtn.addEventListener("click", () => {
            adminLoginHandler();
        });
    }

    if (doctorBtn) {
        doctorBtn.addEventListener("click", () => {
            doctorLoginHandler();
        });
    }
};

// Save role
function selectRole(role) {
    localStorage.setItem("userRole", role);

    if (role === "admin") {
        window.location.href = "/admin/adminDashboard";
    } else if (role === "doctor") {
        window.location.href = "/doctor/doctorDashboard";
    }
}

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

            localStorage.setItem("token", data.token || "dummy-token");
            selectRole("admin");
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

    const doctor = { email, password };

    try {
        const response = await fetch(DOCTOR_API, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(doctor)
        });

        if (response.ok) {
            const data = await response.json();

            localStorage.setItem("token", data.token || "dummy-token");
            selectRole("doctor");
        } else {
            alert("Invalid credentials!");
        }
    } catch (error) {
        alert("Error: " + error);
    }
}