import { API_BASE_URL } from "../config/config.js";

const PATIENT_API = API_BASE_URL + "/patient";

// ================= SIGNUP =================
export async function patientSignup(data) {
    try {
        const response = await fetch(`${PATIENT_API}/signup`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        const res = await response.json();

        return {
            success: response.ok,
            message: res.message || "Signup successful"
        };

    } catch (error) {
        console.error(error);
        return { success: false, message: "Signup failed" };
    }
}

// ================= LOGIN =================
export async function patientLogin(data) {
    try {
        const response = await fetch(`${PATIENT_API}/login`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(data)
        });

        return response;

    } catch (error) {
        console.error(error);
    }
}

// ================= GET PATIENT DATA =================
export async function getPatientData(token) {
    try {
        const response = await fetch(`${PATIENT_API}/profile`, {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        if (response.ok) {
            return await response.json();
        }

        return null;

    } catch (error) {
        console.error(error);
        return null;
    }
}

// ================= GET APPOINTMENTS =================
export async function getPatientAppointments(id, token, user) {
    try {
        const url = `${PATIENT_API}/appointments/${user}/${id}`;

        const response = await fetch(url, {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        return await response.json();

    } catch (error) {
        console.error(error);
        return null;
    }
}

// ================= FILTER APPOINTMENTS =================
export async function filterAppointments(condition, name, token) {
    try {
        const url = `${PATIENT_API}/appointments/filter?status=${condition}&name=${name}`;

        const response = await fetch(url, {
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        return await response.json();

    } catch (error) {
        console.error(error);
        return [];
    }
}