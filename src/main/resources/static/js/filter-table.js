class FilterComboBox {

    constructor(element) {
        this.element = element;
        this.#init();
    }

    #init() {
        this.element.selectedIndex = 0;
        const typeSelect = this.element.options[this.element.selectedIndex].dataset.type;
        this.#setValues(typeSelect);
        this.element.addEventListener('change', (event) => {
            const typeSelect = event.target.options[event.target.selectedIndex].dataset.type;
            this.#setValues(typeSelect);
        });
    }

    #setValues(typeSelect) {
        document.querySelector('[data-condition="true"]').innerHTML = optionsSelect(typeSelect);
        document.querySelector('[data-input="true"]').type = inputOptions(typeSelect);
        document.querySelector('[data-input="true"]').value = '';
    }
}

const inputOptions = (type) => {
    let html = '';
    switch (type) {
        case '0':
            html = 'text';
            break;
        case '1':
            html = 'number';
            break;
        case '2':
            html = 'date';
            break;
    }
    return html;
}
const optionsSelect = (type) => {
    let html = '';
    switch (type) {
        case '0':
            html = '<option value=0>IGUAL</option><option value=1>DIFERENTE</option><option value=2>CONTIENE</option>';
            break;
        case '1':
            html = '<option value=0>IGUAL</option><option value=1>DIFERENTE</option><option value=3>MAYOR</option><option value=4>MAYOR QUE</option><option value=5>MAYOR IGUAL QUE</option><option value=6>MENOR MENOR</option><option value=7>MENOR QUE</option><option value=8>MENOR IGUAL QUE</option>';
            break;
        case '2':
            html = '<option value=0>IGUAL</option><option value=1>DIFERENTE</option><option value=3>MAYOR</option><option value=4>MAYOR QUE</option><option value=5>MAYOR IGUAL QUE</option><option value=6>MENOR MENOR</option><option value=7>MENOR QUE</option><option value=8>MENOR IGUAL QUE</option>';
            break;
    }
    return html;
}