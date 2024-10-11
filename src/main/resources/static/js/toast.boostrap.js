class ToastModal {
    #defaults = {
        classNameToast: "primary",
        textBody: "",
        titleText: "",
        subtitleText: "hola",
    };
    constructor(element) {
        this.element = element;
        this.settings = this.#defaults;
        this.init();
    }
    show(options) {
        options || (options = {});
        Object.extend(this.#defaults, options);
        this.settings = this.#defaults;
        this.reset();
        var toast = new bootstrap.Toast(
            this.element.querySelector('[name="toast"]')
        );
        toast.show();
    }
    reset() {
        this.setClassName(this.settings.classNameToast);
        this.setTextBody(this.settings.textBody);
        this.setSubTitleText(this.settings.subtitleText);
        this.setTitleText(this.settings.titleText);
    }
    init() {
        let html =
            '<div name="toast" class="toast fade hide" role="alert" aria-live="assertive" aria-atomic="true">' +
            '<div class="toast-header text-white opacity-75">' +
            '<span class="rounded me-2"><i class="fa fa-circle" aria-hidden="true"></i></span>' +
            ' <strong class="me-auto"> </strong>' +
            ' <small data-subtitle=""> </small>' +
            '<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>' +
            '</div> <div class="toast-body"></div>  </div>';
        this.element.className = "position-fixed top-0 end-0";
        this.element.style.zIndex = "9999";
        this.element.innerHTML = html;
        this.elements = {
            classTitle: this.element.querySelector(".toast-header"),
            titleText: this.element.querySelector(".toast-header .me-auto"),
            subtitleText: this.element.querySelector('[data-subtitle=""]'),
            textBody: this.element.querySelector(".toast-body"),
        };
        this.setClassName(this.settings.classNameToast);
        this.setTextBody(this.settings.textBody);
        this.setSubTitleText(this.settings.subtitleText);
        this.setTitleText(this.settings.titleText);
    }
    setTextBody(value) {
        this.settings.textBody = value;
        this.elements.textBody.innerHTML = value;
        return this.element;
    }
    setSubTitleText(value) {
        this.settings.subtitleText = value;
        this.elements.subtitleText.innerHTML = value;
        return this.element;
    }
    setTitleText(value) {
        this.settings.titleText = value;
        this.elements.titleText.innerHTML = value;
        return this.element;
    }
    setClassName(value) {
        this.settings.classNameToast = value;
        this.elements.classTitle.className =
            "toast-header bg-" + value + " text-white opacity-75";
        return this.element;
    }
}
Object.prototype.extend =
    Object.prototype.extend ||
    function (destination, source) {
        for (var property in source) destination[property] = source[property];
        return destination;
    };
