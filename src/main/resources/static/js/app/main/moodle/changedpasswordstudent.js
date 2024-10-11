let modal = new bootstrap.Modal(document.getElementById('idmodificarusuario'));

function guardarResultados() {
    if (document.getElementById('model') !== null) {
        submitForm('model', false)
            .then(function (data) {
                document.getElementById('content').innerHTML = data;
                if (document.getElementById('mensaje') !== null) {
                    document.getElementById('btncancelar').disabled = true;
                }
            })
            .catch(function (error) {
                console.log("Error en la solicitud AJAX");
                console.log(error);
            });
    }
    if (document.getElementById('mensaje') !== null) {
        modal.hide();
    }
}

function modificarpassword(input) {
    document.getElementById('btncancelar').disabled = false;
    let data = {
        id_persona: input.dataset.persona,
        indice: input.dataset.indice
    };
    Get('./modificarUsuario', data)
        .then(function (data) {
            document.getElementById('content').innerHTML = data;
            modal.show();
        })
        .catch(function (error) {
            console.log("Error en la solicitud AJAX");
            console.log(error);
        });
}