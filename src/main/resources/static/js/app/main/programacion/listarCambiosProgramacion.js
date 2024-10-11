const checks = document.querySelectorAll('.check');
const tabla = document.querySelector(".filtro");
function seleccionarTodo(input) {
    checks.forEach(function (item, number) {
        item.checked = input.checked;
    });
}
function registrarFormulario(form) {
    if (isNullOrEmpty(document.getElementById('observacion').value)) {
        document.getElementById('observacion').classList.add("is-invalid");
        return;
    } else
        document.getElementById('observacion').classList.remove("is-invalid");
    if (isNullOrEmpty(document.getElementById('id_grupo').value)) {
        document.getElementById('id_grupo').classList.add("is-invalid");
        return;
    } else
        document.getElementById('id_grupo').classList.remove("is-invalid");
    if (notCheckSelect()) {
        alertMessage("Debe seleccionar por lo menos un estudiante");
        return;
    }
    enviarsolicitud(form);
}
function notCheckSelect() {
    let cantidad = 0;
    checks.forEach(function (value, item) {
        if (value.checked)
            cantidad++;
    });
    return cantidad === 0;
}
let filtrarContenido = (input) => {
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