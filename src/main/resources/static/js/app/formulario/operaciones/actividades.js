let comboCondition = new FilterComboBox(document.getElementById('option'));
let table = new TableBoostrap(document.getElementById('table-container'), {
    title: "Lista de actividades realizadas"
});

function cargarElementoFormulario(pagina) {
    let params = {
        pagina: pagina,
        mostrar: document.getElementById("mostrar").value,
        value: document.getElementById("buscar").value,
        type: document.getElementById("condiciones").value,
        option: document.getElementById("option").value,
        cookie: false,
        id: parseInt(document.getElementById("buscar").dataset.id),
        id_programa: parseInt(document.getElementById("buscar").dataset.id_programa),
        id_departamento: parseInt(document.getElementById("buscar").dataset.id_departamento)
    };
    table.getUpdateTable(params);
}

function limpiarCoockieElementoFormulario(pagina) {
    let params = {
        pagina: pagina,
        mostrar: document.getElementById("mostrar").value,
        cookie: true,
        id: parseInt(document.getElementById("buscar").dataset.id)
    };
    table.getUpdateTable(params);
}

function filtrarResultados(pagina) {
    if (event.keyCode === 13) {
        cargarElementoFormulario(pagina);
    }
}
