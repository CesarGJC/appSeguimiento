const toast = new ToastModal(window.parent.document.getElementById('toast'), {});
function filtrarResultados(event, pagina) {
    if (event.keyCode === 13) {
        cargarElementoMenciones(pagina);
    }
}

function cargarElementoMenciones(pagina) {
    let url = document.getElementById("buscar").dataset.url;
    let params = {
        pagina: pagina,
        mostrar: document.getElementById("mostrar").value,
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
function eliminarElemento(event) {
    event.preventDefault();
    let data = {
        id_programa: event.target.dataset.id_programa,
        id_estudiante: event.target.dataset.id_estudiante,
        id_plan: event.target.dataset.id_plan,
        id_mencion: event.target.dataset.id_mencion
    }
    let url = event.target.dataset.url;
    swal({
        title: "¿Desea continuar con la eliminacion del registro?",
        text: "Presiona aceptar para continuar..",
        icon: "warning",
        buttons: {
            cancel: "Cancelar",
            catch: {
                text: "Aceptar",
                value: "Eliminar",
            }
        },
    })
        .then((value) => {
            switch (value) {
                case "Eliminar":
                    Post(url, data)
                        .then(function (response) {
                            console.log(response);
                            if (response.status === "OK") {
                                alertMessage(response.message, "success");
                                eliminarFila(event.target);
                            } else {
                                alertMessage(response.message, "danger");
                            }
                        })
                        .catch(function (err) {
                            alertMessage(err, "danger");
                        })

                    break;
                default:
                    alertMessage("Se cancelo la eliminacion del registro.", "warning");
            }
        });
}

function eliminarFila(boton) {
    let fila = boton.closest('tr'); // Obtener la fila más cercana al botón
    // Agregar la animación de desaparición
    fila.style.animation = 'fadeOut 1s forwards';
    // Eliminar la fila después de que termine la animación
    setTimeout(function () {
        fila.remove();
    }, 1000);
}

function alertMessage(text, className) {
    toast.show({
        classNameToast: className,
        textBody: text,
        titleText: "Aviso",
        subtitleText: ""
    });
}