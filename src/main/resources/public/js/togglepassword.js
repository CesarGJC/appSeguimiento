const password = document.querySelectorAll('[data-toggle="toggle"]');

for (let i = 0; i < password.length; i++) {
    password[i].addEventListener("click", function () {
        var togglePassword = this.parentNode.parentNode.querySelector("input[type='password']");
        if (togglePassword === null)
            togglePassword = this.parentNode.parentNode.querySelector("input[type='text']");
        if (togglePassword.getAttribute("type") === "password") {
            togglePassword.setAttribute("type", "text");
            this.className = "fa fa-eye";
        } else {
            togglePassword.setAttribute("type", "password");
            this.className = "fa fa-eye-slash";
        }
    });
}