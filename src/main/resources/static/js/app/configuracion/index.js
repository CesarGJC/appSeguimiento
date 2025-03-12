let toastForm = new ToastModal(window.parent.document.getElementById("toast"), {});

function alertMessageForm(data) {
    toastForm.show({
        classNameToast: data.ok ? "success" : "danger",
        textBody: data.message,
        titleText: "Aviso",
        subtitleText: "",
    });
}

function cambiarPlanInstitucional(input) {
    let url = input.dataset.url;
    let data = {
        valor: input.value
    };
    Post(url, data).then(function (response) {
        if (response.success) {
            let options = '<option value="-1">Seleccionar</option>';
            let formularios = '';
            console.log(response.result);
            response.result.periodos.forEach(function (item) {
                if (parseInt(item.id) === response.result.id)
                    options += '<option value="' + item.id + '" selected>' + item.value + '</option>';
                else
                    options += '<option value="' + item.id + '">' + item.value + '</option>'
            });
            document.getElementById('planPeriodoGestion').innerHTML = options;
            response.result.formularios.forEach(function (item) {
                formularios += '<a href="' + input.dataset.context + '/configuracion/permisos?id=' + item.id_formulario + '"' +
                    'class="list-group-item list-group-item-action d-flex justify-content-between align-items-center w-100 mb-3 enlace" aria-current="true">' +
                    '<span> <strong>Apertura: </strong>' + item.codigo + ' - ' + item.apertura_programatica + '<br>' +
                    '<strong>Area Estrategica: </strong>' + item.area_estrategica + '</span><i class="fa fa-chevron-right"></i></a>';
            });
            document.getElementById('planesFormularios').innerHTML = formularios;
            alertMessageForm({ok: true, message: 'Se actualizo correctamente el plan'});
            submitFormSend.loadEvents();
        } else {
            alertMessageForm({ok: false, message: response.message});
        }
    }).catch(function (error) {
        alertMessageForm({ok: false, message: error});
    })
}

function cambiarPeriodoGestion(input) {
    let url = input.dataset.url;
    let data = {
        valor: input.value
    };
    Post(url, data).then(function (response) {
        if (response.success) {
        }
        alertMessageForm({
            ok: response.success,
            message: response.success ? 'Se actualizo correctamente' : response.message
        });
    }).catch(function (error) {
        alertMessageForm({ok: false, message: error});
    });
}