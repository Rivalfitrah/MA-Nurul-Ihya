document.addEventListener("DOMContentLoaded", () => {
    const loginForm = document.getElementById("loginForm");
    const errorMessage = document.getElementById("error-message");

    // Data username dan password
    const validCredentials = {
        username: "admin",
        password: "admin123"
    };

    loginForm.addEventListener("submit", function(event) {
        event.preventDefault();
        const username = document.getElementById("username").value;
        const password = document.getElementById("password").value;

        if (username === "" || password === "") {
            errorMessage.textContent = "Username and password cannot be empty.";
            errorMessage.style.display = "block";
        } else if (username !== validCredentials.username || password !== validCredentials.password) {
            errorMessage.textContent = "Incorrect username or password.";
            errorMessage.style.display = "block";
        } else {
            errorMessage.style.display = "none";
            window.location.href = "/dashboard"; // Redirect to dashboard.html on successful login
        }
    });
});

