import { getDoctors } from "./services/doctorServices.js";
import { createDoctorCard } from "./components/doctorCard.js";

// Run when page loads
document.addEventListener("DOMContentLoaded", async () => {
    loadDoctorCards();
});

// Function to load doctors
async function loadDoctorCards() {
    const doctors = await getDoctors();

    const contentDiv = document.getElementById("content");
    contentDiv.innerHTML = "";

    doctors.forEach(doc => {
        const card = createDoctorCard(doc);
        contentDiv.appendChild(card);
    });
}