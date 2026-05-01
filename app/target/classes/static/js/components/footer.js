function renderFooter() {
    const footer = document.getElementById("footer");
    if (!footer) return;

    footer.innerHTML = `
        <footer style="text-align:center; padding:10px;">
            <p>© Clinic Management System</p>
        </footer>
    `;
}

renderFooter();