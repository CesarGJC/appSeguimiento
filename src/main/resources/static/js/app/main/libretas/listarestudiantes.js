
document.getElementById("id_tipo_nota_s").addEventListener("change", function () {
    let tipoevaluacion = document.getElementById("id_tipo_nota_s").value;
    if (tipoevaluacion == '') {
        document.getElementById("contenido").classList.add("invisible");
        document.getElementById("alert").classList.remove("invisible");
        document.getElementById("alert").classList.add("visible");
        document.getElementById("alert").innerHTML = "Debe seleccionar un tipo evaluacion";
    } else {
        loadercontent.show();
        document.getElementById("forma1").submit();
    }
});

window.onload = load();

function load() {
    getLocation();
}

let toast = new ToastModal(document.getElementById('errortoast'), {});

function getLocation() {
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition, function (error) {
            let message = 'Por seguridad al acceder al sistema académico - Moxos en su modulo Docente debe activar el acceso a su geolocalización; para detectar accesos indebidos. <a href=\'\'>para mas informacion haga click</a>';
            toast.show({
                classNameToast: 'danger',
                textBody: message,
                titleText: "Aviso",
                subtitleText: ""
            });
        }, {
            timeout: 30000,
            enableHighAccuracy: true,
            maximumAge: 75000
        });
    } else {
        let message = 'La geolocalizacion no esta soportado por el navegador. debe actualizar su navegador en su dispositivo. <a href=\'\'>para mas informacion haga click</a>';
        toast.show({
            classNameToast: 'danger',
            textBody: message,
            titleText: "Aviso",
            subtitleText: ""
        });
    }
}

function showPosition(position) {
    let x = "Latitude: " + position.coords.latitude + " - Longitude: " + position.coords.longitude;
    document.getElementById('ubicacion').value = x;
}
document.oncontextmenu = function () {
    return false;
}