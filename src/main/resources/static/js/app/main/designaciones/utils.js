let toast = new ToastModal(window.parent.document.getElementById('toast'), {});
let choiceFuncion;
document.addEventListener('DOMContentLoaded', function () {
    const choices = new Choices('.select', {
        allowHTML: true,
        placeholderValue: '',
        searchPlaceholderValue: 'Buscar y seleccionar elementos',
        itemSelectText: 'Seleccionar elemento'
    });
});

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
        alertMessage('No puede seleccionr el tipo de autoridad si selecciona que es una autoridad administrativa o Dedicacion exclusiva');
        input.value = 1;
        return;
    }
}
let cambiarEstadoDesignacion = (input) => {
    if (document.getElementById('autoridades').checked) {
        if (parseInt(input.value) === 1) {
            alertMessage('No puede seleccionr tiempo completo si es a autoridad o dedicacion exclusiva');
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
        alertMessage('No puede seleccionar funcion administrativa si es a autoridad o dedicacion exclusiva');
        choiceFuncion.setChoiceByValue('0');
        return;
    }
    if (parseInt(document.getElementById('id_tipo_asignacion').value) !== 1) {
        alertMessage('No puede seleccionar funcion administrativa si es tiempo horario o medio tiempo');
        choiceFuncion.setChoiceByValue('0');
        return;
    }
}