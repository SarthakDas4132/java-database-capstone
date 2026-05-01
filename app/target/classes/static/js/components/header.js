function renderHeader() {
    const headerDiv = document.getElementById("header");
    if (!headerDiv) return;

    let headerContent = "";
    const role = localStorage.getItem("userRole");
    const token = localStorage.getItem("token");

    // Homepage reset
    if (window.location.pathname.endsWith("/")) {
        localStorage.removeItem("userRole");
        localStorage.removeItem("token");
    }

    // Invalid session check
    if ((role === "admin" || role === "doctor" || role === "loggedPatient") && !token) {
        localStorage.removeItem("userRole");
        alert("Session expired. Please login again.");
        window.location.href = "/";
        return;
    }

    // Role-based UI
    if (role === "admin") {
        headerContent = `
            <h3>Admin Panel</h3>
            <button onclick="alert('Add Doctor Clicked')">Add Doctor</button>
            <button onclick="logout()">Logout</button>
        `;
    } 
    else if (role === "doctor") {
        headerContent = `
            <h3>Doctor Dashboard</h3>
            <button onclick="logout()">Logout</button>
        `;
    } 
    else if (role === "patient") {
        headerContent = `
            <button onclick="alert('Login')">Login</button>
            <button onclick="alert('Signup')">Sign Up</button>
        `;
    } 
    else if (role === "loggedPatient") {
        headerContent = `
            <h3>Patient Dashboard</h3>
            <button onclick="logout()">Logout</button>
        `;
    }

    headerDiv.innerHTML = headerContent;
}

// Logout function
function logout() {
    localStorage.removeItem("token");
    localStorage.removeItem("userRole");
    window.location.href = "/";
}

// Run automatically
renderHeader();