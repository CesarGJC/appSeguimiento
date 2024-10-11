class Form {
    #defaults = {
        target: null,
        text: '',
        url: ''
    }
    constructor(element, options) {
        this.element = element;
        options || (options = {});
        Object.extend(this.#defaults, options);
        this.settings = this.#defaults;
        this.init();
    }
    init() {
        let loader = new Loader(this.settings.target, { textAction: this.settings.text, UrlImage: this.settings.url });
        this.loader = loader;
        Events(this);
    }
}
function Events(form) {
    form.element.addEventListener("submit", (event) => {
        form.loader.show();
    });
}
Object.prototype.extend =
    Object.prototype.extend ||
    function (destination, source) {
        for (var property in source) destination[property] = source[property];
        return destination;
    };