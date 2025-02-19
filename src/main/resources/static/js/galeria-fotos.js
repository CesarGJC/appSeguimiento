class GaleriaFotos {

    constructor(element) {
        if (element === null) {
            throw new Error("El elemento no establecido.");
        }
        this.element = element;
        this.#init();
        this.#setEvents();
    }

    #init() {
        let parser = new DOMParser();
        let html = '<div class="modal-gallery" id="imageModal">' +
            '<div class="modal-gallery-content">' +
            '<button class="modal-gallery-close" data-close="true">X</button>' +
            '<img data-image="true" src="" alt="Imagen ampliada">' +
            '<p data-description="true" id="modalDescription"></p>' +
            '</div>' +
            '</div>';
        let modal = parser.parseFromString(html, "text/html");
        this.btnClose = modal.querySelector('[data-close="true"]');
        this.imageData = modal.querySelector('[data-image="true"]');
        this.descriptionData = modal.querySelector('[data-description="true"]');
        this.btnClose.addEventListener('click', (event) => {
            document.getElementById("imageModal").classList.remove("active");
        })
        this.element.insertAdjacentElement(
            "afterend",
            modal.getElementById("imageModal")
        );
    }

    closeModal() {
        document.getElementById("imageModal").classList.remove("active");
    }

    #setEvents() {
        let buttons = document.querySelectorAll('[data-preview="true"]');
        buttons.forEach((button) => {
            button.addEventListener('click', async (event) => {
                let target = event.target.tagName === "P" ? event.target.parentNode : event.target;
                let id = target.dataset.id;
                let description = target.dataset.description;
                this.imageData.src = document.getElementById(id).src;
                this.descriptionData.textContent = description;
                document.getElementById("imageModal").classList.add("active");
            });
        });

        // Cierra el modal si se hace clic fuera de la imagen
        document.getElementById("imageModal").addEventListener("click", function (event) {
            if (event.target === this) {
                document.getElementById("imageModal").classList.remove("active");
            }
        });
    }
}