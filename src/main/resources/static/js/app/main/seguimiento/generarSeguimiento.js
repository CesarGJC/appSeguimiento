function generarSeguimiento(name, input) {
    let html = '<div class="overlay"><div class="m-loader mr-4"> <svg class="m-circular" viewBox="25 25 50 50"><circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"></circle></svg></div><h3 class="l-text">Loading</h3></div>';
    document.getElementById('informeSeguimiento').innerHTML = html;
    input.disabled = true;
    document.getElementById('btnCancelar').classList.add("disabled");
    submitForm(name, false)
        .then(function (response) {
            document.getElementById('informeSeguimiento').innerHTML = response;
            input.disabled = false;
            document.getElementById('btnCancelar').classList.remove("disabled");
        })
        .catch(function (err) {
            input.disabled = false;
            document.getElementById('btnCancelar').classList.remove("disabled");
        });
}