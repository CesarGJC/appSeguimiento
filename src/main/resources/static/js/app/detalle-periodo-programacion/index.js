let comboboxArea;
let table = new TableBoostrap(document.getElementById('table-container'), {
    title: "Lista de los Planes Estrategicos Institucionales",
    add: document.getElementById('addButton'),
    loadContent: () => {
        comboboxArea = new ComboBoxBoostrap('.filter', {});
    }
});

function cargarElementoDetallePeriodoProgramacion(pagina) {
    let params = {
        pagina: pagina,
        mostrar: document.getElementById("mostrar").value,
        buscar: document.getElementById("buscar").value,
        opcion: parseInt(document.getElementById("opcion").value), // Valor del select
        id: parseInt(document.getElementById("id").value)


    };
    table.getUpdateTable(params);
}

function filtrarResultados(pagina) {
    if (event.keyCode === 13) {
        cargarElementoDetallePeriodoProgramacion(pagina);
    }
}
