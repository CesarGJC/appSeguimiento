
let galeriaFotos = new GaleriaFotos(document.getElementById('main'));
let uploadDocument = new UploadDocument(document.getElementById('main'), {
    loadContent: () => {
        galeriaFotos = new GaleriaFotos(document.getElementById('main'));
    }
});