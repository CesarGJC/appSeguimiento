let toast = new ToastModal(document.getElementById('errortoast'), {});
let modal = new bootstrap.Modal(document.getElementById("vistaprevia"));

function vistapreviadocumentos(input) {
    let data = {
        id: input.dataset.id,
        tabla: input.dataset.tabla
    };
    Get(input.dataset.url, data)
        .then(function (data) {
            let json = JSON.parse(data);
            if (json.status === 'OK') {
                if (json.type === 'pdf') {
                    document.getElementById('imgvistaprevia').innerHTML = '<iframe  src="' + json.base64 + '" width=100% height=600></iframe>';
                } else {
                    document.getElementById('imgvistaprevia').innerHTML = '<img src="' + json.base64 + '" class="img-fluid" alt="Responsive image">';
                }
                modal.show();
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
}

function vistaprevia(input) {
    let data = {
        id_persona: input.dataset.persona,
        columna: input.dataset.columna,
        tabla: input.dataset.tabla
    };
    Get(input.dataset.url, data)
        .then(function (data) {
            let json = JSON.parse(data);
            if (json.status === 'OK') {
                if (json.type === 'pdf') {
                    document.getElementById('imgvistaprevia').innerHTML = '<iframe  src="' + json.base64 + '" width=100% height=600></iframe>';
                } else {
                    document.getElementById('imgvistaprevia').innerHTML = '<img src="' + json.base64 + '" class="img-fluid" alt="Responsive image">';
                }
                modal.show();
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
}