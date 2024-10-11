let getGrupos = (input) => {
    let url = input.dataset.url;
    let data = {
        id_materia: input.value,
        gestion: document.getElementById('gestion').value,
        periodo: document.getElementById('periodo').value,
        id_grupo: document.getElementById('id_grupo').value
    }
    Get(url, data)
        .then(function (result) {
            document.getElementById('id_grupo').innerHTML = result;
        })
        .catch(function (error) {
            console.log(error);
        });
};