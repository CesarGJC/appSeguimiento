
let loader = document.createElement("div");
loader.setAttribute("id", "loader");
document.querySelector('main').insertAdjacentElement("afterend", loader);
let loadercontent = new Loader(loader, {
    textAction: 'Enviando...',
    UrlImage: '/seguimiento/static/image/logominiatura.png'
});
document.querySelectorAll('a.enlace').forEach(function (enlace){
    enlace.addEventListener("click", function(event) {
        // Prevenir el comportamiento predeterminado del enlace (navegar a una nueva página)
        event.preventDefault();
        loadercontent.show();
        // Obtener el valor del atributo "href"
        let href = this.getAttribute("href");
        // Realizar la acción deseada con el valor de "href"
        window.location.href = href;
    });
});
function enviarsolicitud(form) {
    loadercontent.show();
    form.submit();
}