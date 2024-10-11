let modal = new bootstrap.Modal(document.getElementById('idmodificarusuario'));
function guardarResultados() {
    if (document.getElementById('model') !== null) {
        submitForm('model', false)
            .then(function (data) {
                document.getElementById('content').innerHTML = data;
                if (document.getElementById('mensaje') !== null) {
                    document.getElementById('btncancelar').disabled = true;
                    document.getElementById('pass641').dataset.pass = document.getElementById('mensaje').dataset.pass;
                    document.getElementById('pass1').innerHTML = "********************";
                    document.getElementById('pass641').dataset.encode = 'false';
                    document.getElementById('icon1' ).className = 'fa fa-eye';
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

function mostrarpassword(id, input) {
    let encode = input.dataset.encode;
    if (encode === "true") {
        let getval = input.dataset.pass;
        let result = btoa(getval);
        input.dataset.pass = result;
        document.getElementById('pass' + id).innerHTML = "********************";
        input.dataset.encode = 'false';
        document.getElementById('icon' + id).className = 'fa fa-eye';
    } else {
        let getval = input.dataset.pass;
        let result = atob(getval);
        input.dataset.pass = result;
        document.getElementById('pass' + id).innerHTML = result;
        document.getElementById('icon' + id).className = 'fa fa-eye-slash';
        input.dataset.encode = 'true';
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