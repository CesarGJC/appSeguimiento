
document.addEventListener("keydown", function (event) {
    if (event.key === "F12" || (event.ctrlKey && event.shiftKey && event.key === "I")) {
        event.preventDefault();
    }
});

document.addEventListener("contextmenu", function (event) {
    event.preventDefault();
});