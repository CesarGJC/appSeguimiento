function filtrarResultados(event, pagina) {
    if (event.keyCode === 13) {
        cargarElemento(pagina);
    }
}

function cargarElemento(pagina) {
    let url = document.getElementById("buscar").dataset.url;
    let params = {
        pagina: pagina,
        mostrar: document.getElementById("mostrar").value,
        gestion: document.getElementById("gestion").value,
        periodo: document.getElementById("periodo").value,
        buscar: document.getElementById("buscar").value
    };
    let html = '<div class="overlay"><div class="m-loader mr-4"> <svg class="m-circular" viewBox="25 25 50 50"><circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"></circle></svg></div><h3 class="l-text">Loading</h3></div>';
    document.getElementById('datos').innerHTML = html;
    Get(url, params)
        .then(function (response) {
            document.getElementById('datos').innerHTML = response;
        })
        .catch(function (err) {
            console.log(err);
        })
}