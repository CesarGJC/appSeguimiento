class AjaxForm {
    constructor(element) {
        if (element === null) {
            throw new Error("El elemento no establecido.");
        }
        this.element = element;
        this.#init();
    }

    #init() {
        this.updateTarget = '';
        this.#createModal();
        this.Spinner = '<span class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>\n' +
            'ENVIANDO...';
        this.refresh();
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

    refresh() {
        let buttons = document.querySelectorAll('[data-bs-open="true"]');
        buttons.forEach((button) => {
            button.addEventListener('click', async (event) => {
                if (event.target instanceof HTMLButtonElement) {
                    event.preventDefault();
                }
                let target = event.target.tagName === "I" ? event.target.parentNode : event.target;
                let data = getDataAttributes(target);
                let url = target.dataset.url;
                let result = await Get(url, data);
                this.Body.innerHTML = result.toString();
                this.Modal.show();
            });
        })
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
}

let toastForm = new ToastModal(window.parent.document.getElementById("toast"), {});

function alertMessageForm(data) {
    toastForm.show({
        classNameToast: data.ok ? "success" : "danger",
        textBody: data.message,
        titleText: "Aviso",
        subtitleText: "",
    });
}

function getDataAttributes(input) {
    let data = '{'
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