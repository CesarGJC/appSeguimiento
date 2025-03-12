function valueChanged(input) {
    let data = {
        id: parseInt(input.dataset.id),
        habilitado: input.checked
    }
    let url = input.dataset.url;
    Post(url, data).then(function (response) {
        if (!response.success) {
            input.checked = false;
        }
    }).catch(function (error) {
        input.checked = false;
    })
}

function filtrarTabla() {
    let input = document.getElementById("filtro");
    let filtro = input.value.toLowerCase();
    let unidadesDiv = document.querySelectorAll('.descripcion-unidad');
    unidadesDiv.forEach(function (item){
        let textoFila = item.textContent.toLowerCase();
        if (textoFila.includes(filtro)) {
            item.parentNode.parentNode.style.display = "";
        } else {
            item.parentNode.parentNode.style.display = "none";
        }
    })
}