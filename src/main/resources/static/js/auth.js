function togglePassword(inputId) {
    const input = document.getElementById(inputId);

    if (input.type === "password") {
        input.type = "text";
    } else {
        input.type = "password";
    }
}

function validateLoginForm() {
    let valid = true;

    const email = document.getElementById("email").value.trim();
    const password = document.getElementById("password").value.trim();

    document.getElementById("emailError").textContent = "";
    document.getElementById("passwordError").textContent = "";

    if (email === "") {
        document.getElementById("emailError").textContent = "Email is required.";
        valid = false;
    }

    if (password === "") {
        document.getElementById("passwordError").textContent = "Password is required.";
        valid = false;
    }

    return valid;
}

function validateSignupForm() {
    let valid = true;

    const name = document.getElementById("name").value.trim();
    const username = document.getElementById("signupEmail").value.trim();
    const password = document.getElementById("signupPassword").value.trim();
    const confirmPassword = document.getElementById("confirmPassword").value.trim();

    document.getElementById("nameError").textContent = "";
    document.getElementById("signupEmailError").textContent = "";
    document.getElementById("signupPasswordError").textContent = "";
    document.getElementById("confirmPasswordError").textContent = "";

    if (name === "") {
        document.getElementById("nameError").textContent = "Name is required.";
        valid = false;
    }

    if (username === "") {
        document.getElementById("signupEmailError").textContent = "Email is required.";
        valid = false;
    }

    if (password === "") {
        document.getElementById("signupPasswordError").textContent = "Password is required.";
        valid = false;
    } else if (password.length < 6) {
        document.getElementById("signupPasswordError").textContent = "Password must be at least 6 characters.";
        valid = false;
    }

    if (confirmPassword === "") {
        document.getElementById("confirmPasswordError").textContent = "Please confirm your password.";
        valid = false;
    } else if (password !== confirmPassword) {
        document.getElementById("confirmPasswordError").textContent = "Passwords do not match.";
        valid = false;
    }

    return valid;
}