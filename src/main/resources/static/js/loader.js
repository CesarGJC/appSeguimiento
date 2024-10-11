class Loader {
    #defaults = {
        textAction: '',
        UrlImage: ''
    }
    constructor(element, options) {
        this.element = element;
        options || (options = {});
        Object.extend(this.#defaults, options);
        this.settings = this.#defaults;
        this.init();
    }
    init() {
        let html = '<div class="loader-container">' +
            '<img data-image="true" alt="Cargando" class="loader-image">' +
            '<div class="bars-container">' +
            '<div class="bar"></div> ' +
            '<div class="bar"></div> ' +
            '<div class="bar"></div> ' +
            '<div class="bar"></div>' +
            '<div class="bar"></div> ' +
            '<div class="bar"></div> ' +
            '<div class="bar"></div>  ' +
            '</div>' +
            '<span data-title="true" class="loader-title"></span>' +
            '</div>';
        this.element.className = "preloader-background";
        this.element.innerHTML = html;
        this.element.style.opacity = '0';
        this.element.style.zIndex = "-999";
        this.elements = {
            title: this.element.querySelector('[data-title="true"]'),
            image: this.element.querySelector('[data-image="true"]'),
        }
        this.setImage(this.settings.UrlImage);
        this.setMessage(this.settings.textAction);
    }
    setImage(value) {
        this.elements.image.src = value;
        return this.element;
    }
    setMessage(value) {
        this.elements.title.innerHTML = value;
        return this.element;
    }
    show() {
        this.element.style.opacity = '1';
        this.element.style.zIndex = '999999';
    }
    hide() {
        this.element.style.opacity = '0';
        this.element.style.zIndex = "-999";
    } 
}
Object.prototype.extend =
    Object.prototype.extend ||
    function (destination, source) {
        for (var property in source) destination[property] = source[property];
        return destination;
    };