let comboCondition = new FilterComboBox(document.getElementById('option'));
let table = new TableBoostrap(document.getElementById('table-container'), {
    title: "Lista Formularios P.E.I."
});

function cargarElementoFormulario(pagina) {
    let params = {
        pagina: pagina,
        mostrar: document.getElementById("mostrar").value,
        value: document.getElementById("buscar").value,
        type: document.getElementById("condiciones").value,
        option: document.getElementById("option").value,
        cookie: false
    };
    table.getUpdateTable(params);
}

function limpiarCoockieElementoFormulario(pagina) {
    let params = {
        pagina: pagina,
        mostrar: document.getElementById("mostrar").value,
        cookie: true
    };
    table.getUpdateTable(params);
}

function filtrarResultados(pagina) {
    if (event.keyCode === 13) {
        cargarElementoFormulario(pagina);
    }
}
