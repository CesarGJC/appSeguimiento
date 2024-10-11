function cargarProgramas(input) {
    let parent = input.value;
    poblar(programas, parent, 'id_programa');
}

function poblar(list, parent, input) {
    let filter = list.filter(data => data.parent == parent);
    document.getElementById(input).innerHTML = '<option value=""> - Elija una opción -</option>';
    filter.forEach((item) => {
        document.getElementById(input).innerHTML += '<option value="' + item.value + '">' + item.display + '</option>';
    });
}
function cargar_nota(objeto) {
    let nota_entrada = objeto.value;
    let error_msg = document.getElementById("error_msg");
    if (nota_entrada > 100 || nota_entrada < 0 || isNaN(nota_entrada)) {
        error_msg.textContent = "El dato introducido: '" + objeto.value + "' no es válido. Introduzca un número del 0 al 100";
        objeto.value = "";
    } else {
        error_msg.textContent = ""; // Limpiar el mensaje de error si el valor es válido
    }
}