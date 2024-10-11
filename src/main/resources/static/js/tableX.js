var tableX = "tableX",
    preview_cell,
    tableX_class = tableX,
    tableX_cell = "tableX-cell",
    tableX_disabled = tableX_cell + "-disabled",
    tableX_cell_class = tableX_cell,
    active_cell = "active-cell",
    active_cell_class = "." + active_cell,
    tableX_txtbox = "tableX-txt-box",
    tableX_txtbox_class = "." + tableX_txtbox,
    projected = "projected",
    text_area_html = '<input type="number" class="' + tableX_txtbox + '"/>',
    selected_area = "selected-area",
    selected_area_class = "." + selected_area,
    clicked = false,
    STRING = "string",
    ctrl_key_pressed = false,
    caret_position = 0,
    TD = "td",
    TR = "tr",
    TBODY_TR = "tbody tr",
    tableX_clipboard_text_area = "tableX-clipboard-text-area",
    tableX_clipboard_text_area_class = "." + tableX_clipboard_text_area,
    longitud = 0,
    celllongitud = 0,
    edit = false,
    key_codes = {
        nav_left: 37,
        nav_right: 39,
        nav_up: 38,
        nav_down: 40,
        tab: 9,
        delete: 46,
        escape: 27,
        enter: 13,
        ctrl: 17,
        c: 67,
        a: 65,
        v: 86,
        x: 88,
    };

let toastmessage = new ToastModal(document.getElementById('errortoasttable'), {});

function updateCaretPosition() {
    let textbox = document
        .getElementsByClassName(tableX_class)[0]
        .querySelectorAll(tableX_txtbox_class);
    for (let i = 0; i < textbox.length; i++) {
        textbox[i].addEventListener("keydown", (event) => {
            caret_position = this.selectionStart;
        });
    }
}

function clickAndSelectArea() {
    let start_column_cell_index, start_cell_row_index, start_cell;
    const table = document.getElementsByClassName(tableX_class)[0];

    table.addEventListener("mousedown", function (event) {
        if (event.target.matches(TD + tableX_cell_class)) {
            clicked = true;
            event.target.dispatchEvent(new Event("click"));
            start_cell = event.target;
            start_column_cell_index = event.target.cellIndex;
            start_cell_row_index = event.target.parentElement.rowIndex;
            if (edit) {
                saveData(preview_cell);
                edit = false;
            }
        }
    });
    table.addEventListener("mousedown", function (event) {
        if (event.target.matches(TD + tableX_cell_class)) {
            if (clicked == false) {
                return;
            }
            tableX.style.cursor = "default";
            var end_column_cell_index = event.target.cellIndex;
            var end_cell_row_index = event.target.parentElement.rowIndex;

            if (
                start_column_cell_index == end_column_cell_index &&
                start_cell_row_index == end_cell_row_index
            ) {
                event.target.dispatchEvent(new Event("click"));
            }
            var start_select, end_select;
            if (
                start_column_cell_index != end_column_cell_index &&
                start_cell_row_index == end_cell_row_index
            ) {
                if (start_column_cell_index < end_column_cell_index) {
                    start_select = start_column_cell_index;
                    end_select = end_column_cell_index;
                } else {
                    start_select = end_column_cell_index;
                    end_select = start_column_cell_index;
                }
                const selectedElements = Array.from(
                    event.target.parentElement.querySelectorAll(selected_area_class)
                );

                selectedElements.forEach((element) => {
                    const element_index = element.cellIndex;
                    if (!(start_select <= element_index && end_select >= element_index)) {
                        element.classList.remove(selected_area);
                    }
                });
                Array.from(event.target.closest("table").rows).forEach((row) => {
                    const rowCells = Array.from(row.cells);
                    if (rowCells[end_column_cell_index]) {
                        rowCells.splice(end_column_cell_index, 1);
                        rowCells.forEach((cell) => {
                            cell.classList.remove(selected_area);
                        });
                    }
                });
                Array.from(event.target.parentElement.querySelectorAll("td"))
                    .slice(start_select, end_select + 1)
                    .forEach((cell) => {
                        cell.classList.add(selected_area);
                    });
            }

            // clicked and moved in same column but different row
            if (
                start_column_cell_index == end_column_cell_index &&
                start_cell_row_index != end_cell_row_index
            ) {
                if (start_cell_row_index < end_cell_row_index) {
                    start_select = start_cell_row_index;
                    end_select = end_cell_row_index;
                } else {
                    start_select = end_cell_row_index;
                    end_select = start_cell_row_index;
                }

                var $selected_area_rows = getSelectedAreaRows($(this));

                $selected_area_rows.forEach((element) => {
                    const element_index = element.rowIndex;
                    if (!(start_select <= element_index && end_select >= element_index)) {
                        Array.from(element.cells).forEach((cell) => {
                            cell.classList.remove(selected_area);
                        });
                    }
                });

                var $selected_rows = Array.from(
                    event.target.closest("table").rows
                ).slice(start_select, end_select + 1);

                $selected_rows.forEach((row) => {
                    Array.from(row.cells)
                        .filter((cell, index) => {
                            return (
                                index !== start_column_cell_index &&
                                cell.classList.contains(selected_area)
                            );
                        })
                        .forEach((cell) => {
                            cell.classList.remove(selected_area);
                        });
                });

                $selected_rows.forEach((row) => {
                    const cell = row.cells[start_column_cell_index];
                    cell.classList.add(selected_area);
                });
            }
            // clicked and moved in different column and different row
            var row_select_start, row_select_end;
            if (
                start_column_cell_index != end_column_cell_index &&
                start_cell_row_index != end_cell_row_index
            ) {
                if (start_column_cell_index < end_column_cell_index) {
                    start_select = start_column_cell_index;
                    end_select = end_column_cell_index;
                } else {
                    start_select = end_column_cell_index;
                    end_select = start_column_cell_index;
                }
                if (start_cell_row_index < end_cell_row_index) {
                    row_select_start = start_cell_row_index;
                    row_select_end = end_cell_row_index;
                } else {
                    row_select_start = end_cell_row_index;
                    row_select_end = start_cell_row_index;
                }

                $selected_area_rows = getSelectedAreaRows($(this));
                $selected_area_rows.each(function (index, row) {
                    var element_index = row.rowIndex;
                    $(row)
                        .querySelectorAll(selected_area_class)
                        .forEach(function (element) {
                            var element_index = element.cellIndex;
                            if (
                                !(start_select <= element_index && end_select >= element_index)
                            ) {
                                element.classList.remove(selected_area);
                            }
                        });

                    if (
                        !(
                            row_select_start <= element_index &&
                            row_select_end >= element_index
                        )
                    ) {
                        $(row)
                            .querySelectorAll(TD)
                            .forEach(function (cell) {
                                cell.classList.remove(selected_area);
                            });
                    }
                });

                var $current_selection_rows = this.closest("table")
                    .querySelectorAll(TR)
                    .slice(row_select_start, row_select_end + 1);

                $current_selection_rows.forEach(function (row) {
                    var $cells = row.querySelectorAll(TD);
                    for (var i = start_select; i <= end_select; i++) {
                        $($cells[i]).addClass(selected_area);
                    }
                });
            }
        }
        copyAll(event.target);
    });
}

function init() {
    var table = document.getElementsByClassName(tableX_class)[0];

    table.addEventListener("click", function (e) {
        var $parent_tbody = e.target.closest("tbody");
        if ($parent_tbody == null) return;
        var $tableX_cells = $parent_tbody.querySelectorAll("." + tableX_cell_class);
        if (!hasClass(e.target, tableX_cell)) return;

        $tableX_cells.forEach(function ($tableX_cell) {
            $tableX_cell.classList.remove(active_cell);
        });
        $parent_tbody
            .querySelectorAll(selected_area_class)
            .forEach(function ($selected_area) {
                $selected_area.classList.remove(selected_area);
            });
        e.target.classList.add(active_cell);
        var text_box_val = $parent_tbody.querySelector(tableX_txtbox_class);
        if (text_box_val === null) {
            return;
        }
        text_box_val = $parent_tbody.querySelector(tableX_txtbox_class).value;
        var $text_box_parent =
            $parent_tbody.querySelector(tableX_txtbox_class).parentElement;
        $text_box_parent.textContent = text_box_val;
        $text_box_parent.classList.remove(projected);
        copyAll(e.target);
    });
    table.addEventListener("dblclick", function (e) {
        var target = e.target;
        if (!hasClass(e.target, tableX_cell)) return;
        if (target.querySelector(tableX_txtbox_class) !== null) {
            return;
        }

        var cellText = target.textContent.trim();
        target.textContent = "";
        target.classList.add(projected);

        var textArea = document.createElement("input");
        textArea.className = tableX_txtbox_class.substring(1);
        textArea.setAttribute("type", "number");
        textArea.value = cellText;
        textArea.style.width = "48px";
        target.appendChild(textArea);
        textArea.focus();
        preview_cell = target;
        edit = true;
    });

    document.body.insertAdjacentHTML(
        "beforeend",
        '<textarea class="tableX-clipboard-text-area"></textarea>'
    );
}

var tableX = function (customSettings) {
    // Default settings

    updateCaretPosition();
    clickAndSelectArea();
    init();
    var settings = {
        index_from_left: 0,
        index_from_right: 0,
        index_from_top: 0,
        index_from_bottom: 0,
        /* and so on... */
    };
    customSettings || (customSettings = {});
    Object.extend(settings, customSettings);

    var options = settings;
    var $rows = document.querySelectorAll(TBODY_TR);
    var rows_length = $rows.length;
    longitud = rows_length;
    /* Awesome more methods goes here */

    return $rows.forEach(function (row, row_index) {
        if (isDefined(options)) {
            if (isDefined(options.index_from_top)) {
                if (row_index <= options.index_from_top) {
                    return true;
                }
            }
            if (isDefined(options.index_from_bottom)) {
                var $row_index_need_to_excluded =
                    rows_length - (options.index_from_bottom + 1);
                if (row_index >= $row_index_need_to_excluded) {
                    return true;
                }
            }
        }
        var $cells = row.querySelectorAll(TD);
        var cells_length = $cells.length;
        border = "1";
        celllongitud = cells_length;
        $cells.forEach(function (cell, cell_index) {
            var $cell = cell;
            if (isDefined(options)) {
                if (isDefined(options.index_from_left)) {
                    if (cell_index <= options.index_from_left) {
                        return true;
                    }
                }
                if (isDefined(options.index_from_right)) {
                    var $cell_index_need_to_excluded =
                        cells_length - (options.index_from_right + 1);
                    if (cell_index >= $cell_index_need_to_excluded) {
                        return true;
                    }
                }
            }
            if (hasClass($cell, tableX_disabled)) {
                return true;
            }
            $cell.classList.add(tableX_cell);
        });
    }); // Only for testing
};

function saveData(cell) {
    if (cell.dataset.estado == 0) {
        cell.innerHTML = cell.dataset.nota;
        messageSend("Esta intentando modificar una nota con fecha fuera de plazo comunicarse con DTIC.");
    } else {
        if (isNaN(cell.innerHTML)) {
            messageSend("Introdujo un valor no numerico vuelva a registrar la nota del alumno");
            cell.innerHTML = cell.dataset.nota;
        } else {
            if (parseInt(cell.innerHTML) > 100) {
                messageSend("El dato : " + cell.innerHTML + "  no es un numero valido. Introduzca un numero del 0 al 100.");
                cell.innerHTML = cell.dataset.nota;
                return false;
            } else {
                let valor = document.getElementsByName('id_tipo_nota')[0].value;
                let ids = valor.split(':');
                let data = {
                    id_asignacion: document.getElementsByName('id_asignacion')[0].value,
                    id_programa: document.getElementsByName('id_programa')[0].value,
                    id_tipo_nota: ids[0],
                    id_tipo_grado: document.getElementsByName('id_tipo_grado')[0].value,
                    nro_nota_s: cell.dataset.nronota,
                    gestion: document.getElementsByName('gestion')[0].value,
                    periodo: document.getElementsByName('periodo')[0].value,
                    id_modelo_ahorro: document.getElementsByName('id_modelo_ahorro')[0].value,
                    id_materia: document.getElementsByName('id_materia')[0].value,
                    id_fase: document.getElementsByName('id_fase')[0].value,
                    id_tipo_evaluacion: document.getElementsByName('id_tipo_evaluacion')[0].value,
                    id_grupo: document.getElementsByName('id_grupo')[0].value,
                    id_departamento: document.getElementsByName('id_departamento')[0].value,
                    id_estudiante: cell.dataset.idestudiante,
                    nota: parseFloat(cell.innerHTML),
                    ubicacion: document.getElementsByName('ubicacion')[0].value,
                }
                Post('./registrarNotasEstudiantes', data)
                    .then(function (data) {
                        if (data.status !== "OK") {
                            messageSend(data.message);
                            cell.innerHTML = cell.dataset.nota;
                            cell.dataset.status = "not";
                        } else {
                            cell.dataset.nota = cell.innerHTML;
                            cell.dataset.status = "ok";
                        }
                    })
                    .catch(function (error) {
                        messageSend("Problemas con el servidor o la conexion de red: " + error);
                    });
            }
        }
    }
}

function messageSend(text) {
    toastmessage.show({
        classNameToast: 'danger',
        textBody: text,
        titleText: "Aviso",
        subtitleText: ""
    });
}

function isDefined(element) {
    return typeof element !== undefined;
}

function hasClass(target, className) {
    return new RegExp("(\\s|^)" + className + "(\\s|$)").test(target.className);
}

function getSelectedAreaRows(target) {
    const table = target.closest("table");
    return Array.from(table.rows).filter((row) => {
        return Array.from(row.cells).some((cell) => {
            return cell.classList.contains(selected_area);
        });
    });
}

function copyAll(activeCell) {
    let copiedData = "";
    if (!hasClass(activeCell, tableX_cell)) return;

    const selectedAreaRows = getSelectedAreaRows(activeCell);

    if (selectedAreaRows.length === 0) {
        activeCell.click(true);
        copiedData = activeCell.textContent;
        document.querySelector(tableX_clipboard_text_area_class).value = copiedData;
        document.querySelector(tableX_clipboard_text_area_class).select();
        return copiedData;
    }

    const copiedDataArray = [];
    selectedAreaRows.forEach((row, index) => {
        const cells = Array.from(row.querySelectorAll(selected_area_class));
        copiedDataArray[index] = cells.map((cell) => {
            return cell.textContent.trim();
        });
    });

    for (let i = 0; i < copiedDataArray.length; i++) {
        copiedData += copiedDataArray[i].join("\t") + "\n";
    }

    document.querySelector(tableX_clipboard_text_area_class).value = copiedData;
    document.querySelector(tableX_clipboard_text_area_class).select();
    return copiedData;
}

Object.prototype.extend =
    Object.prototype.extend ||
    function (destination, source) {
        for (var property in source) destination[property] = source[property];
        return destination;
    };

function navDown($active_cell, active_cell_index) {
    var parent_tr = $active_cell.parentNode;
    var parent_tbody = $active_cell.closest("tbody");
    var rowindex = parent_tr.rowIndex;
    if (rowindex == longitud) {
        $active_cell.click();
    } else {
        var target_tr = parent_tbody.querySelectorAll("tr")[parent_tr.rowIndex];
        var target_cell = target_tr.querySelectorAll("td")[active_cell_index];
        if (target_cell.classList.contains("tableX-cell")) {
            $active_cell.click();
            $active_cell.classList.remove("active-cell");
            target_cell.classList.add("active-cell");
        }
    }
    if (edit) {
        saveData($active_cell);
        edit = false;
    }
}

function canNavigate(active_cell, nav_code) {
    var text_box = active_cell.querySelectorAll(".tableX-txtbox");
    if (text_box.length !== 0) {
        if (nav_code === key_codes.nav_right) {
            return text_box[0].value.length === caret_position;
        } else if (nav_code === key_codes.nav_left) {
            return caret_position === 0;
        } else {
            return true;
        }
    } else {
        return true;
    }
}

function pasteData(activeCell) {
    var clipboardData = document
        .querySelector(".tableX-clipboard-text-area")
        .value.trim();
    var pasteDataInfo = [];
    var rowArray = clipboardData.split("\n");
    for (var i = 0; i < rowArray.length; i++) {
        pasteDataInfo.push(rowArray[i].split("\t"));
    }
    var startingRowIndex, currentCell;
    startingRowIndex = activeCell.parentNode.rowIndex;
    currentCell = activeCell;
    for (i = 0; i < pasteDataInfo.length; i++) {
        var innerArray = pasteDataInfo[i];
        for (var k = 0; k < innerArray.length; k++) {
            currentCell.textContent = innerArray[k];
            currentCell.dispatchEvent(new Event("keyup"), {
                include_active_cell: false,
            });
            if (currentCell.nextElementSibling.classList.contains("tableX-cell")) {
                currentCell = currentCell.nextElementSibling;
            }
        }

        var nextRow = document.querySelector(
            ".tableX tbody tr:nth-child(" + (startingRowIndex + i + 1) + ")"
        );
        currentCell = nextRow.querySelector(
            ".tableX-cell:nth-child(" + (activeCell.cellIndex + 1) + ")"
        );
    }
}

document.onmouseup = function () {
    clicked = false;
};
document.onkeyup = function (e) {
    var table = document.getElementsByClassName(tableX_class)[0];
    if (key_codes.ctrl == e.keyCode) {
        ctrl_key_pressed = false;
    }
    if (key_codes.v == e.keyCode) {
        if (!ctrl_key_pressed) return;
        var activeCell = document.querySelector(".active-cell");
        pasteData(activeCell);
    }
};
document.onkeydown = function (e) {
    var table = document.getElementsByClassName(tableX_class)[0];
    var active_cell = table.querySelector(".active-cell");
    var active_cell_index = Array.prototype.indexOf.call(
        active_cell.parentNode.children,
        active_cell
    );
    if (e.ctrlKey) {
        ctrl_key_pressed = true;
    }

    switch (e.keyCode) {
        case key_codes.nav_left:
            if (
                active_cell.previousElementSibling.classList.contains(tableX_cell) &&
                canNavigate(active_cell, key_codes.nav_left)
            ) {
                active_cell.click();
                active_cell.classList.remove("active-cell");
                active_cell.previousElementSibling.classList.add("active-cell");
                if (edit) {
                    saveData(active_cell);
                    edit = false;
                }
            }
            break;

        case key_codes.tab:
            e.preventDefault();
        case key_codes.nav_right:
            var cell = active_cell.cellIndex;
            if (cell === celllongitud - 1) {
                active_cell.click();
            } else {
                active_cell.focus();
                if (
                    active_cell.nextElementSibling.classList.contains(tableX_cell) &&
                    canNavigate(active_cell, key_codes.nav_right)
                ) {
                    active_cell.click();
                    active_cell.classList.remove("active-cell");
                    active_cell.nextElementSibling.classList.add("active-cell");
                    if (edit) {
                        saveData(active_cell);
                        edit = false;
                    }
                }
            }
            break;

        case key_codes.nav_up:
            var parent_tr = active_cell.parentNode;
            var parent_tbody = active_cell.closest("tbody");
            var target_tr = parent_tbody.querySelectorAll(TR)[parent_tr.rowIndex - 2];
            if (parent_tr.rowIndex - 2 === -1) {
                active_cell.click();
            } else {
                var target_cell = target_tr.querySelectorAll(TD)[active_cell_index];
                if (
                    target_cell.classList.contains(tableX_cell) &&
                    parent_tr.rowIndex != 0
                ) {
                    active_cell.click();
                    active_cell.classList.remove("active-cell");
                    target_cell.classList.add("active-cell");
                    if (edit) {
                        saveData(active_cell);
                        edit = false;
                    }
                }
            }

            break;

        case key_codes.nav_down:
            navDown(active_cell, active_cell_index);
            break;

        case key_codes.delete:
            active_cell.textContent = "";
            active_cell.dispatchEvent(new KeyboardEvent("keyup"));
            active_cell
                .closest("table")
                .querySelectorAll(selected_area_class)
                .forEach(function (elem) {
                    elem.textContent = "";
                    elem.dispatchEvent(new KeyboardEvent("keyup"));
                });
            edit = false;
            break;

        case key_codes.escape:
            active_cell.click();
            edit = false;
            break;

        case key_codes.enter:
            if (active_cell.querySelector(tableX_txtbox_class) !== null) {
                navDown(active_cell, active_cell_index);
            } else {
                active_cell.dispatchEvent(new MouseEvent("dblclick"));
            }
            break;

        case key_codes.a:
            if (ctrl_key_pressed) {
                active_cell.click();
                active_cell
                    .closest("table")
                    .querySelectorAll(tableX_cell_class)
                    .forEach(function (elem) {
                        elem.classList.add(selected_area);
                    });
                active_cell.classList.remove(active_cell);
                document
                    .querySelectorAll(selected_area_class)[0]
                    .classList.add(active_cell);
                copyAll(active_cell);
            } else {
                if (e.target.classList.contains("search-input")) return;
                if (active_cell.querySelector(tableX_txtbox_class) !== null) return;
                active_cell.textContent = "";
                active_cell.dispatchEvent(new MouseEvent("dblclick"));
            }
            break;

        case key_codes.c:
            if (ctrl_key_pressed) {
                return;
            } else {
                if (e.target.classList.contains("search-input")) return;
                if (active_cell.querySelector(tableX_txtbox_class) !== null) return;
                active_cell.textContent = "";
                active_cell.dispatchEvent(new MouseEvent("dblclick"));
            }
            break;

        case key_codes.v:
            if (ctrl_key_pressed) {
                document.querySelector(tableX_clipboard_text_area_class).focus();
                document.querySelector(tableX_clipboard_text_area_class).value = "";
            }
            break;

        case key_codes.x:
            if (!ctrl_key_pressed) return;
            document.querySelectorAll(selected_area_class).forEach(function (elem) {
                elem.textContent = "";
                elem.dispatchEvent(new KeyboardEvent("keyup"));
            });
            break;

        default:
            if (ctrl_key_pressed) return;
            if (e.target.classList.contains("search-input")) return;
            if (e.target.classList.contains(tableX_txtbox)) return;

            if (e.keyCode == 78) {
                active_cell.click();
                active_cell.textContent = "na";
                active_cell.dispatchEvent(new Event("keyup"));
                return;
            }

            if (e.keyCode == 79 || e.keyCode == 80) {
                active_cell.click();
                active_cell.textContent = "op";
                active_cell.dispatchEvent(new Event("keyup"));
                return;
            }
            var eventoDobleClic = new MouseEvent("dblclick", {
                view: window,
                bubbles: true,
                cancelable: true,
            });
            active_cell.textContent = "";
            active_cell.dispatchEvent(eventoDobleClic);
            break;
    }
};
