class SubmitFormLoader {
    #defaults = {
        contextPath: null
    }

    constructor(element, options) {
        this.element = element;
        options || (options = {});
        Object.extendForm(this.#defaults, options);
        this.settings = this.#defaults;
        this.#init();
    }

    #init() {
        let loader = document.createElement("div");
        loader.setAttribute("id", "loader");
        this.element.insertAdjacentElement("afterend", loader);
        this.loaderContent = new Loader(loader, {
            textAction: 'Enviando...',
            UrlImage: this.settings.contextPath + '/static/image/logominiatura.png'
        });
        this.loadEvents();
    }

    loadEvents() {
        this.#eventLinks(this.loaderContent);
        this.#eventButtons();
    }

    #eventButtons() {
        let buttons = document.querySelectorAll('.btn-enviar-solicitud');
        buttons.forEach((button) => {
            if (!hasClickEventAssignedClick(button)) {
                button.addEventListener('click', (event) => {
                    let input;
                    if (event.target.tagName.toUpperCase() === "I") input = event.target.parentNode;
                    else input = event.target;
                    if (input instanceof HTMLAnchorElement)
                        event.preventDefault();
                    this.loaderContent.show();
                    document.getElementById(input.dataset.name).submit();
                })
            }
        })
    }

    #eventLinks(loader) {
        let enlaces = document.querySelectorAll('a.enlace');
        enlaces.forEach(function (enlace) {
            if (!hasClickEventAssignedClick(enlaces)) {
                enlace.addEventListener('click', (event) => {
                    let input;
                    if (event.target.tagName.toUpperCase() === "I") input = event.target.parentNode;
                    else input = event.target;
                    if (input instanceof HTMLAnchorElement)
                        event.preventDefault();
                    loader.show();
                    let href = input.getAttribute("href");
                    window.location.href = href;
                })
            }
        });
    }

    enviarSolicitudForm(form) {
        this.loaderContent.show();
        form.submit();
    }
}

Object.prototype.extendForm =
    Object.prototype.extendForm ||
    function (destination, source) {
        for (let property in source) destination[property] = source[property];
        return destination;
    };
let submitFormSend = new SubmitFormLoader(document.getElementById('main'), {contextPath: document.getElementById('main').dataset.context});
