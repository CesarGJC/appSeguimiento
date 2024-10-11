function setLimpiar() {
    let celdillas = document.getElementsByName('celdilla');
    for (let i = 0; i < celdillas.length; i++)
        celdillas[i].className = '';
}
function setMarcarRequisitos(obj) {
    setLimpiar();
    obj.className = 'btn btn-primary btn-lg btn-block';
    for (let i = 0; i < vRequisitos.length; i++)
        if (vRequisitos[i][0] == obj.id)
            document.getElementById(vRequisitos[i][1]).className = 'btn btn-warning btn-lg btn-block';
}