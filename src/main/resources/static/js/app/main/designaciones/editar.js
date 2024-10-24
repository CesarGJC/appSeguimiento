let toast = new ToastModal(window.parent.document.getElementById('toast'), {});
let modal = new bootstrap.Modal(document.getElementById("designacionModal"));
let choiceFuncion;
document.addEventListener('DOMContentLoaded', function () {
    const choices = new Choices('.select', {
        allowHTML: true,
        placeholderValue: '',
        searchPlaceholderValue: 'Buscar y seleccionar elementos',
        itemSelectText: 'Seleccionar elemento'
    });
});

function cambiarEstadoDesignacionElemento(event, input) {
    event.preventDefault();
    let url = input.dataset.url;
    let htmlLoading = '<div id="preloader" class="overlay"><div class="m-loader mr-4"><svg class="m-circular" viewBox="25 25 50 50"> <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"/></svg></div><h3 class="l-text">Cargando</h3></div>';
    document.getElementById('formulario').insertAdjacentHTML('beforeend', htmlLoading);
    let data = {
        id_docente: parseInt(document.getElementById('id_docente').value),
        gestion: parseInt(document.getElementById('gestion').value),
        periodo: parseInt(document.getElementById('periodo').value),
        id_tipo_evaluacion: parseInt(document.getElementById('id_tipo_evaluacion').value),
        id_asignacion: parseInt(input.dataset.id_asignacion)
    };
    Get(url, data)
        .then(function (result) {
            document.getElementById('formularioDesignacion').innerHTML = result;
            modal.show();
            eliminarElementoPorId('preloader');
        })
        .catch(function (error) {

        });
}

function enviarSolicitudCreacion(name) {
    sendForm(name).then(function (result) {
        let json = JSON.parse(result);
        if (json.ok) {
            document.querySelector('[data-target="transcision' + json.id_asignacion + '"]').innerHTML = json.transcision ? 'SI' : 'NO';
            document.querySelector('[data-target="pago' + json.id_asignacion + '"]').innerHTML = json.pago ? 'SI' : 'NO';
            document.querySelector('[data-target="mostrar' + json.id_asignacion + '"]').innerHTML = json.mostrar ? 'SI' : 'NO';
            modal.hide();
        } else {
            modal.hide();
            alertMessage(json.message);
        }
    })
        .catch(function (err) {
            modal.hide();
            alertMessage(err);
        });
}

function registrarDesignacion(input) {
    let url = input.dataset.url;
    let htmlLoading = '<div id="preloader" class="overlay"><div class="m-loader mr-4"><svg class="m-circular" viewBox="25 25 50 50"> <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"/></svg></div><h3 class="l-text">Cargando</h3></div>';
    document.getElementById('formulario').insertAdjacentHTML('beforeend', htmlLoading);
    let data = {
        id_docente: parseInt(document.getElementById('id_docente').value),
        gestion: parseInt(document.getElementById('gestion').value),
        periodo: parseInt(document.getElementById('periodo').value),
        id_tipo_evaluacion: parseInt(document.getElementById('id_tipo_evaluacion').value),
        id_tipo_asignacion: parseInt(document.getElementById('id_tipo_asignacion').value),
        id_tipo_asignacion_ant: parseInt(document.getElementById('id_tipo_asignacion_ant').value),
        id_asignacion: parseInt(document.getElementById('id_asignacion').value),
        transcision: document.getElementById('transcision').checked,
        id_departamento: document.getElementById('id_departamento').value,
        mostrar: document.getElementById('mostrar').checked,
        edit: document.getElementById('edit').value
    };
    Get(url, data)
        .then(function (result) {
            let json = JSON.parse(result);
            if (json.status === 'OK') {
                enviarsolicitud(document.getElementById('model'));
            } else {
                eliminarElementoPorId('preloader');
                alertMessage(json.message);
            }
        })
        .catch(function (error) {

        });
}

function eliminarElementoPorId(id) {
    const elemento = document.getElementById(id);
    if (elemento) {
        elemento.remove();
    }
}

function eliminarElemento(name) {
    const div = document.getElementById(name);
    if (div.lastChild) {
        div.removeChild(div.lastChild);
    }
}

function iniciartutorial() {
    introJs().setOptions({
        nextLabel: "Siguiente",
        prevLabel: "Atras",
        skipLabel: "Salir",
        doneLabel: "Gracias",
        tooltipClass: "",
        exitOnOverlayClick: false,
        showStepNumbers: true
    }).start();
}

function alertMessage(text) {
    toast.show({
        classNameToast: 'danger',
        textBody: text,
        titleText: "Aviso",
        subtitleText: ""
    });
}

let registrarFormulario = (name) => {
    let htmlLoading = '<div class="overlay"><div class="m-loader mr-4"><svg class="m-circular" viewBox="25 25 50 50"> <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"/></svg></div><h3 class="l-text">Cargando</h3></div><br><br><br><br>';
    let rango = document.createRange();
    let fragmentoDOM = rango.createContextualFragment(htmlLoading);
    document.getElementById('formulario').appendChild(fragmentoDOM);
    sendForm(name)
        .then(function (result) {
            document.getElementById('formulario').innerHTML = result;
            document.getElementById('contenedor').classList.add("invisible");
        })
        .catch(function (error) {

        });
}

function cargarSelects(input) {
    choiceFuncion = new Choices(input, {
        allowHTML: true,
        placeholderValue: '',
        searchPlaceholderValue: 'Buscar y seleccionar elementos',
        itemSelectText: 'Seleccionar elemento'
    });
}

let seleccionarElemento = (input) => {
    let url = input.dataset.url;
    let htmlLoading = '<div class="overlay"><div class="m-loader mr-4"><svg class="m-circular" viewBox="25 25 50 50"> <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"/></svg></div><h3 class="l-text">Cargando</h3></div><br><br><br><br>';
    document.getElementById('formulario').innerHTML = htmlLoading;
    let data = {
        gestion: parseInt(input.dataset.gestion),
        periodo: parseInt(input.dataset.periodo),
        id_tipo_evaluacion: parseInt(input.dataset.evaluacion),
        id_docente: parseInt(input.value),
        id_dpto_grupo: parseInt(input.dataset.grupo),
        id_departamento: parseInt(input.dataset.departamento),
        id_programa: parseInt(input.dataset.programa),
        id_grupo: parseInt(input.dataset.idgrupo),
        id_materia: parseInt(input.dataset.materia),
        id_prg_plan: parseInt(input.dataset.id_prg_plan)
    };
    Get(url, data)
        .then(function (result) {
            document.getElementById('formulario').innerHTML = result;
            const element = document.getElementById('formulario').querySelector('.select');
            cargarSelects(element);
        })
        .catch(function (error) {

        });
}
let cambiarEstado = (input) => {
    if (input.checked) {
        document.getElementById('id_tipo_asignacion').value = 3;
        choiceFuncion.setChoiceByValue('0');
    } else {
        document.getElementById('id_dct_autoridades').value = 1;
    }
}
cambiarEstadoAutoridad = (input) => {
    if (!document.getElementById('autoridades').checked) {
        alertMessage('No puede seleccionar el tipo de autoridad si selecciona que es una autoridad administrativa o Dedicacion exclusiva');
        input.value = 1;
        return;
    }
}
let cambiarEstadoDesignacion = (input) => {
    if (document.getElementById('autoridades').checked) {
        if (parseInt(input.value) === 1) {
            alertMessage('No puede seleccionar tiempo completo si es una autoridad o dedicacion exclusiva');
            input.value = 3;
            return;
        }
    }
    if (parseInt(input.value) !== 1) {
        choiceFuncion.setChoiceByValue('0');
        return;
    }
}
let cambiarEstadoFuncion = (input) => {
    if (document.getElementById('autoridades').checked) {
        alertMessage('No puede seleccionar funcion administrativa si es una autoridad o dedicacion exclusiva');
        choiceFuncion.setChoiceByValue('0');
        return;
    }
    if (parseInt(document.getElementById('id_tipo_asignacion').value) !== 1) {
        alertMessage('No puede seleccionar funcion administrativa si es tiempo horario o medio tiempo');
        choiceFuncion.setChoiceByValue('0');
        return;
    }
}