class ContextMenu {
    #defaults = {
        contextmenuname: "menu",
        eventContext: null,
    };

    constructor(element, options) {
        this.element = element;
        options || (options = {});
        Object.extend(this.#defaults, options);
        this.settings = this.#defaults;
        this.settings.eventContext = options.eventContext || (() => {
        });
        this.init();
    }

    init() {
        this.elements = {
            text: this.element.dataset.text.toString().split("|"),
            icons: this.element.dataset.icons.toString().split("|"),
            option: this.element.dataset.option.toString().split("|"),
        };
        addEventMenucontext(this);
    }
}

function addEventMenucontext(component) {
    component.element.addEventListener("contextmenu", function (event) {
        event.preventDefault();
        // Evita que aparezca el menú contextual predeterminado del navegador
        let buttonRect = event.target.getBoundingClientRect();
        let buttonLeft = buttonRect.left;
        let buttonTop = buttonRect.top;

        // Muestra el menú contextual personalizado en la posición del puntero del mouse
        let contextMenu = document.getElementById(
            component.settings.contextmenuname
        );
        contextMenu.style.display = "block";
        contextMenu.style.left = buttonLeft + "px";
        contextMenu.style.top = (buttonTop + 150) + "px";
        console.log(buttonTop + 150);
        contextMenu.innerHTML = "";
        //crear los sunmenus
        let menu = document.createElement("ul");
        for (let i = 0; i < component.elements.text.length; i++) {
            let lista = document.createElement("li");
            let action = document.createElement("a");
            action.innerHTML =
                '<i class="' +
                component.elements.icons[i] +
                '" aria-hidden="true"></i>' +
                component.elements.text[i];
            action.href = "#";
            addEventActionlink(
                action,
                component.elements.option[i],
                component,
                contextMenu
            );
            lista.appendChild(action);
            menu.appendChild(lista);
        }
        contextMenu.appendChild(menu);
    });

    function addEventActionlink(dom, option, component, contextMenu) {
        dom.addEventListener("click", function (event) {
            component.settings.eventContext(component.element, option);
            contextMenu.style.display = "none";
        });
    }

    // Agrega un evento de clic para ocultar el menú contextual cuando se hace clic en otro lugar de la página
    document.addEventListener("click", function (event) {
        let contextMenu = document.getElementById(component.settings.contextmenuname);
        // Verifica si se hizo clic fuera del menú contextual
        if (!contextMenu.contains(event.target)) {
            contextMenu.style.display = "none";
        }
    });
}

Object.prototype.extend =
    Object.prototype.extend ||
    function (destination, source) {
        for (let property in source) destination[property] = source[property];
        return destination;
    };
