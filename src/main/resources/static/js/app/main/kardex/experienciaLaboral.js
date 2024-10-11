function cargarFormulario(input) {
    let url = input.dataset.url;
    let data = {
        id_experiencia_laboral: document.getElementById('id_experiencia_laboral').value,
        id_persona: document.getElementById('id_persona').value,
        id_persona_kardex: document.getElementById('id_persona_kardex').value,
        tipo_experiencia_laboral: input.value,
        nombrecompleto: document.getElementById('nombrecompleto').value,
        id_docente: document.getElementById('id_docente').value
    };
    Get(url, data)
        .then(function (response) {
            document.getElementById('formulario').innerHTML = response;
            if (input.value !== '')
                document.getElementById('btnEnviar').disabled = false;
            else
                document.getElementById('btnEnviar').disabled = true;
        })
        .catch(function (err) {
            document.getElementById('btnEnviar').disabled = true;
        });
}