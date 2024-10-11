const toast = new ToastModal(window.parent.document.getElementById('toast'), {});
function eliminarElemento(event) {
    event.preventDefault();
    let data = {
        id_calificacion_calendario: event.target.dataset.id_calificacion_calendario,
        periodo: event.target.dataset.periodo,
        gestion: event.target.dataset.gestion,
        id_programa: event.target.dataset.id_programa
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