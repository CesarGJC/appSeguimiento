let table = new TableBoostrap(document.getElementById('table-container'), {
    title: "Lista de Catalogo de Indicadores"
});


function cargarElementoCatalogos(pagina) {
    let params = {
        pagina: pagina,
        mostrar: document.getElementById("mostrar").value,
        buscar: document.getElementById("buscar").value,
        opcion: parseInt(document.getElementById("option").value),
        id: parseInt(document.getElementById("id").value)
    };
    table.getUpdateTable(params);
}

function filtrarResultados(pagina) {
    if (event.keyCode === 13) {
        cargarElementoAreas(pagina);
    }
}
