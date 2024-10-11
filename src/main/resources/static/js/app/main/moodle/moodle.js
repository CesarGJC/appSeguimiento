
const toast = new ToastModal(window.parent.document.getElementById('toast'), {});
let modal = new bootstrap.Modal(document.getElementById('anuncio'), {
    keyboard: false
})

function rankinguso() {
    let html = '<div class="row"><div class="col"><H1>RANKING DE USO DE PLATAFORMA</H1></div></div>' + contenidohtml;
    agregarContenido(html);
    modal.show();
}

function alertMessage(text, className) {
    toast.show({
        classNameToast: className,
        textBody: text,
        titleText: "Aviso",
        subtitleText: ""
    });
}

function rankingcontenido() {
    let html = '<div class="row"><div class="col"><H1>RANKING DE CONTENIDO</H1></div></div>' + contenidohtmlrecursos;
    agregarContenido(html);
    modal.show();
}

function getDetalle(input) {
    let url = input.dataset.url;
    let data = {
        id_moodle_cursos: parseInt(input.dataset.id)
    };
    input.disabled = true;
    Get(url, data)
        .then(function (response) {
            document.getElementById('contenido').innerHTML = response;
            input.disabled = false;
            modal.show();
        })
        .catch(function (err) {
            console.log(err);
        })
}

function matricularDocente(input) {
    let url = input.dataset.url;
    let data = {
        idcurso: parseInt(input.dataset.id),
        username: input.dataset.username,
        idnumber: input.dataset.idnumber
    }
    input.disabled = true;
    Post(url, data)
        .then(function (json) {
            if (json.status === "OK") {
                alertMessage(json.message, 'success');
            } else {
                alertMessage(json.message, 'danger');
            }
            input.disabled = false;
        })
        .catch(function (err) {
            alertMessage(err, 'danger');
        })
}

function agregarContenido(html) {
    document.getElementById('contenido').innerHTML = '';
    let div = document.createElement('div');
    div.className = 'container';
    div.innerHTML = html;
    document.getElementById('contenido').appendChild(div);
}

let filtrarContenido = (input) => {
    let tabla = document.getElementById("tablagrupo");
    const filtro = input.value.toLowerCase();
    const filas = tabla.getElementsByTagName("tr");
    for (let i = 1; i < filas.length; i++) {
        const fila = filas[i];
        const celdas = fila.getElementsByTagName("td");
        let mostrarFila = false;
        for (let j = 0; j < celdas.length; j++) {
            let contenido = celdas[j];
            const textoCelda = contenido.textContent.toLowerCase();
            if (textoCelda.includes(filtro)) {
                mostrarFila = true;
                break;
            }
        }

        if (mostrarFila) {
            fila.style.display = "";
        } else {
            fila.style.display = "none";
        }
    }
}

function getDetalleNumeroContenidos(input) {
    let url = input.dataset.url;
    let data = {
        id_moodle_cursos: parseInt(input.dataset.id),
        nombres: input.dataset.nombres,
        sigla: input.dataset.sigla,
        materias: input.dataset.materias,
        grupo: input.dataset.grupo
    };
    input.disabled = true;
    Get(url, data)
        .then(function (response) {
            document.getElementById('contenido').innerHTML = response;
            input.disabled = false;
            modal.show();
        })
        .catch(function (err) {
            console.log(err);
        })
}

function getGrafico(datas, colors, labels, title, context) {
    let data = {
        labels: labels,
        datasets: [{label: title, data: datas, backgroundColor: colors, hoverOffset: 4}]
    };
    let options = {
        title: {display: true, text: title, position: 'top'},
        rotation: -0.7 * Math.PI
    };
    let pieChart = new Chart(context, {
        type: 'pie', data: data, options: options
    });
}