class ConfiguracionSelect {
    #defaults = {
        targetUpdate: null,
    };

    constructor(element, options) {
        this.element = element;
        options || (options = {});
        Object.extendComboboxItems(this.#defaults, options);
        this.settings = this.#defaults;
        this.#init();
    }

    #init() {
        this.#createModal();
        this.#setEventsButton();
    }

    refresh() {
        this.#setEventsButton();
    }


    #createModal() {
        let parser = new DOMParser();
        let html =
            '<div id="modal" class="modal fade zoom"  data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="designacionModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog modal-dialog-centered">' +
            '<div class="modal-content">' +
            '<div id="body" class="modal-body"></div>' +
            '<div  class="modal-footer">' +
            '<button type="button" class="waves-effect waves-red btn-flat" data-send="true"  >ACEPTAR</button>' +
            '<button type="button" class="waves-effect waves-red btn-flat" data-bs-dismiss="modal">CANCELAR</button>' +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>";
        let modal = parser.parseFromString(html, "text/html");
        this.Modal = new bootstrap.Modal(modal.getElementById("modal"), {
            backdrop: 'static',
            keyboard: false
        });
        this.Body = modal.getElementById("body");
        this.Send = modal.querySelector('[data-send="true"]');
        this.Cancel = modal.querySelector('[data-bs-dismiss="modal"]');
        this.Send.addEventListener("click", async (event) => {
            let form = this.Body.querySelector('[data-form="true"]');
            this.updateTarget = form.dataset.target;
            await this.#enviarSolicitud(form.id, event.target);
        });
        this.element.insertAdjacentElement(
            "afterend",
            modal.getElementById("modal")
        );
    }
    async #enviarSolicitud(name, button) {
        this.Send.innerHTML = this.Spinner;
        this.Send.setAttribute("disabled", "true");
        this.Cancel.setAttribute("disabled", "true");
        let result = await sendForm(name);
        if (result.includes('data-form')) {
            this.Body.innerHTML = result.toString();
            this.Send.innerHTML = 'ACEPTAR';
            this.Send.removeAttribute("disabled");
            this.Cancel.removeAttribute("disabled");
        } else {
            const target = document.getElementById(this.updateTarget);
            this.Modal.hide();
            this.Body.innerHTML = '';
            this.Send.innerHTML = 'ACEPTAR';
            this.Send.removeAttribute("disabled");
            this.Cancel.removeAttribute("disabled");
            target.innerHTML = result.toString();
            this.refresh();
            alertMessageForm({ok: true, message: "Se realizo la operacion con exito"});
        }
    }


    #setEventsButton() {
        let buttons = document.querySelectorAll('.btn-bs-open');
        buttons.forEach((button) => {
            button.addEventListener('click', async (event) => {
                let input = event.target;
                if (event.target.tagName === "I") input = event.target.parentNode;
                this.buttonTarget = input;
                this.UrlTarget = input.dataset.url;
                this.updateTarget = input.dataset.target;
                this.dataTarget = getDataAttributesComboBox(input);
                this.Modal.show();
            })
        });
    }
}

let toastItems = new ToastModal(window.parent.document.getElementById("toast"), {});
Object.prototype.extendComboboxItems =
    Object.prototype.extendComboboxItems ||
    function (destination, source) {
        for (let property in source) destination[property] = source[property];
        return destination;
    };

function alertMessageItems(data) {
    toastItems.show({
        classNameToast: data.ok ? "success" : "danger",
        textBody: data.message,
        titleText: "Aviso",
        subtitleText: "",
    });
}

function getDataAttributesComboBox(input) {
    let data = '{ "data": "true",'
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

