var toast = new ToastModal(document.getElementById('toast'));
document.querySelectorAll('.treeview-menu a.treeview-item').forEach(function (link) {
    link.addEventListener('click', function (e) {
        let ruta = link.getAttribute("data-url");
        if (navigator.onLine) {
            let ruta = link.getAttribute("data-url");
            document.getElementById('marco').setAttribute('src', ruta);
        } else {
            let today = new Date();
            let now = today.toLocaleString();
            toast.show({
                classNameToast: "danger",
                textBody: "No existe conexion, revise su red o su conexion a internet",
                titleText: "Notificacion",
                subtitleText: now
            });
        }
        return true;
    });
});
window.addEventListener('online', function () {
    document.getElementById("status").classList.add("text-success");
    document.getElementById("status").classList.remove("text-danger");
    localStorage.setItem("estaConectado", true);
    let today = new Date();
    let now = today.toLocaleString();
    toast.show({
        classNameToast: "success",
        textBody: "conectado..",
        titleText: "Notificacion",
        subtitleText: now
    });
}, false);
window.addEventListener('offline', function () {
    localStorage.setItem("estaConectado", false);
    document.getElementById("status").classList.add("text-danger");
    document.getElementById("status").classList.remove("text-success");
    let today = new Date();
    let now = today.toLocaleString();
    toast.show({
        classNameToast: "danger",
        textBody: "No existe conexion, revise su red o su conexion a internet",
        titleText: "Notificacion",
        subtitleText: now
    });
}, false);
window.addEventListener('beforeunload', function (event) {
    navigator.sendBeacon('/logout', '');
});
document.oncontextmenu = function () {
    return false;
}
document.addEventListener("DOMContentLoaded", function () {
    IniciarCuerpo();
});

function IniciarCuerpo() {
    let ruta = "./main";
    document.getElementById('marco').setAttribute('src', ruta);
}

function IrPerfil() {
    let ruta = "./verificarYObtenerKardex";
    document.getElementById('marco').setAttribute('src', ruta);
}