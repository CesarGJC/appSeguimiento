
let valores = [];

function validarValores(input) {
    if (validarPonderacion() && validarCantidad())
        document.getElementById('btnaceptar').disabled = false;
    else
        document.getElementById('btnaceptar').disabled = true;
};

function validarCantidad() {
    let numberInput = document.querySelectorAll('.cantidad');
    let continuar = 0;
    let total = 1;
    numberInput.forEach((element) => {
        if (!isNaN(element.value)) {
            if (parseInt(element.value) >= 1) {
                continuar += 1;
            }
        }
        total++;
    });
    return continuar == numberInput.length;
}

function guardarCambios() {
    valores = getTable();
    let data = {
        id_asignacion: document.getElementById('id_asignacion').value,
        id_programa: document.getElementById('id_programa').value,
        id_modelo_ahorro: document.getElementById('id_modelo_ahorro').value,
        id_tipo_evaluacion: document.getElementById('id_tipo_evaluacion').value,
        detalle: valores
    }
    document.getElementById('formDefincion').classList.add('invisible');
    document.getElementById('loading').classList.remove('invisible');
    Post('./confirmarDefinicion', data)
        .then(function (data) {
            if (data.status === "OK") {
                document.getElementById('formDefincion').innerHTML = getTableHtml();
                document.getElementById('formDefincion').classList.remove('invisible');
                document.getElementById('loading').classList.add('invisible');
            } else {
                toast.show({
                    classNameToast: 'danger',
                    textBody: data.message,
                    titleText: "Aviso",
                    subtitleText: ""
                });
                document.getElementById('formDefincion').classList.remove('invisible');
                document.getElementById('loading').classList.add('invisible');
            }
        })
        .catch(function (error) {
            toast.show({
                classNameToast: 'danger',
                textBody: "Problemas con el servidor",
                titleText: "Aviso",
                subtitleText: ""
            });
            document.getElementById('formDefincion').classList.remove('invisible');
            document.getElementById('loading').classList.add('invisible');
        });
}

function validarPonderacion() {
    let numberInput = document.querySelectorAll('.ponderacion');
    let suma = 0;
    numberInput.forEach((element) => {
        if (!isNaN(element.value))
            suma = suma + parseInt(element.value);
    });
    return suma == 100;
}

function getTableHtml() {
    let htmlTable = '';
    htmlTable += '<table id="tbl_definirnotas" class="table table-striped table-sm">';
    htmlTable += '<thead class="cf">   <tr> <th>Nro.</th> <th>TIPO NOTA</th> <th>CANTIDAD</th>  <th>PORCENTAJE</th> </tr> </thead><tbody>';
    for (let i = 0; i < valores.length; i++) {
        htmlTable += '<tr><td>' + valores[i].nro + '</td><td>' + valores[i].detalle + '</td><td>' + valores[i].cantidad + '</td><td>' + valores[i].ponderacion + '</td></tr>';
    }
    htmlTable += '</tbody> </table>';
    return htmlTable;
}

function getTable() {
    let table = document.getElementById("tbl_definirnotas");
    let valorCelda = '';
    let array = [];
    for (let i = 1; i < table.rows.length; i++) {
        let detalleDefinicionNotas = {
            nro: table.rows[i].cells[0].innerHTML,
            detalle: table.rows[i].cells[1].innerHTML,
            tipoNota: table.rows[i].cells[2].querySelector('.cantidad').dataset.tipo,
            cantidad: table.rows[i].cells[2].querySelector('.cantidad').value,
            ponderacion: table.rows[i].cells[3].querySelector('.ponderacion').value,

        }
        array.push(detalleDefinicionNotas);
    }
    return array;
}

document.oncontextmenu = function () {
    return false;
}