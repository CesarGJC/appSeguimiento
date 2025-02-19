class ComboBoxBoostrap {
    #defaults = {
        targetUpdate: null,
    };

    constructor(element, options) {
        this.elements = [];
        let comboBox = document.querySelectorAll(element);
        comboBox.forEach((item) => {
            this.elements.push(item);
        });
        options || (options = {});
        Object.extendCombobox(this.#defaults, options);
        this.settings = this.#defaults;
        this.#init();
    }

    #init() {
        this.#setEvents();
    }

    #setEvents() {
        this.elements.forEach((item) => {
            if (!hasChangeEventAssigned(item)) {
                item.addEventListener("change", async (event) => {
                    let url = event.target.dataset.url;
                    let data = getDataAttributesSelectComboBox(event.target);
                    data.id = event.target.value;
                    let id = event.target.dataset.target;
                    document.getElementById(id).innerHTML = await Get(url, data);
                });
            }
        });
    }
}

Object.prototype.extendCombobox =
    Object.prototype.extendCombobox ||
    function (destination, source) {
        for (var property in source) destination[property] = source[property];
        return destination;
    };

(function () {
    const originalAddEventListenerCombo = Element.prototype.addEventListenerCombo;

    Element.prototype.addEventListenerCombo = function (eventType, listener, options) {
        if (!this._eventListeners) {
            this._eventListeners = {};
        }

        if (!this._eventListeners[eventType]) {
            this._eventListeners[eventType] = [];
        }

        // Guardamos el listener en la lista de eventos
        this._eventListeners[eventType].push(listener);

        // Llamamos al método original de addEventListener
        originalAddEventListenerCombo.call(this, eventType, listener, options);
    };

    // Método para verificar si un evento específico está asignado
    Element.prototype.hasEventListenerCombo = function (eventType) {
        return this._eventListeners && this._eventListeners[eventType] && this._eventListeners[eventType].length > 0;
    };
})();

function hasChangeEventAssigned(button) {
    return button.hasEventListenerCombo && button.hasEventListenerCombo('change');
}

function getDataAttributesSelectComboBox(input) {
    let data = '{ "display": "item",'
    Object.entries(input.dataset).forEach(([clave, valor]) => {
        if (clave.includes('sistema')) {
            let name = clave.replace("sistema", "").toLowerCase();
            if (!isNaN(Number(valor))) {
                data += '"' + name + '": ' + valor + ',';
            } else if (valor.toLowerCase() === "true" || valor.toLowerCase() === "false") {
                data += '"' + name + '": ' + valor + ',';
            } else {
                data += '"' + name + '": "' + valor + '",';
            }
        }
    });
    data = data.slice(0, -1)
    data += '}';
    return JSON.parse(data);
}