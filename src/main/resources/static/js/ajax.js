function Post(url, data) {
    let init = {
        method: 'POST',
        body: JSON.stringify(data),
        headers: {'Content-Type': 'application/json', 'X-XSRF-TOKEN': getCSRFToken()}
    };
    let request = new Request(url, init);
    return new Promise(function (resolve, reject) {
        fetch(request)
            .then(response => {
                if (response.status === 200) {
                    return response.json();
                } else {
                    throw new Error('Something went wrong on api server!');
                }
            })
            .then(data => {
                resolve(data);
            }).catch(error => {
            reject(error);
        });
    });
}

function PostForm(url, formData) {
    return new Promise(function (resolve, reject) {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', url);
        xhr.setRequestHeader('Content-Type', 'multipart/form-data');
        xhr.setRequestHeader("X-CSRFToken", getCSRFToken()); // función para obtener el token CSRF
        xhr.onload = function () {
            if (xhr.status === 200 || xhr.status === 201) { // Si la solicitud se completó con éxito
                resolve(xhr.response);
            } else { // Si hubo algún error en la solicitud
                reject(xhr.statusText);
            }
        };
        xhr.onerror = function () { // Si hubo algún error en la solicitud
            reject("Error en la solicitud AJAX");
        };
        xhr.send(formData); // Enviamos el objeto FormData
    });
}

function getCSRFToken() {
    let csrfTokenElement = document.querySelector("meta[name='_csrf']").getAttribute("content");
    return csrfTokenElement;
}

function Get(url, data) {
    return new Promise(function (resolve, reject) {
        let params = '';
        for (const propiedad in data) {
            const valor = data[propiedad];
            if (params !== '')
                params += '&';
            params += propiedad + '=' + encodeURIComponent(valor);
        }
        let queryParams = '?' + params;
        let xhr = new XMLHttpRequest();
        xhr.open('GET', url + queryParams);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.onload = function () {
            if (xhr.status === 200) {
                resolve(xhr.response);
            } else {
                reject(xhr.statusText);
            }
        };
        xhr.onerror = function () {
            reject("Error en la solicitud AJAX");
        };
        xhr.send();
    });
}
function sendForm(name) {
    return new Promise(function (resolve, reject) {
        var formulario = document.getElementById(name);
        let url = formulario.getAttribute("action");
        let metodo = formulario.getAttribute("method");
        var datos = new FormData(formulario);
        var xhr = new XMLHttpRequest();
        xhr.open(metodo, url, true);
        xhr.onload = function () {
            if (xhr.status === 200) {
                // Éxito
                resolve(xhr.responseText);
            } else {
                // Error
                reject('Error al enviar el formulario');
            }
        };

        xhr.send(datos);
    });
}
function submitForm(id, multipart) {
    return new Promise(function (resolve, reject) {
        let formulario = document.getElementById(id);
        let url = formulario.getAttribute("action");
        let metodo = formulario.getAttribute("method");
        let formData = new FormData();
        for (let i = 0; i < formulario.elements.length; i++) {
            let elemento = formulario.elements[i];
            formData.append(elemento.name, elemento.value);
        }
        let xhr = new XMLHttpRequest();
        if (metodo.toUpperCase() === "GET") {
            let queryString = new URLSearchParams(formData).toString();
            url = formulario.action + "?" + queryString;
            xhr.open("GET", url);
            xhr.onload = function () {
                if (xhr.status === 200) {
                    resolve(xhr.response);
                } else {
                    reject(xhr.statusText);
                }
            };
            xhr.onerror = function () {
                reject("Error en la solicitud AJAX");
            };
            xhr.send();
        } else {
            xhr.open(metodo, url);
            if (multipart)
                xhr.setRequestHeader('Content-Type', 'multipart/form-data');
            xhr.setRequestHeader("X-CSRFToken", getCSRFToken());
            xhr.onload = function () {
                if (xhr.status === 200 || xhr.status === 201) { // Si la solicitud se completó con éxito
                    resolve(xhr.response);
                } else { // Si hubo algún error en la solicitud
                    reject(xhr.statusText);
                }
            };
            xhr.onerror = function () { // Si hubo algún error en la solicitud
                reject("Error en la solicitud AJAX");
            };
            xhr.send(formData);
        }

    });
}

function downloadFile(url) {
    let params = '';
    for (const propiedad in data) {
        const valor = data[propiedad];
        if (params !== '')
            params += '&';
        params += propiedad + '=' + encodeURIComponent(valor);
    }
    let queryParams = '?' + params;
    fetch(url + queryParams, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        responseType: 'blob'
    })
        .then(response => response.blob())
        .then(blob => {
            const url = window.URL.createObjectURL(blob);
            const a = document.createElement('a');
            a.href = url;
            a.download = 'archivo.txt';
            a.click();
            window.URL.revokeObjectURL(url);
        })
        .catch(error => {
            console.error('Error:', error);
        });
}