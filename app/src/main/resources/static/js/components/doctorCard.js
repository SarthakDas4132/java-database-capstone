export function createDoctorCard(doctor) {
    const card = document.createElement("div");
    card.style.border = "1px solid black";
    card.style.padding = "10px";
    card.style.margin = "10px";

    const name = document.createElement("h3");
    name.textContent = doctor.name;

    const specialty = document.createElement("p");
    specialty.textContent = "Specialty: " + doctor.specialty;

    const email = document.createElement("p");
    email.textContent = "Email: " + doctor.email;

    const actions = document.createElement("div");

    const role = localStorage.getItem("userRole");

    if (role === "admin") {
        const deleteBtn = document.createElement("button");
        deleteBtn.textContent = "Delete";
        deleteBtn.onclick = () => alert("Delete doctor");
        actions.appendChild(deleteBtn);
    } 
    else {
        const bookBtn = document.createElement("button");
        bookBtn.textContent = "Book";
        bookBtn.onclick = () => alert("Book appointment");
        actions.appendChild(bookBtn);
    }

    card.appendChild(name);
    card.appendChild(specialty);
    card.appendChild(email);
    card.appendChild(actions);

    return card;
}