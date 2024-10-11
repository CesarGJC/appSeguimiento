let btnflix = document.querySelectorAll("[data-toggle='flip']");
let loginbox = document.querySelector(".login-box");
const form = document.getElementById("forma");

for (let i = 0; i < btnflix.length; i++) {
    btnflix[i].addEventListener("click", function (event) {
        loginbox.classList.toggle("flipped");
    });
}
let getLocation = () => {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, function (error) {
            let message = 'Por seguridad al acceder al sistema académico - Moxos en su modulo Docente debe activar el acceso a su geolocalización; para detectar accesos indebidos. <a href=\'\'>para mas informacion haga click</a>';
            notificacion(message);
        }, {
            timeout: 30000,
            enableHighAccuracy: true,
            maximumAge: 75000
        });
    } else {
        let message = 'La geolocalizacion no esta soportado por el navegador. debe actualizar su navegador en su dispositivo. <a href=\'\'>para mas informacion haga click</a>';
        notificacion(message);
    }
}

function showPosition(position) {
    let x = "Latitude: " + position.coords.latitude + " - Longitude: " + position.coords.longitude;
    document.getElementById('ubicacion').value = x;
}

form.addEventListener("submit", (e) => {
    let str = document.getElementById('ubicacion').value;
    if (str === "nulo") {
        let message = 'Por seguridad al acceder al sistema académico - Moxos en su modulo Docente debe permitir el acceso a su geolocalización; en caso de detectarse accesos indebidos, esta medida de seguridad brindara información que ayudara a tomar los recaudos necesarios. <a href=\'\'>para mas informacion haga click</a>';
        notificacion(message);
        e.preventDefault();
    }
});

function notificacion(error) {
    document.getElementById('ubicacion').value = 'nulo';
    let container = document.getElementById('toastcontainer');
    container.innerHTML = '<div name="errortoastgeololizacion" class="toast" role="alert" aria-live="assertive" aria-atomic="true">'
        + '<div class="toast-header bg-danger text-white">'
        + '<i class="fa fa-bell" aria-hidden="true"></i>&nbsp;&nbsp;'
        + '<strong class="me-auto"> Notificacion</strong>'
        + '<button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>'
        + '</div>'
        + '<div class="toast-body">'
        + error
        + '</div>'
        + '</div>';
    let toast = container.querySelector('[name="errortoastgeololizacion"]');
    let toastBootstrap = bootstrap.Toast.getOrCreateInstance(toast);
    toastBootstrap.show();
}
document.oncontextmenu = function () {
    return false;
}