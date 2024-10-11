const tabla = document.querySelector(".filtro");
let filtrarContenido = (input) => {
    const filtro = input.value.toLowerCase();
    const filas = tabla.getElementsByTagName("tr");
    for (let i = 1; i < filas.length; i++) {
        const fila = filas[i];
        const celdas = fila.getElementsByTagName("td");
        let mostrarFila = false;
        for (let j = 0; j < celdas.length; j++) {
            let contenido = celdas[j].querySelector('.contenido');
            let contenidoNombre = celdas[j].querySelector('.contenido-nombre');
            let content = contenidoNombre === null ? '' : contenidoNombre.textContent.toLowerCase();
            const textoCelda = contenido.textContent.toLowerCase() + ' ' + content;
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