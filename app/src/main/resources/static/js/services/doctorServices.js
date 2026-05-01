import { API_BASE_URL } from "../config/config.js";

const DOCTOR_API = API_BASE_URL + "/doctor";

// ================= GET ALL DOCTORS =================
export async function getDoctors() {
    try {
        const response = await fetch(DOCTOR_API);
        const data = await response.json();
        return data || [];
    } catch (error) {
        console.error("Error fetching doctors:", error);
        return [];
    }
}

// ================= DELETE DOCTOR =================
export async function deleteDoctor(id, token) {
    try {
        const response = await fetch(`${DOCTOR_API}/${id}`, {
            method: "DELETE",
            headers: {
                "Authorization": `Bearer ${token}`
            }
        });

        const data = await response.json();

        return {
            success: response.ok,
            message: data.message || "Deleted"
        };

    } catch (error) {
        console.error(error);
        return { success: false, message: "Error deleting doctor" };
    }
}

// ================= SAVE DOCTOR =================
export async function saveDoctor(doctor, token) {
    try {
        const response = await fetch(DOCTOR_API, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: JSON.stringify(doctor)
        });

        const data = await response.json();

        return {
            success: response.ok,
            message: data.message || "Doctor added"
        };

    } catch (error) {
        console.error(error);
        return { success: false, message: "Error saving doctor" };
    }
}

// ================= FILTER DOCTORS =================
export async function filterDoctors(name, time, specialty) {
    try {
        let url = `${DOCTOR_API}/filter?name=${name || ""}&time=${time || ""}&specialty=${specialty || ""}`;

        const response = await fetch(url);
        const data = await response.json();

        return data || [];

    } catch (error) {
        console.error(error);
        return [];
    }
}