function cargarMateriasPorPlan(input) {
    let url = input.dataset.url;
    let data = {
        id_prg_plan: parseInt(input.value)
    };
    Get(url, data)
        .then(function (response) {
            document.getElementById('id_materia').innerHTML = response;
            const choices = new Choices('.select', {
                allowHTML: true,
                placeholderValue: '',
                searchPlaceholderValue: 'Buscar y seleccionar elementos',
                itemSelectText: 'Seleccionar elemento'
            });
        })
        .catch(function (err) {
            console.log(err);
        });
}
function cargarGrupos(input) {
    let url = input.dataset.url;
    let data = {
        id_tipo_evaluacion: document.getElementById('id_tipo_evaluacion').value,
        gestion: document.getElementById('gestion').value,
        periodo: document.getElementById('periodo').value,
        id_materia: parseInt(input.value)
    };
    Get(url, data)
        .then(function (response) {
            document.getElementById('id_grupo').innerHTML = response;
        })
        .catch(function (err) {
            console.log(err);
        });
}