let modal = new bootstrap.Modal(document.getElementById("buscarEstudiante"));

function abrirbuscador(input) {
    modal.show();
}

function buscarElemento(input) {
    let url = input.dataset.url;
    let data = {
        id_programa: parseInt(input.dataset.id_programa),
        buscar: document.getElementById('buscarElementos').value
    };
    input.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Buscar';
    input.disabled = true;
    Get(url, data)
        .then(function (response) {
            document.getElementById('listaTabla').innerHTML = response;
            input.innerHTML = '<i class="fa fa-fw fa-lg fa-search"></i> Buscar';
            input.disabled = false;
        }).catch(function (err) {
        console.log(err);
        input.innerHTML = '<i class="fa fa-fw fa-lg fa-search"></i> Buscar';
        input.disabled = false;
    });
}

function seleccionarfila(value) {
    let id = 'tdEstudiante' + value;
    let id_plan = document.getElementById(id).dataset.plan;
    let nombre_completo = document.getElementById(id).dataset.nombre;
    let id_estudiante = parseInt(document.getElementById(id).dataset.id);
    let url = document.getElementById(id).dataset.url;
    document.getElementById('listaTabla').innerHTML = '';
    document.getElementById('id_plan').value = id_plan;
    document.getElementById('nombreEstudiante').innerHTML = nombre_completo;
    document.getElementById('id_estudiante').value = id_estudiante;
    document.getElementById('nombre_completo').value = nombre_completo;
    let data = {
        id_programa: document.getElementById('id_programa').value,
        id_plan: id_plan
    }
    Get(url, data)
        .then(function (response) {
            document.getElementById('id_mencion').innerHTML = response;
        })
        .catch(function (err) {
            console.log(err);
        });
    modal.hide();
}