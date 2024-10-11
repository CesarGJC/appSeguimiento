const toast = new ToastModal(window.parent.document.getElementById('toast'), {});
function alertMessage(text) {
    toast.show({
        classNameToast: 'danger',
        textBody: text,
        titleText: "Aviso",
        subtitleText: ""
    });
}
