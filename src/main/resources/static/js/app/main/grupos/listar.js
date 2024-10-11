function poblar(list, parent, input) {
    let filter = list.filter(data => data.parent == parent);
    document.getElementById(input).innerHTML = '<option value=""> - Elija una opción -</option>';
    filter.forEach((item) => {
        document.getElementById(input).innerHTML += '<option value="' + item.value + '">' + item.display + '</option>';
    });
}