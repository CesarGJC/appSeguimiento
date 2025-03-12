let table = new TableBoostrap(document.getElementById('table-container'), {
    title: "Lista de Areas Estrategicas",
    add: document.getElementById('addButton')
});

function cargarElementoAreas(pagina) {
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
        cargarElementoAreas(pagina);
    }
}