(function () {
    const originalAddEventListener = Element.prototype.addEventListener;

    Element.prototype.addEventListener = function (eventType, listener, options) {
        if (!this._eventListeners) {
            this._eventListeners = {};
        }

        if (!this._eventListeners[eventType]) {
            this._eventListeners[eventType] = [];
        }

        // Guardamos el listener en la lista de eventos
        this._eventListeners[eventType].push(listener);

        // Llamamos al método original de addEventListener
        originalAddEventListener.call(this, eventType, listener, options);
    };

    // Método para verificar si un evento específico está asignado
    Element.prototype.hasEventListener = function (eventType) {
        return this._eventListeners && this._eventListeners[eventType] && this._eventListeners[eventType].length > 0;
    };
})();

function hasClickEventAssignedClick(button) {
    return button.hasEventListener && button.hasEventListener('click');
}

function isNullOrEmpty(value) {
    return value === null || value === undefined || value === '';
}