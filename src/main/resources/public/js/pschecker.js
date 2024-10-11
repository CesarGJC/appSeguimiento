class psChecker {
    #defaults = {
        minlength: 9,
        maxlength: 64,
        onPasswordValidate: null,
        onPasswordMatch: null
    }
    constructor(password, cPassword, options) {
        this.password = password;
        this.cPassword = cPassword;
        options || (options = {});
        Object.extend(this.#defaults, options);
        this.settings = this.#defaults;
        this.init();
    }
    init() {
        this.cPassword.classList.remove('is-invalid');
        EventsChecker(this);
    }
}
function EventsChecker(form) {
    form.password.addEventListener('keyup', function validate() { validatePassword(form); });
    form.password.addEventListener('blur', function validate() { validatePassword(form); });
    form.password.addEventListener('focus', function validate() { validatePassword(form); });

    form.cPassword.addEventListener('keyup', function validate() { validatePassword(form); });
    form.cPassword.addEventListener('blur', function validate() { validatePassword(form); });
    form.cPassword.addEventListener('focus', function validate() { validatePassword(form); });

}
function validatePassword(form) {
    var pstr = form.password.value.toString();
    var meter = document.querySelector('.meter');
    meter.innerHTML = "";
    //fires password validate event if password meets the min length requirement
    if (form.settings.onPasswordValidate != null) {
        form.settings.onPasswordValidate(pstr.length >= form.settings.minlength);
    }

    if (pstr.length < form.settings.maxlength) {
        meter.classList.remove('strong');
        meter.classList.remove('medium');
        meter.classList.remove('week');
    }

    if (pstr.length > 0) {
        var rx = /^(?=(.*[a-z]){1,})(?=(.*[\d]){1,})(?=(.*[\W]){1,})(?!.*\s).{7,30}$/;
        if (rx.test(pstr)) {
            meter.classList.add('strong');
            meter.innerHTML = "Alto";
        } else {
            var alpha = containsAlpha(pstr);
            var number = containsNumeric(pstr);
            var upper = containsUpperCase(pstr);
            var special = containsSpecialCharacter(pstr);
            var result = alpha + number + upper + special;

            if (result > 2) {
                meter.classList.add('medium');
                meter.innerHTML = "Medio";
            } else {
                meter.classList.add('week');
                meter.innerHTML = "Bajo";
            }
        }

        if (form.cPassword.value.toString().length > 0) {
            if (pstr == form.cPassword.value.toString()) {
                form.cPassword.classList.remove('no-match');
                if (form.settings.onPasswordMatch != null) {
                    form.settings.onPasswordMatch(true);
                }
            } else {
                form.cPassword.classList.add('no-match');
                if (form.settings.onPasswordMatch != null) {
                    form.settings.onPasswordMatch(false);
                }
            }
        } else {
            form.cPassword.classList.add('no-match');
            if (form.settings.onPasswordMatch != null) {
                form.settings.onPasswordMatch(false);
            }
        }
    }
}

function containsAlpha(str) {
    var rx = /[a-z]/;
    if (rx.test(str)) {
        return 1;
    }
    return 0;
}

function containsNumeric(str) {
    var rx = /[0-9]/;
    if (rx.test(str)) {
        return 1;
    }
    return 0;
}

function containsUpperCase(str) {
    var rx = /[A-Z]/;
    if (rx.test(str)) {
        return 1;
    }
    return 0;
}

function containsSpecialCharacter(str) {
    var rx = /[\W]/;
    if (rx.test(str)) {
        return 1;
    }
    return 0;
}

Object.prototype.extend =
    Object.prototype.extend ||
    function (destination, source) {
        for (var property in source) destination[property] = source[property];
        return destination;
    };