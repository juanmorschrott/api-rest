document.addEventListener("DOMContentLoaded", function(event) {
    var usernameField = document.getElementById("username");
    var passwordField = document.getElementById("password");
    if (usernameField && !usernameField.value) {
        usernameField.value = "admin";
    }
    if (passwordField && !passwordField.value) {
        passwordField.value = "admin";
    }
});
