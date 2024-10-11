function cambiarTipoBusqueda(input) {
    if (parseInt(input.value) == 1)
        document.getElementById("buscar").type = 'number';
    else
        document.getElementById("buscar").type = 'text';
}

document.getElementById('model').addEventListener("keydown", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        enviarSolicitudFormularioTipo('model');
    }
});
function enviarSolicitudFormularioTipo(form) {
    let tipoArgumento = parseInt(document.getElementById('opcion').value);
    if (tipoArgumento != 3) {
        enviarsolicitud(document.getElementById(form));
    } else {
        let html = '<div class="tile border border-primary"><div class="overlay"><div class="m-loader mr-4"><svg class="m-circular" viewBox="25 25 50 50"><circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"></circle></svg></div><h3 class="l-text">Loading</h3></div></div>';
        document.getElementById('buscarPersonas').innerHTML = html;
        sendForm(form).then(function (data) {
            document.getElementById('buscarPersonas').innerHTML = data;
        }).catch(function (error) {
            document.getElementById('buscarPersonas').innerHTML = '';
        });
    }
}