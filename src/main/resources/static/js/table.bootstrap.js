class TableBoostrap {
    #defaults = {
        title: "Tabla",
        buttons: [],
        params: {},
        updateTarget: null,
        dataUpdate: {},
        loadData: (...args) => {
        },
        add: null,
        loadContent:()=>{

        }
    };

    constructor(element, options) {
        if (!(element instanceof HTMLDivElement)) {
            throw new Error("El elemento proporcionado no es contenedor.");
        }
        this.element = element;
        options || (options = {});
        Object.extends(this.#defaults, options);
        this.settings = this.#defaults;
        this.#init();
    }

    #init() {
        this.#createModal();
        this.#setToolbar();
        this.Loading =
            '<div class="overlay"><div class="m-loader mr-4"> <svg class="m-circular" viewBox="25 25 50 50"><circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"></circle></svg></div><h3 class="l-text">Loading</h3></div>';
        this.Spinner = '<span class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>\n' +
            'ENVIANDO...';
        if (this.#getVisibleValue("toolbar")) this.#setButtons(this.Toolbar);
        this.refreshTable();
        this.loadButtonToUpdate();
        this.loadButtonToDelete();
    }

    refreshTable() {
        this.Table = this.#getTable();
        let divTitle = document.createElement("div");
        divTitle.className = "table-title";
        divTitle.innerHTML =
            '<div class="table-title-text">' + this.settings.title + "</div>";
        this.Table.parentNode.insertBefore(divTitle, this.Table);
        this.#getTemplateFilter();
        this.#setColumnResize();
        this.#initResizble();
    }

    #getTable() {
        let table = this.element.querySelectorAll('[data-table="true"]');
        if (table == null)
            throw new Error("El elemento proporcionado no contine una tabla.");
        if (table.length === 0)
            throw new Error("El elemento proporcionado no contine una tabla.");
        if (table.length > 1)
            throw new Error(
                "El elemento tiene definido mas de una tabla en el contenedor."
            );
        return table[0];
    }

    #setToolbar() {
        let divToolbar = document.createElement("div");
        divToolbar.className = "btn-toolbar justify-content-between";
        divToolbar.setAttribute("role", "toolbar");
        divToolbar.setAttribute("aria-label", "Toolbar with button groups");

        if (this.#getVisibleValue("toolbar")) {
            this.Toolbar = document.createElement("div");
            this.Toolbar.className = "btn-group";
            this.Toolbar.setAttribute("role", "group");
            this.Toolbar.setAttribute("aria-label", "First group");
            divToolbar.appendChild(this.Toolbar);
        }
        if (this.#getVisibleValue("search")) {
            let divSearch = document.createElement("div");
            divSearch.className = "input-group";

            this.TextSearch = document.createElement("input");
            this.TextSearch.className = "form-control";
            this.TextSearch.setAttribute("placeholder", "Buscar elemento");
            this.TextSearch.setAttribute("type", "text");
            this.TextSearch.setAttribute("aria-describedby", "btnGroupAddon2");

            let divGrupo = document.createElement("button");
            divGrupo.className =
                "btn btn-primary border-start-0 border-bottom-0 border ms-n5";
            divGrupo.setAttribute("id", "btnGroupAddon2");
            divGrupo.innerHTML = '<i class="fa fa-search" aria-hidden="true"></i>';

            divSearch.appendChild(this.TextSearch);
            divSearch.appendChild(divGrupo);

            divToolbar.appendChild(divSearch);
        }
        this.element.parentNode.insertBefore(divToolbar, this.element);
    }

    #setColumnResize() {
        let columns = this.element.querySelectorAll('[data-resize="true"]');
        columns.forEach((value) => {
            value.className = "resizable";
        });
    }

    #initResizble() {
        let thElements = this.Table.querySelectorAll("th.resizable");
        thElements.forEach((th) => {
            th.addEventListener("mousedown", function (e) {
                let startX = e.pageX;
                let startWidth = th.offsetWidth;

                function mouseMoveHandler(e) {
                    let newWidth = startWidth + (e.pageX - startX);
                    th.style.width = newWidth + "px";
                }

                function mouseUpHandler() {
                    document.removeEventListener("mousemove", mouseMoveHandler);
                    document.removeEventListener("mouseup", mouseUpHandler);
                }

                document.addEventListener("mousemove", mouseMoveHandler);
                document.addEventListener("mouseup", mouseUpHandler);
            });
        });
    }

    #getVisibleValue(attribute) {
        let value = this.element.getAttribute("data-" + attribute);
        if (value === null) return false;
        else return value === "true";
    }

    #setButtons(toolbar) {
        if (this.settings.add !== null) {
            if (this.#getVisibleValue("add")) {
                this.settings.add.className = "btn btn-primary";
                this.settings.add.innerHTML = '<span class="fa fa-plus" aria-hidden="true"></span> Nuevo';
                this.settings.add.addEventListener("click", (event) => {
                    if (event.target.tagName === "A") event.preventDefault();
                    if (event.target instanceof HTMLSpanElement)
                        this.newButtonAddElement(event.target.parentNode);
                    else
                        this.newButtonAddElement(event.target);


                });
                toolbar.appendChild(this.settings.add);
            } else
                this.settings.style.display = 'none';
        }

        if (Array.isArray(this.settings.buttons)) {
            if (this.settings.buttons.length !== 0) {
                this.settings.buttons.forEach((value) => {
                    let button = document.createElement("button");
                    button.className = "btn " + value.className;
                    button.innerHTML = value.icon + " " + value.text;
                    if (value.data !== null || value.data.length !== 0)
                        value.data.forEach((parametros) => {
                            Object.keys(parametros).forEach(function (propiedad) {
                                button.setAttribute(`data-${propiedad}`, parametros[propiedad]);
                            });
                        });
                    button.setAttribute("id", value.id);
                    if (value.click !== null)
                        button.onclick = () => {
                            value.click(button);
                        };
                    toolbar.appendChild(button);
                });
            }
        } else throw new Error("No es un array");
    }

    #getTemplateFilter() {
        let columns = this.Table.querySelectorAll("thead tr th");

        columns.forEach((value) => {
            let sortable = TableBoostrap.#getDataTh(value, "sortable"),
                filter = TableBoostrap.#getDataTh(value, "filter");
            if (this.#verificaIndice(value)) {
                let textContent = value.textContent;
                let divContent = document.createElement("div");
                if (sortTable)
                    divContent.className =
                        "column-header-container column-header-sortable";
                else divContent.className = "column-header-container";

                divContent.setAttribute("data-sort", true);
                divContent.innerHTML = `<span class="column-header-text">${textContent}</span>`;
                let type = TableBoostrap.#getDataTypeTh(value, "type");

                if (filter && type !== null) {
                    divContent.appendChild(
                        this.#getDOMFilter(
                            type,
                            Number(value.getAttribute("data-row-sort"))
                        )
                    );
                }

                value.innerHTML = "";
                value.appendChild(divContent);
                if (!filter) {
                    value.addEventListener("click", (event) => {
                        let numero = Number(event.target.getAttribute("data-row-sort"));
                        sortTable(this.element, numero, value);
                    });
                }
            }
        });
    }

    static #getDataTypeTh(th, attribute) {
        let value = th.getAttribute("data-" + attribute);
        if (value === null || value === "") return "text";
        return value.toLowerCase() === "number".toLowerCase()
            ? "number"
            : value.toLowerCase() === "date".toLowerCase()
                ? "date"
                : "text";
    }

    static #getDataTh(th, attribute) {
        let value = th.getAttribute("data-" + attribute);
        if (value === null) return false;
        else return value.toLowerCase() === "true".toLowerCase();
    }

    #verificaIndice(th) {
        let value = th.getAttribute("data-row-sort");
        let numero = Number(value);
        if (value === null || value === "" || Number.isNaN(numero)) return false;
        return true;
    }

    #getDOMFilter(type, column) {
        let divContent = document.createElement("div");
        divContent.className = "btn-group float-end";

        let parser = new DOMParser();
        let id = guid();
        let button = parser
            .parseFromString(
                '<button type="button" class="btn btn-primary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false"><i class="fa fa-filter" aria-hidden="true"></i> </button>',
                "text/html"
            )
            .querySelector("button");

        let divider = parser
            .parseFromString('<div class="dropdown-divider"></div>', "text/html")
            .querySelector("div");

        let desc = parser
            .parseFromString(
                '<a class="dropdown-item" href="#"> <i class="fa fa-sort-alpha-desc" aria-hidden="true"></i> Ordenar de Z a A</a>',
                "text/html"
            )
            .querySelector("a");
        desc.addEventListener("click", (event) => {
            event.preventDefault();
            ordenarTablaPorTipo(column, this.Table, false);
        });

        let asc = parser
            .parseFromString(
                '<a class="dropdown-item" href="#"> <i class="fa fa-sort-alpha-asc" aria-hidden="true"></i> Ordenar de A a Z</a>',
                "text/html"
            )
            .querySelector("a");
        asc.addEventListener("click", (event) => {
            event.preventDefault();
            ordenarTablaPorTipo(column, this.Table, true);
        });
        let clear = parser
            .parseFromString(
                '<a id="clear' +
                id +
                '" class="dropdown-item disabled" data-column="' +
                column +
                '" href="#"> <i class="fa fa-trash-o" aria-hidden="true"></i> Limpiar filtro</a>',
                "text/html"
            )
            .querySelector("a");
        clear.addEventListener("click", (event) => {
            event.preventDefault();
            clearFilterTable(event.target.dataset.column, this.Table);
            event.target.className = "dropdown-item disabled";
        });
        let dropdownContent = parser
            .parseFromString('<div class="dropdown-menu"></div>', "text/html")
            .querySelector("div");

        let divFormContent = document.createElement("div");
        divFormContent.className = "px-4 py-3";

        let containerSelectGroup = document.createElement("div");
        containerSelectGroup.className = "mb-3";
        containerSelectGroup.innerHTML = TableBoostrap.#getTipoElmento(type, id);

        let containerSearchGroup = document.createElement("div");
        containerSearchGroup.className = "mb-3";
        containerSearchGroup.innerHTML =
            '<input type="' +
            type +
            '" class="form-control" id="search' +
            id +
            '" placeholder="Buscar">';

        let buttonApply = document.createElement("button");
        buttonApply.className = "btn btn-primary";
        buttonApply.setAttribute("data-id", id);
        buttonApply.setAttribute("data-column", column);
        buttonApply.innerHTML = "Aceptar";
        buttonApply.addEventListener("click", (event) => {
            filterTable(
                event.target.dataset.column,
                type,
                this.Table,
                document.getElementById("filter" + event.target.dataset.id),
                document.getElementById("search" + event.target.dataset.id)
            );
            document.getElementById("search" + event.target.dataset.id).value = "";
            document.getElementById("clear" + event.target.dataset.id).className =
                "dropdown-item";
        });

        divFormContent.appendChild(containerSelectGroup);
        divFormContent.appendChild(divider);
        divFormContent.appendChild(containerSearchGroup);
        divFormContent.appendChild(buttonApply);

        dropdownContent.appendChild(divider);
        dropdownContent.appendChild(asc);
        dropdownContent.appendChild(desc);
        dropdownContent.appendChild(clear);
        dropdownContent.appendChild(divider);
        dropdownContent.appendChild(divFormContent);

        //Contenerdor principal
        divContent.appendChild(button);
        divContent.appendChild(dropdownContent);

        return divContent;
    }

    static #getTipoElmento(type, id) {
        let html;
        switch (type) {
            case "number":
                html =
                    '<select id="filter' +
                    id +
                    '" class="form-select" aria-label="Seleccionar Default"> ' +
                    '<option value="1" selected>Mayor que</option>' +
                    '<option value="2">Mayor igual que</option>' +
                    '<option value="3">Menor que</option>' +
                    '<option value="4">Menor igual que</option>' +
                    '<option value="5">Igual</option>' +
                    '<option value="10">Distinto de</option>' +
                    "</select>";
                break;
            case "date":
                html =
                    '<select id="filter' +
                    id +
                    '" class="form-select" aria-label="Seleccionar Default"> ' +
                    '<option value="1" selected>Mayor que</option>' +
                    '<option value="2">Mayor igual que</option>' +
                    '<option value="3">Menor que</option>' +
                    '<option value="4">Menor igual que</option>' +
                    '<option value="5">Igual</option>' +
                    '<option value="10">Distinto de</option>' +
                    "</select>";
                break;
            case "text":
                html =
                    '<select id="filter' +
                    id +
                    '" class="form-select" aria-label="Seleccionar Default"> ' +
                    '<option value="5" selected>igual</option>' +
                    '<option value="6">Contiene</option>' +
                    '<option value="7">Termina por</option>' +
                    '<option value="8">Empieza por</option>' +
                    '<option value="9">No contiene</option>' +
                    '<option value="10">Distinto de</option>' +
                    "</select>";
                break;
            default:
                html =
                    '<select id="filter' +
                    id +
                    '" class="form-select" aria-label="Seleccionar Default"> ' +
                    '<option value="-1" selected>NAN</option>' +
                    "</select>";
                break;
        }
        return html;
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
            await this.#enviarSolicitud(form.id, event.target);
        });
        this.element.insertAdjacentElement(
            "afterend",
            modal.getElementById("modal")
        );
    }

    async getUpdateTable(params) {
        let url = this.element.getAttribute("data-url-data");
        if (url === null) throw new Error("No definio la ruta de acceso a la data");
        this.element.innerHTML = this.Loading;
        let result = await Get(url, params);
        this.element.innerHTML = result.toString();
        this.refreshTable();
        this.loadButtonToUpdate();
        this.loadButtonToDelete();
    }

    getUpdateTableDataToolbar(pagina) {
        let url = this.element.getAttribute("data-url-data");
        if (url === null) throw new Error("No definio la ruta de acceso a la data");

        let params = this.settings.params;
        params["search"] = this.TextSearch.value;
        params["pagina"] = pagina;

        this.element.innerHTML = this.Loading;
        Get(url, params)
            .then(function (response) {
                this.element.innerHTML = response;
                this.refreshTable();
            })
            .catch(function (err) {
                console.log(err);
            });
    }

    async newButtonAddElement(button) {
        let url = this.element.getAttribute("data-url-add");
        let data = '{' + button.getAttribute('data-atributes') + '}';
        if (!isValidJSON(data))
            throw new Error("El elemento proporcionado no tiene el formato json correcto.");
        let result = await Get(url, JSON.parse(data));
        this.Body.innerHTML = result;
        this.Send.setAttribute("data-option", "add");
        this.settings.loadContent();
        this.Modal.show();
    }

    loadButtonToDelete() {
        let buttons = this.element.querySelectorAll(
            '[data-delete="true"]'
        );
        buttons.forEach((button) => {
            if (!hasClickEventAssigned(button)) {
                button.addEventListener("click", async (event) => {
                    if (event.target.tagName === "A") event.preventDefault();
                    let id = event.target.dataset.id;
                    let urlUpdate = this.element.getAttribute("data-url-delete");
                    let dataDelete = '{' + button.getAttribute('data-atributes') + '}';
                    if (!isValidJSON(dataDelete))
                        throw new Error("El elemento proporcionado no tiene el formato json correcto.");
                    let result = await Get(urlUpdate, JSON.parse(dataDelete));
                    this.Body.innerHTML = result.toString();
                    this.Send.setAttribute("data-option", "delete");
                    this.Send.setAttribute("data-id", id);
                    this.Modal.show();
                });
            }
        });
    }

    loadButtonToUpdate() {
        let buttons = this.element.querySelectorAll(
            '[data-update="true"]'
        );
        buttons.forEach((button) => {
            if (!hasClickEventAssigned(button)) {
                button.addEventListener("click", async (event) => {
                    if (event.target.tagName === "A") event.preventDefault();
                    let id = event.target.dataset.id;
                    let urlUpdate = this.element.getAttribute("data-url-update");
                    let dataUpdate = '{' + button.getAttribute('data-atributes') + '}';
                    if (!isValidJSON(dataUpdate))
                        throw new Error("El elemento proporcionado no tiene el formato json correcto.");
                    let result = await Get(urlUpdate, JSON.parse(dataUpdate));
                    this.Body.innerHTML = result.toString();
                    this.Send.setAttribute("data-option", "update");
                    this.Send.setAttribute("data-id", id);
                    this.settings.loadContent();
                    this.Modal.show();
                });
            }
        });
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
            if (!result.toString().includes('class="container-error"')) {
                const tbody = this.Table.querySelector('tbody');
                let fila;
                switch (this.Send.dataset.option) {
                    case "update":
                        this.Modal.hide();
                        this.Body.innerHTML = '';
                        this.Send.innerHTML = 'ACEPTAR';
                        this.Send.removeAttribute("disabled");
                        this.Cancel.removeAttribute("disabled");

                        fila = document.getElementById('td' + button.getAttribute('data-id')).parentNode;
                        fila.innerHTML = result.toString();
                        fila.className = 'highlight-row';
                        setTimeout(() => {
                            fila.className = ''; // Agrega la clase visible para el fade-in
                        }, 5000);
                        alertMessageTable({ok: true, message: "se registro correctamente la informacion"});
                        this.loadButtonToUpdate();
                        this.loadButtonToDelete();
                        break;
                    case "add":
                        this.Modal.hide();
                        this.Body.innerHTML = '';
                        this.Send.innerHTML = 'ACEPTAR';
                        this.Send.removeAttribute("disabled");
                        this.Cancel.removeAttribute("disabled");

                        fila = document.createElement('tr');
                        fila.innerHTML = result.toString();
                        fila.className = 'fade-in-tr';
                        tbody.insertBefore(fila, tbody.firstChild);
                        setTimeout(() => {
                            fila.classList.add('visible'); // Agrega la clase visible para el fade-in
                        }, 10);
                        alertMessageTable({ok: true, message: "se registro correctamente la informacion"});
                        this.loadButtonToUpdate();
                        this.loadButtonToDelete();
                        break;
                    case "delete":
                        this.Body.innerHTML = result.toString();
                        setTimeout(() => {
                            this.Modal.hide();
                            this.Body.innerHTML = '';
                            this.Send.innerHTML = 'ACEPTAR';
                            this.Send.removeAttribute("disabled");
                            this.Cancel.removeAttribute("disabled");
                        }, 500);
                        fila = document.getElementById('td' + button.getAttribute('data-id')).parentNode;
                        fila.classList.add('fade-out');
                        setTimeout(() => {
                            fila.remove();
                        }, 1000);
                        alertMessageTable({ok: true, message: "se elimino correctamente la informacion"});
                        break;
                    case "data":
                        this.Modal.hide();
                        this.Body.innerHTML = '';
                        this.Send.innerHTML = 'ACEPTAR';
                        this.Send.removeAttribute("disabled");
                        this.Cancel.removeAttribute("disabled");

                        tbody.innerHTML = result.toString();

                        this.loadButtonToUpdate();
                        this.loadButtonToDelete();
                        break;
                    default:
                        alertMessageTable({ok: false, message: "No marco ningun operacion"});
                        break;
                }
            } else {
                alertMessageTable({ok: false, message: "Hubo un problema al registrar la informacion"});
            }
        }
    }
}

let sortDirections = [true, true, true];
let toast = new ToastModal(window.parent.document.getElementById("toast"), {});
Object.prototype.extends =
    Object.prototype.extends ||
    function (destination, source) {
        for (let property in source) destination[property] = source[property];
        return destination;
    };

function alertMessageTable(data) {
    toast.show({
        classNameToast: data.ok ? "success" : "danger",
        textBody: data.message,
        titleText: "Aviso",
        subtitleText: "",
    });
}

function sortTable(table, n, th) {
    let rows,
        switching,
        i,
        x,
        y,
        shouldSwitch,
        dir,
        switchcount = 0;
    switching = true;
    dir = sortDirections[n] ? "asc" : "desc"; // Alterna la dirección de la ordenación
    sortDirections[n] = !sortDirections[n]; // Cambia la dirección después de cada clic

    // Cambia los iconos de ordenación
    let icons = th.querySelectorAll('[data-sort="true"]');
    icons.forEach((icon) => {
        icon.className =
            dir === "asc"
                ? "column-header-container column-header-sorted-asc"
                : "column-header-container column-header-sorted-desc";
    });

    while (switching) {
        switching = false;
        rows = table.rows;
        for (i = 1; i < rows.length - 1; i++) {
            shouldSwitch = false;
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            if (dir === "asc") {
                if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            } else if (dir === "desc") {
                if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                    shouldSwitch = true;
                    break;
                }
            }
        }
        if (shouldSwitch) {
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            switchcount++;
        }
    }
}

function guid() {
    return "xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx".replace(/[xy]/g, function (c) {
        let r = (Math.random() * 16) | 0,
            v = c === "x" ? r : (r & 0x3) | 0x8;
        return v.toString(16);
    });
}

// Función para filtrar la tabla
function filterTable(columnIndex, type, table, selectInput, input) {
    let filter, condition, tr, tds, td;
    tr = table.querySelector("tbody").getElementsByTagName("tr");

    // Obtener el valor del campo de entrada y la condición seleccionada
    filter = input.value.toLowerCase();
    condition = selectInput.value;

    for (let i = 0; i < tr.length; i++) {
        tds = tr[i].getElementsByTagName("td");
        td = tds[columnIndex];
        if (td) {
            // Obtener el texto de la celda
            let cellValue = td.textContent || td.innerText;

            // Mostrar u ocultar la fila dependiendo si coincide con el filtro
            if (getConditions(condition, type, cellValue, filter)) {
                let column = td.getAttribute("data-filter");
                if (column !== null) {
                    if (parseInt(columnIndex) === parseInt(column)) {
                        tr[i].cells[columnIndex].removeAttribute("data-filter");
                        tr[i].style.display = "";
                    }
                }
            } else {
                tr[i].cells[columnIndex].setAttribute("data-filter", columnIndex);
                tr[i].style.display = "none";
            }
        }
    }
}

function clearFilterTable(columnIndex, table) {
    let tr, tds, td;
    tr = table
        .querySelector("tbody")
        .querySelectorAll('tr[style*="display: none"]');

    for (let i = 0; i < tr.length; i++) {
        tds = tr[i].getElementsByTagName("td");
        td = tds[columnIndex];
        if (td) {
            let column = td.getAttribute("data-filter");
            if (column !== null) {
                if (viewFilterRows(tr[i].querySelectorAll("td"), column)) {
                    tr[i].style.display = "";
                }
                tr[i].cells[columnIndex].removeAttribute("data-filter");
            }
        }
    }
}

function viewFilterRows(tr, indexCol) {
    let cantidad = 0;
    tr.forEach((td, index) => {
        if (index !== parseInt(indexCol)) {
            let column = td.getAttribute("data-filter");
            if (column !== null) cantidad++;
        }
    });
    return cantidad === 0;
}

function getConditions(condicion, type, cellValue, filtertextNumber) {
    let result = false;
    switch (type) {
        case "number":
            if (!isNaN(filtertextNumber) && !isNaN(cellValue)) {
                let number = Number.parseInt(cellValue);
                let filterNumber = Number.parseInt(filtertextNumber);
                switch (condicion) {
                    case "1":
                        result = filterNumber < number;
                        break;
                    case "2":
                        result = filterNumber <= number;
                        break;
                    case "3":
                        result = filterNumber > number;
                        break;
                    case "4":
                        result = filterNumber >= number;
                        break;
                    case "5":
                        result = filterNumber === number;
                        break;
                    case "10":
                        result = filtertextNumber !== cellValue;
                        break;
                }
            } else result = false;
            break;
        case "date":
            if (
                !isNaN(Date.parse(filtertextNumber)) &&
                !isNaN(Date.parse(cellValue))
            ) {
                let number = new Date(cellValue + "T12:00:00Z");
                let filterNumber = new Date(filtertextNumber + "T12:00:00Z");
                switch (condicion) {
                    case "1":
                        result = filterNumber < number;
                        break;
                    case "2":
                        result = filterNumber <= number;
                        break;
                    case "3":
                        result = filterNumber > number;
                        break;
                    case "4":
                        result = filterNumber >= number;
                        break;
                    case "5":
                        result = filterNumber.toISOString() === number.toISOString();
                        break;
                    case "10":
                        result = filterNumber.toISOString() !== number.toISOString();
                        break;
                }
            } else result = false;
            break;
        default:
            switch (condicion) {
                case "5":
                    result = filtertextNumber === cellValue.toLowerCase();
                    break;
                case "6":
                    result = cellValue.toLowerCase().includes(filtertextNumber);
                    break;
                case "7":
                    result = cellValue.toLowerCase().endsWith(filtertextNumber);
                    break;
                case "8":
                    result = cellValue.toLowerCase().startWidth(filtertextNumber);
                    break;
                case "9":
                    result = !cellValue.toLowerCase().includes(filtertextNumber);
                    break;
                case "10":
                    result = filtertextNumber !== cellValue.toLowerCase();
                    break;
            }
            break;
    }
    return result;
}

function isValidJSON(str) {
    try {
        JSON.parse(str);
        return true; // Si no hay errores, es un JSON válido
    } catch (e) {
        return false; // Si ocurre un error, no es un JSON válido
    }
}

function ordenarTablaPorTipo(indiceColumna, tabla, esAscendente) {
    let filas = Array.from(
        tabla.getElementsByTagName("tbody")[0].getElementsByTagName("tr")
    );

    // Ordenar las filas en base a la columna seleccionada
    filas.sort(function (a, b) {
        let valorA = a.getElementsByTagName("td")[indiceColumna].textContent.trim();
        let valorB = b.getElementsByTagName("td")[indiceColumna].textContent.trim();

        // Si la columna tiene números, convertirlos a números
        if (!isNaN(valorA) && !isNaN(valorB)) {
            valorA = parseFloat(valorA);
            valorB = parseFloat(valorB);
        }

        if (valorA < valorB) {
            return esAscendente ? -1 : 1;
        }
        if (valorA > valorB) {
            return esAscendente ? 1 : -1;
        }
        return 0;
    });

    // Volver a agregar las filas ordenadas al tbody
    let tbody = tabla.getElementsByTagName("tbody")[0];
    tbody.innerHTML = ""; // Limpiar el tbody actual
    filas.forEach(function (fila) {
        tbody.appendChild(fila); // Añadir cada fila ordenada de nuevo
    });
}

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

function hasClickEventAssigned(button) {
    return button.hasEventListener && button.hasEventListener('click');
}