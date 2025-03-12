let comboboxArea;
let table = new TableBoostrap(document.getElementById('table-container'), {
    title: "Lista de Tipos de Unidad",
    add: document.getElementById('addButton'),
    loadContent: () => {
        comboboxArea = new ComboBoxBoostrap('.filter', {});
    }
});

function cargarElementoUnidadMedida(pagina) {
    let params = {
        pagina: pagina,
        mostrar: document.getElementById("mostrar").value,
        buscar: document.getElementById("buscar").value,
        option: document.getElementById("option").value
    };
    table.getUpdateTable(params);
}

function filtrarResultados(pagina) {
    if (event.keyCode === 13) {
        cargarElementoUnidadMedida(pagina);
    }
}
