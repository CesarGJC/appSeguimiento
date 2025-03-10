class UploadDocument {
    #defaults = {
        loadContent: () => {

        }
    };

    constructor(element, options) {
        if (element === null) {
            throw new Error("El elemento no establecido.");
        }
        this.element = element;
        options || (options = {});
        Object.extendsUpload(this.#defaults, options);
        this.settings = this.#defaults;
        this.#init();
    }

    #init() {
        this.updateTarget = '';
        this.#createModalDropDragArea();
        this.refresh();
    }

    #createModalDropDragArea() {
        let parser = new DOMParser();
        let html =
            '<div id="modal" class="modal fade zoom"  data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="designacionModalLabel" aria-hidden="true">' +
            '<div class="modal-dialog modal-dialog-centered">' +
            '<div class="modal-content">' +
            '<div class="loading-overlay">' +
            '<div class="spinner-border" role="status"></div>' +
            '</div>' +
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
        this.spinner = modal.querySelector("#modal .modal-content");
        this.Body = modal.getElementById("body");
        this.Send = modal.querySelector('[data-send="true"]');
        this.Cancel = modal.querySelector('[data-bs-dismiss="modal"]');
        this.Send.addEventListener("click", async (event) => {
            let form = this.Body.querySelector('[data-form="true"]');
            this.updateTarget = form.dataset.target;
            this.spinner.classList.add("show-loading");
            await this.#enviarSolicitud(form.id, this.spinner);
        });
        this.element.insertAdjacentElement(
            "afterend",
            modal.getElementById("modal")
        );
    }

    async #enviarSolicitud(name, spinner) {
        this.Send.setAttribute("disabled", "true");
        this.Cancel.setAttribute("disabled", "true");
        let result = await sendForm(name);
        if (result.includes('data-form')) {
            this.Body.innerHTML = result.toString();
            this.Send.innerHTML = 'ACEPTAR';
            this.Send.removeAttribute("disabled");
            this.Cancel.removeAttribute("disabled");
            this.#initDragDropInput(this.buttonTarget.dataset.type);
        } else {
            const target = document.getElementById(this.updateTarget);
            this.Modal.hide();
            this.Body.innerHTML = '';
            this.Send.innerHTML = 'ACEPTAR';
            this.Send.removeAttribute("disabled");
            this.Cancel.removeAttribute("disabled");
            target.innerHTML = result.toString();
            this.refresh();
            this.settings.loadContent();
            alertMessageForm({ok: true, message: "Se cargo el documento exito"});
        }
        spinner.classList.remove("show-loading");
    }

    refresh() {
        let buttons = document.querySelectorAll('[data-bs-open="true"]');
        buttons.forEach((button) => {
            button.addEventListener('click', async (event) => {
                if (event.target instanceof HTMLButtonElement) {
                    event.preventDefault();
                }
                let target = event.target.tagName === "I" ? event.target.parentNode : event.target;
                this.buttonTarget = target;
                let data = getDataAttributes(target);
                let url = target.dataset.url;
                let result = await Get(url, data);
                this.Body.innerHTML = result.toString();
                this.Modal.show();
                this.#initDragDropInput(target.dataset.type);
            });
        })
    }

    #initDragDropInput(type) {
        this.dropArea = document.getElementById("dropArea");
        this.fileInput = document.getElementById("file");
        this.fileName = document.getElementById("fileName");
        this.uploadStatus = document.getElementById("uploadStatus");

        this.dropArea.addEventListener("dragover", (e) => {
            e.preventDefault();
            this.dropArea.classList.add("dragover");
        });

        this.dropArea.addEventListener("dragleave", () => {
            this.dropArea.classList.remove("dragover");
        });

        this.dropArea.addEventListener("drop", async (e) => {
            e.preventDefault();
            this.dropArea.classList.remove("dragover");
            const files = e.dataTransfer.files;
            await handleFileUpload(files, this.fileName, this.uploadStatus, type);
        });
        this.fileInput.addEventListener("change", async () => {
            const files = this.fileInput.files;
            await handleFileUpload(files, this.fileName, this.uploadStatus, type);
        });
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
    try {
        return JSON.parse(data);
    } catch {
        return {};
    }
}

async function handleFileUpload(files, fileName, uploadStatus, dataType) {
    if (files.length > 0) {
        const file = files[0];
        fileName.textContent = `Archivo: ${file.name}`;
        uploadStatus.textContent = "Subiendo archivo...";
        if (dataType === 'image' && !file.type.startsWith("image/")) {
            uploadStatus.textContent = "Debe cargar una imagen";
            uploadStatus.style.color = "red";
            return;
        }
        if (dataType === 'documento' && file.type.startsWith("image/")) {
            uploadStatus.textContent = "Debe cargar un documento";
            uploadStatus.style.color = "red";
            return;
        }
        if (file.type.startsWith("image/")) {
            if (file.size > 20 * 1024 * 1024) {
                let selectedFile = await compressImage(file);
                const dataTransfer = new DataTransfer();
                dataTransfer.items.add(selectedFile);
                files.files = dataTransfer.files;

                uploadStatus.textContent = `<br>Archivo comprimido: ${(selectedFile.size / (1024 * 1024)).toFixed(2)} MB`;
                uploadStatus.textContent += " Carga exitosa";
                uploadStatus.style.color = "green";
            }
        }
        uploadStatus.textContent = "Carga exitosa";
        uploadStatus.style.color = "green";
    }
}

Object.prototype.extendsUpload =
    Object.prototype.extendsUpload ||
    function (destination, source) {
        for (let property in source) destination[property] = source[property];
        return destination;
    };

function compressImage(file, quality = 0.6) {
    return new Promise((resolve) => {
        const reader = new FileReader();
        reader.readAsDataURL(file);

        reader.onload = function (event) {
            const img = new Image();
            img.src = event.target.result;

            img.onload = function () {
                const canvas = document.createElement("canvas");
                const ctx = canvas.getContext("2d");

                canvas.width = img.width;
                canvas.height = img.height;
                ctx.drawImage(img, 0, 0, canvas.width, canvas.height);

                canvas.toBlob((blob) => {
                    const compressedFile = new File([blob], file.name, {
                        type: "image/jpeg",
                        lastModified: Date.now()
                    });
                    resolve(compressedFile);
                }, "image/jpeg", quality);
            };
        };
    });
}