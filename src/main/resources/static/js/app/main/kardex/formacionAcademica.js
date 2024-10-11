const choices = new Choices('.select', {
    allowHTML: true,
    placeholderValue: '',
    searchPlaceholderValue: 'Buscar y seleccionar elementos',
    itemSelectText: 'Seleccionar elemento'
});

function cargarNivelEstudio(input) {
    let url = input.dataset.url;
    let data = {
        tipotitulo: input.value
    };
    Get(url, data)
        .then(function (response) {
            document.getElementById('id_nivelestudio').innerHTML = response;
        })
        .catch(function (err) {
            console.log(err);
        });
}

function habulitarOtraUniversidad(input) {
    if (input.value === "OTRO") {
        document.getElementById('expedido').removeAttribute('readonly');
        document.getElementById('expedido').value = '';
    } else {
        document.getElementById('expedido').setAttribute("readonly", '');
        document.getElementById('expedido').value = input.value;
    }
}