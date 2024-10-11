class ContextMenuToggle {
    #defaults = {
        contextmenuname: "menu",
        eventContext: null,
    };
    constructor(element, options) {
        this.element = element;
        options || (options = {});
        Object.extend(this.#defaults, options);
        this.settings = this.#defaults;
        this.settings.eventContext = options.eventContext || (() => {});
        this.init();
    }
    init() {
        this.elements = {
            text: this.element.dataset.text.toString().split("|"),
            icons: this.element.dataset.icons.toString().split("|"),
            option: this.element.dataset.option.toString().split("|"),
            contextMenu: document.getElementById(this.settings.contextmenuname),
        };

        this.offcanvas = new bootstrap.Offcanvas(this.elements.contextMenu, {
            backdrop: false,
        });
        addEventMenucontext(this);
    }
}
function addEventMenucontext(component) {
    component.element.addEventListener("contextmenu", function (event) {
        event.preventDefault();
        // Evita que aparezca el menú contextual predeterminado del navegador

        // Muestra el menú contextual personalizado en la posición del puntero del mouse
        var contextMenu = component.elements.contextMenu;
        contextMenu.innerHTML = "";
        let div =
            '<div class="offcanvas-header"> <h5 class="offcanvas-title" id="offcanvasBottomLabel">Menu</h5> </div>';
        contextMenu.innerHTML = div;
        let contenedor = document.createElement("div");
        contenedor.className = "offcanvas-body small";
        //crear los sunmenus
        let menu = document.createElement("div");
        menu.className = "list-group";
        for (let i = 0; i < component.elements.text.length; i++) {
            let action = document.createElement("a");
            action.className = "list-group-item list-group-item-action";
            action.innerHTML =
                '<i class="' +
                component.elements.icons[i] +
                '" aria-hidden="true" style="padding-left: 5px; padding-right: 10px;"></i>' +
                component.elements.text[i];
            action.href = "#";
            addEventActionlink(
                action,
                component.elements.option[i],
                component,
                contextMenu
            );
            menu.appendChild(action);
        }
        contenedor.appendChild(menu);
        contextMenu.appendChild(contenedor);
        component.offcanvas.show();
    });
    function addEventActionlink(dom, option, component, contextMenu) {
        dom.addEventListener("click", function (event) {
            component.settings.eventContext(component.element, option);
            component.offcanvas.hide();
        });
    }
    component.elements.contextMenu.addEventListener(
        "hide.bs.offcanvas",
        function () {
            component.elements.contextMenu.innerHTML = "";
        }
    );
    document.addEventListener("click", function (event) {
        // Verifica si se hizo clic fuera del menú contextual
        if (!contextMenu.contains(event.target)) {
            component.offcanvas.hide();
        }
    });
}
Object.prototype.extend =
    Object.prototype.extend ||
    function (destination, source) {
        for (var property in source) destination[property] = source[property];
        return destination;
    };
