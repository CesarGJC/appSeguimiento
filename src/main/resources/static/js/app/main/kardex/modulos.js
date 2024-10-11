let modal = new bootstrap.Modal(document.getElementById("vistaPrevia"));
function vistapreviadocumentos(input) {
    let url = input.dataset.url;
    let data = {
        id: parseInt(input.dataset.id),
        tabla: input.dataset.tabla
    };
    modal.show();
    let html = '<div class="overlay"> <div class="m-loader mr-4"> <svg class="m-circular" viewBox="25 25 50 50"><circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="4" stroke-miterlimit="10"></circle></svg></div><h3 class="l-text">Loading</h3></div>';
    document.getElementById('imgvistaprevia').innerHTML = html;
    Get(url, data)
        .then(function (response) {
            let json = JSON.parse(response);
            if (json.status === 'OK') {
                if (json.type === 'pdf') {
                    document.getElementById('imgvistaprevia').innerHTML = '<iframe  src="' + json.base64 + '" width=100% height=600></iframe> ';
                } else {
                    document.getElementById('imgvistaprevia').innerHTML = '<img src="' + json.base64 + '" class="img-fluid" alt="Responsive image">';
                }
            } else {
                alertMessage(json.message, "danger");
            }
        })
        .catch(function (err) {
            alertMessage(err, "danger");
        });
}
function filtrarDocumento(input, name) {
    let contenedor = document.getElementById(name);
    let divs = contenedor.querySelectorAll(".flex-grow-1");
    let filter = input.value.toUpperCase();
    divs.forEach(function (data, item) {
        let txtValue = data.querySelector('.buscar').textContent || data.querySelector('.buscar').innerText;
        if (txtValue.toUpperCase().includes(filter)) {
            if (!document.getElementById(data.dataset.id).className.includes('d-flex')) {
                document.getElementById(data.dataset.id).classList.add('d-flex');
            }
            document.getElementById(data.dataset.id).style.display = "";
        } else {
            document.getElementById(data.dataset.id).classList.remove('d-flex');
            document.getElementById(data.dataset.id).style.display = "none";
        }
    })
}
function aprobarDocumento(input) {
    let url = input.dataset.url;
    let params = {
        id: parseInt(input.dataset.id),
        estado: input.checked
    };
    console.log(params);
    Post(url, params)
        .then(function (json) {
            if (json.status !== "OK") {
                input.checked = false;
                alertMessage(json.message, "danger");
            }
        })
        .catch(function (err) {
            alertMessage(err, "danger");
        });
}