function deleteelement(url, data) {
    swal({
        title: "¿Desea continuar con la eliminacion del registro?",
        text: "Presiona aceptar para continuar..",
        icon: "warning",
        className: "sweet-alert",
        buttons: true,
        dangerMode: true,
        buttons: {
            cancel: 'Cancelar',
            aceptar: {
                text: "Aceptar",
                value: true,
            }
        }
    })
        .then((eliminar) => {
            if (eliminar) {
                Get(url, data)
                    .then(function (data) {
                        let json = JSON.parse(data);
                        if (json.status === "OK") {
                            let item = document.getElementById(json.id);
                            item.parentNode.removeChild(item);
                        } else {
                            toast.show({
                                classNameToast: 'danger',
                                textBody: json.message,
                                titleText: "Aviso",
                                subtitleText: ""
                            });
                        }
                    })
                    .catch(function (error) {
                        toast.show({
                            classNameToast: 'danger',
                            textBody: error,
                            titleText: "Aviso",
                            subtitleText: ""
                        });
                    });
            } else {

            }
        });
}