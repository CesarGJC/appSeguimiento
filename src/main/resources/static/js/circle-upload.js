class Upload {
    #defaults = {
        src: "",
        maxSize: 500000,
        upload: null
    };
    constructor(element, options) {
        this.element = element;
        options || (options = {});
        Object.extend(this.#defaults, options);
        this.settings = this.#defaults;
        this.settings.upload = options.upload || (() => { });
        this.init();
    }
    init() {
        let content =
            '<img data-image="preview" class="imagen" alt="Descripción de la imagen">' +
            '<button  data-button="close" class="boton boton1 hidden"><i class="fa fa-times" aria-hidden="true"></i></button>' +
            '<button  data-button="edit" class="boton boton2"><i class="fa fa-pencil" aria-hidden="true"></i></button>' +
            '<button  data-button="upload" class="boton boton3"><i class="fa fa-upload" aria-hidden="true"></i></button>' +
            '<div data-progress="true" class="progress hidden"><span data-span="true"></span></div>' +
            '<input type="file" data-file="file" style="display: none;">';
        let modal =
            '<div class="modal fade zoom" data-modal="true" data-bs-backdrop="static" tabindex="-1" aria-labelledby="modal" aria-hidden="true">' +
            '<div class="modal-dialog modal-xl modal-dialog-centered">' +
            '<div class="modal-content">' + '<div class="modal-header"> RECORTAR FOTOGRAFIA </div>' +
            '<div class="modal-body">' +
            '<div class="img-container"> <img data-image="true" alt="Responsive image"> </div>' +
            "</div>" +
            '<div class="modal-footer">' +
            '<button type="button" class="waves-effect waves-red btn-flat" data-bs-dismiss="modal">CANCELAR</button>' +
            '<button type="button" class="waves-effect waves-red btn-flat" data-aceptar="true">ACEPTAR</button>' +
            "</div>" +
            "</div>" +
            "</div>" +
            "</div>";
        this.element.className = "circulo";
        this.element.innerHTML = content + modal;
        this.elements = {
            temp: this.settings.src,
            preview: this.element.querySelector('[data-image="preview"]'),
            close: this.element.querySelector('[data-button="close"]'),
            edit: this.element.querySelector('[data-button="edit"]'),
            upload: this.element.querySelector('[data-button="upload"]'),
            file: this.element.querySelector('[data-file="file"]'),
            modal: new bootstrap.Modal(this.element.querySelector('[data-modal="true"]')),
            modalImage: this.element.querySelector('[data-image="true"]'),
            modalAcept: this.element.querySelector('[data-aceptar="true"]'),
            modalBody: this.element.querySelector('.modal-body'),
            progressBar: this.element.querySelector('[data-progress="true"]'),
            cropper: null
        };
        this.elements.file.setAttribute("accept", "image/*");
        this.setImage(this.settings.src);
        this.initCropper(this.elements);
        events(this);
    }
    initCropper(elements) {

        elements.cropper = new Cropper(elements.modalImage, {
            aspectRatio: 16 / 16,
            viewMode: 1,
            autoCropArea: 0.8,
            crop(event) {

            },
        });
        const screenWidth = window.innerWidth;
        if (screenWidth <= 360) {
            elements.cropper.options.minContainerWidth = 310;
            elements.cropper.options.minContainerHeight = 300;
        }
        if (screenWidth > 360 && screenWidth <= 768) {
            elements.cropper.options.minContainerWidth = 400;
            elements.cropper.options.minContainerHeight = 300;
        }
        if (screenWidth > 768 && screenWidth <= 992) {
            elements.cropper.options.minContainerWidth = 460;
            elements.cropper.options.minContainerHeight = 400;
        }
        if (screenWidth > 992 && screenWidth <= 1200) {
            elements.cropper.options.minContainerWidth = 760;
            elements.cropper.options.minContainerHeight = 500;
        }

        if (screenWidth > 1200) {
            elements.cropper.options.minContainerWidth = 1100;
            elements.cropper.options.minContainerHeight = 600;
        }
    }
    setImage(value) {
        this.settings.src = value;
        this.elements.preview.src = value;
        return this.element;
    }
}
function events(upload) {
    upload.elements.preview.addEventListener("click", function () {
        upload.elements.file.click();
    });
    upload.elements.edit.addEventListener("click", function () {
        upload.elements.cropper.reset();
        upload.elements.modalImage.src = upload.elements.preview.src;
        upload.elements.cropper.replace(upload.elements.preview.src);
        upload.elements.modal.show();
    });
    upload.elements.file.addEventListener("change", function () {
        limitarImagen(upload)
            .then(function (result) {
            })
            .catch(function (error) {
                console.error(error);
            });
    });
    upload.elements.close.addEventListener("click", function () {
        upload.elements.close.classList.add("hidden");
        upload.elements.preview.src = upload.elements.temp;
        upload.elements.file.value = "";
    });
    upload.elements.modalAcept.addEventListener("click", function () {
        var croppedImage = upload.elements.cropper.getCroppedCanvas().toDataURL();
        upload.elements.preview.src = croppedImage;
        upload.elements.modal.hide();
    });
    upload.elements.upload.addEventListener('click', function (event) {
        let data = {
            maxSize: upload.settings.maxSize,
            src: upload.elements.preview.src,
            progressBar: upload.elements.progressBar
        }
        upload.settings.upload(data);
    });
}
Object.prototype.extend =
    Object.prototype.extend ||
    function (destination, source) {
        for (var property in source) destination[property] = source[property];
        return destination;
    };
function limitarImagen(upload) {
    // Verificar que el archivo es una imagen
    let inputfile = upload.elements.file.files[0];
    let imgElement = upload.elements.preview;
    if (!inputfile.type.match("image.*")) {
        return Promise.reject("El archivo seleccionado no es una imagen.");
    }

    // Verificar que el tamaño del archivo es menor o igual a 500 KB
    if (inputfile.size > upload.settings.maxSize) {
        return Promise.reject(
            "El tamaño del archivo seleccionado es mayor a " +
            bytesToMB(upload.settings.maxSize) +
            " MB."
        );
    }
    return new Promise(function (resolve, reject) {
        var reader = new FileReader();
        reader.onload = function (event) {
            var img = new Image();
            img.onload = function () {
                // Verificar que el ancho de la imagen es menor o igual a 800 píxeles
                if (img.width > 800) {
                    var canvas = document.createElement("canvas");
                    var ctx = canvas.getContext("2d");
                    canvas.width = 800;
                    canvas.height = img.height * (800 / img.width);
                    ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
                    resolve(canvas.toDataURL("image/jpeg"));
                } else {
                    resolve(img);
                }
            };
            img.src = event.target.result;
        };
        reader.readAsDataURL(inputfile);
    }).then(function (result) {
        if (imgElement) {
            imgElement.src = result.src || result;
            upload.elements.close.classList.remove("hidden");
        }
        return result;
    });
}
function bytesToMB(bytes) {
    return (bytes / (1024 * 1024)).toFixed(2);
}
