class ListBox {
    #pluginName = 'bootstrapDualListbox';
    #isBuggyAndroid = /android/i.test(navigator.userAgent.toLowerCase());
    #defaults = {
        filterTextClear: 'Mostrar todo',
        filterPlaceHolder: 'Filtrar',
        moveSelectedLabel: 'Mover Seleccioado',
        moveAllLabel: 'Mover todo',
        removeSelectedLabel: 'Remover seleccionado',
        removeAllLabel: 'Remover todo',
        moveOnSelect: true,                                                                 // true/false (forced true on androids, see the comment later)
        moveOnDoubleClick: true,                                                            // true/false (forced false on androids, cause moveOnSelect is forced to true)
        preserveSelectionOnMove: false,                                                     // 'all' / 'moved' / false
        selectedListLabel: false,                                                           // 'string', false
        nonSelectedListLabel: false,                                                        // 'string', false
        helperSelectNamePostfix: '_helper',                                                 // 'string_of_postfix' / false
        selectorMinimalHeight: 100,
        showFilterInputs: true,                                                             // whether to show filter inputs
        nonSelectedFilter: '',                                                              // string, filter the non selected options
        selectedFilter: '',                                                                 // string, filter the selected options
        infoText: 'Mostrando todo {0}',                                                        // text when all options are visible / false for no info text
        infoTextFiltered: '<span class="badge badge-warning">Filtrado</span> {0} de {1}', // when not all of the options are visible due to the filter
        infoTextEmpty: 'Lista vacia',                                                        // when there are no options present in the list
        filterOnValues: false,                                                              // filter by selector's values, boolean
        sortByInputOrder: false,
        eventMoveOverride: false,                                                           // boolean, allows user to unbind default event behaviour and run their own instead
        eventMoveAllOverride: false,                                                        // boolean, allows user to unbind default event behaviour and run their own instead
        eventRemoveOverride: false,                                                         // boolean, allows user to unbind default event behaviour and run their own instead
        eventRemoveAllOverride: false,                                                      // boolean, allows user to unbind default event behaviour and run their own instead
        btnClass: 'btn-primary',                                                  // sets the button style class for all the buttons
        btnMoveText: '&gt;',                                                                // string, sets the text for the "Move" button
        btnRemoveText: '&lt;',                                                              // string, sets the text for the "Remove" button
        btnMoveAllText: '&gt;&gt;',                                                         // string, sets the text for the "Move All" button
        btnRemoveAllText: '&lt;&lt;'                                                        // string, sets the text for the "Remove All" button
    };

    constructor(element, options) {
        this.element = element;
        options || (options = {});
        Object.extend(this.#defaults, options);
        this.settings = this.#defaults;
        this.init();
    }

    init() {
        this.container = document.createElement('div');
        this.container.className = 'bootstrap-duallistbox-container row';
        this.container.innerHTML = '' +
            '<div class="box1 col-md-6">' +
            ' <label></label>' +
            ' <div class="info-container">' +
            ' <span class="info"></span>' +
            ' <button type="button" class="clear1" style="float:right!important;"></button>' +
            ' </div>' +
            ' <input class="form-control filter" type="text">' +
            ' <div class="btn-group buttons">' +
            ' <button type="button" class="btn btn-primary moveall"></button>' +
            ' <button type="button" class="btn  btn-primary move"></button>' +
            ' </div>' +
            ' <select multiple="multiple"></select>' +
            ' </div>' +
            ' <div class="box2 col-md-6">' +
            ' <label></label>' +
            ' <div class="info-container">' +
            ' <span class="info"></span>' +
            ' <button type="button" class="clear2" style="float:right!important;"></button>' +
            ' </div>' +
            ' <input class="form-control filter" type="text">' +
            ' <div class="btn-group buttons">' +
            ' <button type="button" class="btn btn-primary remove"></button>' +
            ' <button type="button" class="btn btn-primary removeall"></button>' +
            ' </div>' +
            ' <select multiple="multiple"></select>' +
            ' </div>' +
            '</div>';
        this.element.parentNode.insertBefore(this.container, this.element);
        this.elements = {
            originalSelect: this.element,
            box1: this.container.querySelector('.box1'),
            box2: this.container.querySelector('.box2'),
            filterInput1: this.container.querySelector('.box1 .filter'),
            filterInput2: this.container.querySelector('.box2 .filter'),
            filterClear1: this.container.querySelector('.box1 .clear1'),
            filterClear2: this.container.querySelector('.box2 .clear2'),
            label1: this.container.querySelector('.box1 > label'),
            label2: this.container.querySelector('.box2 > label'),
            info1: this.container.querySelector('.box1 .info'),
            info2: this.container.querySelector('.box2 .info'),
            select1: this.container.querySelector('.box1 select'),
            select2: this.container.querySelector('.box2 select'),
            moveButton: this.container.querySelector('.box1 .move'),
            removeButton: this.container.querySelector('.box2 .remove'),
            moveAllButton: this.container.querySelector('.box1 .moveall'),
            removeAllButton: this.container.querySelector('.box2 .removeall'),
            form: this.container.querySelector('.box1 .filter').form
        };
        this.originalSelectName = this.element.getAttribute('name') || '';
        var select1Id = 'bootstrap-duallistbox-nonselected-list_' + this.originalSelectName,
            select2Id = 'bootstrap-duallistbox-selected-list_' + this.originalSelectName;
        this.elements.select1.setAttribute('id', select1Id);
        this.elements.select2.setAttribute('id', select2Id);
        this.elements.label1.setAttribute('for', select1Id);
        this.elements.label2.setAttribute('for', select1Id);

        this.selectedElements = 0;
        this.sortIndex = 0;
        this.elementCount = 0;
        this.setFilterTextClear(this.settings.filterTextClear);
        this.setFilterPlaceHolder(this.settings.filterPlaceHolder);
        this.setMoveSelectedLabel(this.settings.moveSelectedLabel);
        this.setMoveAllLabel(this.settings.moveAllLabel);
        this.setRemoveSelectedLabel(this.settings.removeSelectedLabel);
        this.setRemoveAllLabel(this.settings.removeAllLabel);
        this.setMoveOnSelect(this.settings.moveOnSelect);
        this.setMoveOnDoubleClick(this.settings.moveOnDoubleClick);
        this.setPreserveSelectionOnMove(this.settings.preserveSelectionOnMove);
        this.setSelectedListLabel(this.settings.selectedListLabel);
        this.setNonSelectedListLabel(this.settings.nonSelectedListLabel);
        this.setHelperSelectNamePostfix(this.settings.helperSelectNamePostfix);
        this.setSelectOrMinimalHeight(this.settings.selectorMinimalHeight);

        updateSelectionStates(this);

        this.setShowFilterInputs(this.settings.showFilterInputs);
        this.setNonSelectedFilter(this.settings.nonSelectedFilter);
        this.setSelectedFilter(this.settings.selectedFilter);
        this.setInfoText(this.settings.infoText);
        this.setInfoTextFiltered(this.settings.infoTextFiltered);
        this.setInfoTextEmpty(this.settings.infoTextEmpty);
        this.setFilterOnValues(this.settings.filterOnValues);
        this.setSortByInputOrder(this.settings.sortByInputOrder);
        this.setEventMoveOverride(this.settings.eventMoveOverride);
        this.setEventMoveAllOverride(this.settings.eventMoveAllOverride);
        this.setEventRemoveOverride(this.settings.eventRemoveOverride);
        this.setEventRemoveAllOverride(this.settings.eventRemoveAllOverride);
        this.setBtnClass(this.settings.btnClass);

        this.setBtnMoveText(this.settings.btnMoveText);
        this.setBtnRemoveText(this.settings.btnRemoveText);
        this.setBtnMoveAllText(this.settings.btnMoveAllText);
        this.setBtnRemoveAllText(this.settings.btnRemoveAllText);
        this.element.style.display = 'none';

        bindEvents(this);
        refreshSelects(this);

        return this.element;
    }

    setShowFilterInputs(value, refresh) {
        if (!value) {
            this.setNonSelectedFilter('');
            this.setSelectedFilter('');
            refreshSelects(this);
            this.elements.filterInput1.style.display = 'none';
            this.elements.filterInput2.style.display = 'none';
        } else {
            this.elements.filterInput1.style.display = 'inline';
            this.elements.filterInput2.style.display = 'inline';
        }
        this.settings.showFilterInputs = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setNonSelectedFilter(value, refresh) {
        if (this.settings.showFilterInputs) {
            this.settings.nonSelectedFilter = value;
            this.elements.filterInput1.value = value;
            if (refresh) {
                refreshSelects(this);
            }
            return this.element;
        }
    }

    setSelectedFilter(value, refresh) {
        if (this.settings.showFilterInputs) {
            this.settings.selectedFilter = value;
            this.elements.filterInput2.value = value;
            if (refresh) {
                refreshSelects(this);
            }
            return this.element;
        }
    }

    setInfoText(value, refresh) {
        this.settings.infoText = value;
        if (value) {
            this.elements.info1.style.display = 'inline';
            this.elements.info2.style.display = 'inline';
        } else {
            this.elements.info1.style.display = 'none';
            this.elements.info2.style.display = 'none';
        }
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setInfoTextFiltered(value, refresh) {
        this.settings.infoTextFiltered = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setInfoTextEmpty(value, refresh) {
        this.settings.infoTextEmpty = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setFilterOnValues(value, refresh) {
        this.settings.filterOnValues = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setSortByInputOrder(value, refresh) {
        this.settings.sortByInputOrder = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setEventMoveOverride(value, refresh) {
        this.settings.eventMoveOverride = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setEventMoveAllOverride(value, refresh) {
        this.settings.eventMoveAllOverride = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setEventRemoveOverride(value, refresh) {
        this.settings.eventRemoveOverride = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setEventRemoveAllOverride(value, refresh) {
        this.settings.eventRemoveAllOverride = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setBtnClass(value, refresh) {
        this.settings.btnClass = value;
        this.elements.moveButton.className = 'btn move ' + value;
        this.elements.removeButton.className = 'btn remove ' + value;
        this.elements.moveAllButton.className = 'btn moveall ' + value;
        this.elements.removeAllButton.className = 'btn removeall ' + value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setHelperSelectNamePostfix(value, refresh) {
        this.settings.helperSelectNamePostfix = value;
        if (value) {
            this.elements.select1.name = this.originalSelectName + value + '1';
            this.elements.select2.name = this.originalSelectName + value + '2';
        } else {
            this.elements.select1.removeAttribute('name');
            this.elements.select2.removeAttribute('name');
        }
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setSelectOrMinimalHeight(value, refresh) {
        this.settings.selectorMinimalHeight = value;
        var height = this.element.offsetHeight;
        if (this.element.offsetHeight < value) {
            height = value;
        }
        this.elements.select1.style.height = height + 'px';
        this.elements.select2.style.height = height + 'px';
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setSelectedListLabel(value, refresh) {
        this.settings.selectedListLabel = value;
        if (value) {
            this.elements.label2.style.display = 'block';
            this.elements.label2.innerHTML = value;
        } else {
            this.elements.label2.style.display = 'none';
            this.elements.label2.innerHTML = value;
        }
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setNonSelectedListLabel(value, refresh) {
        this.settings.nonSelectedListLabel = value;
        if (value) {
            this.elements.label1.style.display = 'block';
            this.elements.label1.innerHTML = value;
        } else {
            this.elements.label1.style.display = 'none';
            this.elements.label1.innerHTML = value;
        }
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setPreserveSelectionOnMove(value, refresh) {
        if (this.#isBuggyAndroid) {
            value = false;
        }
        this.settings.preserveSelectionOnMove = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setMoveOnDoubleClick(value, refresh) {
        if (this.#isBuggyAndroid) {
            value = false;
        }
        this.settings.moveOnDoubleClick = value;
        if (this.settings.moveOnDoubleClick) {
            this.container.classList.add('moveondoubleclick');
            var self = this;
            this.elements.select1.addEventListener('dblclick', function () {
                move(self);
            });
            this.elements.select2.addEventListener('dblclick', function () {
                remove(self);
            });
        } else {
            this.container.classList.remove('moveondoubleclick');
            this.elements.select1.removeEventListener('dblclick');
            this.elements.select2.removeEventListener('dblclick');
        }
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setMoveOnSelect(value, refresh) {
        if (this.#isBuggyAndroid) {
            value = true;
        }
        this.settings.moveOnSelect = value;
        if (this.settings.moveOnSelect) {
            this.container.classList.add('moveonselect');
            var self = this;
            this.elements.select1.addEventListener('change', function () {
                move(self);
            });
            this.elements.select2.addEventListener('change', function () {
                remove(self);
            });
            this.elements.moveButton.remove();
            this.elements.removeButton.remove();
        } else {
            this.container.classList.remove('moveonselect');
            if (!hasEventListener(this.elements.select1, 'change'))
                this.elements.select1.removeEventListener('change');

            if (!hasEventListener(this.elements.select2, 'change'))
                this.elements.select2.removeEventListener('change');

            this.elements.moveButton.insertAdjacentElement('afterend', this.elements.moveAllButton);
            this.elements.removeButton.insertAdjacentElement('beforebegin', this.elements.removeAllButton);
        }
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setRemoveAllLabel(value, refresh) {
        this.settings.removeAllLabel = value;
        this.elements.removeAllButton.setAttribute('title', value);
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setRemoveSelectedLabel(value, refresh) {
        this.settings.removeSelectedLabel = value;
        this.elements.removeButton.setAttribute('title', value);
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setBtnRemoveText(value, refresh) {
        this.settings.btnMoveText = value;
        this.elements.removeButton.innerHTML = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setBtnRemoveAllText(value, refresh) {
        this.settings.btnMoveText = value;
        this.elements.removeAllButton.innerHTML = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setBtnMoveText(value, refresh) {
        this.settings.btnMoveText = value;
        this.elements.moveButton.innerHTML = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setBtnMoveAllText(value, refresh) {
        this.settings.btnMoveText = value;
        this.elements.moveAllButton.innerHTML = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setMoveAllLabel(value, refresh) {
        this.settings.moveAllLabel = value;
        this.elements.moveAllButton.setAttribute('title', value);
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setMoveSelectedLabel(value, refresh) {
        this.settings.moveSelectedLabel = value;
        this.elements.moveButton.setAttribute('title', value);
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }

    setFilterTextClear(value, refresh) {
        this.settings.filterTextClear = value;
        this.elements.filterClear1.innerHTML = value;
        this.elements.filterClear2.innerHTML = value;
        if (refresh) {
            refreshSelects(dualListbox);
        }
        return this.element;
    }

    setFilterPlaceHolder(value, refresh) {
        this.settings.filterPlaceHolder = value;
        this.elements.filterInput1.placeholder = value;
        this.elements.filterInput2.placeholder = value;
        if (refresh) {
            refreshSelects(this);
        }
        return this.element;
    }
}

function updateSelectionStates(dualListbox) {
    dualListbox.element.querySelectorAll('option').forEach(function (item, index) {
        if (typeof (item.dataset.originalIndex) === 'undefined') {
            item.dataset.originalIndex = dualListbox.elementCount++;
        }
        if (typeof (item.dataset.selected) === 'undefined') {
            item.dataset.selected = false;
        }
    });
}

function refreshSelects(dualListbox) {
    dualListbox.selectedElements = 0;

    dualListbox.elements.select1.empty();
    dualListbox.elements.select2.empty();

    dualListbox.element.find('option').each(function (index, item) {
        var $item = $(item);
        if ($item.prop('selected')) {
            dualListbox.selectedElements++;
            dualListbox.elements.select2.append($item.clone(true).prop('selected', $item.data('_selected')));
        } else {
            dualListbox.elements.select1.append($item.clone(true).prop('selected', $item.data('_selected')));
        }
    });

    if (dualListbox.settings.showFilterInputs) {
        filter(dualListbox, 1);
        filter(dualListbox, 2);
    }
    refreshInfo(dualListbox);
}

function refreshInfo(dualListbox) {
    if (!dualListbox.settings.infoText) {
        return;
    }

    var visible1 = dualListbox.elements.select1.querySelectorAll('option').length,
        visible2 = dualListbox.elements.select2.querySelectorAll('option').length,
        all1 = dualListbox.element.querySelectorAll('option').length - dualListbox.selectedElements,
        all2 = dualListbox.selectedElements,
        content = '';

    if (all1 === 0) {
        content = dualListbox.settings.infoTextEmpty;
    } else if (visible1 === all1) {
        content = formatString(dualListbox.settings.infoText, [visible1, all1]);
    } else {
        content = formatString(dualListbox.settings.infoTextFiltered, [visible1, all1]);
    }

    dualListbox.elements.info1.innerHTML = content;
    dualListbox.elements.box1.classList.toggle('filtered', !(visible1 === all1 || all1 === 0));

    if (all2 === 0) {
        content = dualListbox.settings.infoTextEmpty;
    } else if (visible2 === all2) {
        content = formatString(dualListbox.settings.infoText, [visible2, all2]);
    } else {
        content = formatString(dualListbox.settings.infoTextFiltered, [visible2, all2]);
    }

    dualListbox.elements.info2.innerHTML = content;
    dualListbox.elements.box2.classList.toggle('filtered', !(visible2 === all2 || all2 === 0));
}

function formatString(s, args) {
    return s.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] !== 'undefined' ? args[number] : match;
    });
}

function bindEvents(dualListbox) {
    if (dualListbox.elements.form !== null) {
        dualListbox.elements.form.addEventListener('submit', function (e) {
            if (dualListbox.elements.filterInput1 === document.activeElement) {
                e.preventDefault();
                dualListbox.elements.filterInput1.blur();
            } else if (dualListbox.elements.filterInput2 === document.activeElement) {
                e.preventDefault();
                dualListbox.elements.filterInput2.blur();
            }
        });
    }
    dualListbox.element.addEventListener('bootstrapDualListbox.refresh', function (e, mustClearSelections) {
        dualListbox.refresh(mustClearSelections);
    });

    dualListbox.elements.filterClear1.addEventListener('click', function () {
        dualListbox.setNonSelectedFilter('', true);
    });

    dualListbox.elements.filterClear2.addEventListener('click', function () {
        dualListbox.setSelectedFilter('', true);
    });

    if (dualListbox.settings.eventMoveOverride === false) {
        dualListbox.elements.moveButton.addEventListener('click', function () {
            move(dualListbox);
        });
    }

    if (dualListbox.settings.eventMoveAllOverride === false) {
        dualListbox.elements.moveAllButton.addEventListener('click', function () {
            moveAll(dualListbox);
        });
    }

    if (dualListbox.settings.eventRemoveOverride === false) {
        dualListbox.elements.removeButton.addEventListener('click', function () {
            remove(dualListbox);
        });
    }

    if (dualListbox.settings.eventRemoveAllOverride === false) {
        dualListbox.elements.removeAllButton.addEventListener('click', function () {
            removeAll(dualListbox);
        });
    }

    dualListbox.elements.filterInput1.addEventListener('change', function () {
        filter(dualListbox, 1);
    });

    dualListbox.elements.filterInput2.addEventListener('change', function () {
        filter(dualListbox, 2);
    });

    dualListbox.elements.filterInput1.addEventListener('keyup', function () {
        filter(dualListbox, 1);
    });

    dualListbox.elements.filterInput2.addEventListener('keyup', function () {
        filter(dualListbox, 2);
    });
}

function refreshSelects(dualListbox) {
    dualListbox.selectedElements = 0;

    dualListbox.elements.select1.innerHTML = '';
    dualListbox.elements.select2.innerHTML = '';

    var options = dualListbox.element.querySelectorAll('option');
    for (var i = 0; i < options.length; i++) {
        var option = options[i];
        if (option.selected) {
            dualListbox.selectedElements++;
            var clone = option.cloneNode(true);
            clone.selected = option.dataset._selected === 'true';
            dualListbox.elements.select2.appendChild(clone);
        } else {
            var clone = option.cloneNode(true);
            clone.selected = option.dataset._selected === 'true';
            dualListbox.elements.select1.appendChild(clone);
        }
    }

    if (dualListbox.settings.showFilterInputs) {
        filter(dualListbox, 1);
        filter(dualListbox, 2);
    }
    refreshInfo(dualListbox);
}

function saveSelections(dualListbox, selectIndex) {
    var options = dualListbox.element.getElementsByTagName('option');
    var selectOptions = dualListbox.elements['select' + selectIndex].getElementsByTagName('option');
    for (var i = 0; i < selectOptions.length; i++) {
        var item = selectOptions[i];
        var index = item.dataset.originalIndex;
        options[index].dataset._selected = item.selected;
    }
}

function sortOptionsByInputOrder(select) {
    var selectopt = select.getElementsByTagName('option');

    Array.from(selectopt).sort(function (a, b) {
        var an = parseInt(a.getAttribute('data-sortindex')),
            bn = parseInt(b.getAttribute('data-sortindex'));

        if (an > bn) {
            return 1;
        } else if (an < bn) {
            return -1;
        } else {
            return 0;
        }
    }).forEach(function (item) {
        select.appendChild(item);
    });
}

function changeSelectionState(dualListbox, original_index, selected) {
    var options = dualListbox.element.querySelectorAll('option');
    options.forEach(function (item) {
        if (item.dataset.originalIndex == original_index) {
            item.selected = selected;
            if (selected) {
                item.dataset.sortindex = dualListbox.sortIndex;
                dualListbox.sortIndex++;
            } else {
                delete item.dataset.sortindex;
            }
        }
    });
}

function triggerChangeEvent(dualListbox) {
    dualListbox.element.dispatchEvent(new Event('change'));
}

function sortOptions(select, dualListbox) {
    Array.from(select.querySelectorAll('option')).sort(function (a, b) {
        return a.dataset.originalIndex - b.dataset.originalIndex;
    }).forEach(function (option) {
        select.appendChild(option);
    });

    // workaround for chromium bug: https://bugs.chromium.org/p/chromium/issues/detail?id=1072475
    refreshSelects(dualListbox);
}

function clearSelections(dualListbox) {
    dualListbox.elements.select1.querySelectorAll('option').forEach(function (item) {
        item.dataset._selected = false;
    });
}

function move(dualListbox) {

    if (dualListbox.settings.preserveSelectionOnMove === 'all' && !dualListbox.settings.moveOnSelect) {
        saveSelections(dualListbox, 1);
        saveSelections(dualListbox, 2);
    } else if (dualListbox.settings.preserveSelectionOnMove === 'moved' && !dualListbox.settings.moveOnSelect) {
        saveSelections(dualListbox, 1);
    }
    dualListbox.elements.select1.querySelectorAll('option:checked').forEach(function (item) {
        changeSelectionState(dualListbox, item.dataset.originalIndex, true);
    });

    refreshSelects(dualListbox);
    triggerChangeEvent(dualListbox);
    if (dualListbox.settings.sortByInputOrder) {
        sortOptionsByInputOrder(dualListbox.elements.select2);
    } else {
        sortOptions(dualListbox.elements.select2, dualListbox);
    }
}

function remove(dualListbox) {
    if (dualListbox.settings.preserveSelectionOnMove === 'all' && !dualListbox.settings.moveOnSelect) {
        saveSelections(dualListbox, 1);
        saveSelections(dualListbox, 2);
    } else if (dualListbox.settings.preserveSelectionOnMove === 'moved' && !dualListbox.settings.moveOnSelect) {
        saveSelections(dualListbox, 2);
    }

    dualListbox.elements.select2.querySelectorAll('option:checked').forEach(function (item) {
        if (!item.dataset.filtered2) {
            changeSelectionState(dualListbox, item.dataset.originalIndex, false);
        }
    });

    refreshSelects(dualListbox);
    triggerChangeEvent(dualListbox);
    sortOptions(dualListbox.elements.select1, dualListbox);
    if (dualListbox.settings.sortByInputOrder) {
        sortOptionsByInputOrder(dualListbox.elements.select2);
    }
}

function moveAll(dualListbox) {
    if (dualListbox.settings.preserveSelectionOnMove === 'all' && !dualListbox.settings.moveOnSelect) {
        saveSelections(dualListbox, 1);
        saveSelections(dualListbox, 2);
    } else if (dualListbox.settings.preserveSelectionOnMove === 'moved' && !dualListbox.settings.moveOnSelect) {
        saveSelections(dualListbox, 1);
    }

    dualListbox.element.querySelectorAll('option').forEach(function (item, index) {
        item.selected = true;
        item.dataset.sortindex = dualListbox.sortIndex;
        dualListbox.sortIndex++;
    });

    refreshSelects(dualListbox);
    triggerChangeEvent(dualListbox);
}

function remove(dualListbox) {
    if (dualListbox.settings.preserveSelectionOnMove === 'all' && !dualListbox.settings.moveOnSelect) {
        saveSelections(dualListbox, 1);
        saveSelections(dualListbox, 2);
    } else if (dualListbox.settings.preserveSelectionOnMove === 'moved' && !dualListbox.settings.moveOnSelect) {
        saveSelections(dualListbox, 2);
    }

    dualListbox.elements.select2.querySelectorAll('option:checked').forEach(function (item, index) {
        changeSelectionState(dualListbox, item.dataset.originalIndex, false);
    });

    refreshSelects(dualListbox);
    triggerChangeEvent(dualListbox);
    sortOptions(dualListbox.elements.select1, dualListbox);
    if (dualListbox.settings.sortByInputOrder) {
        sortOptionsByInputOrder(dualListbox.elements.select2);
    }
}

function removeAll(dualListbox) {
    if (dualListbox.settings.preserveSelectionOnMove === "all" &&
        !dualListbox.settings.moveOnSelect) {
        saveSelections(dualListbox, 1);
        saveSelections(dualListbox, 2);
    } else if (dualListbox.settings.preserveSelectionOnMove === "moved" &&
        !dualListbox.settings.moveOnSelect) {
        saveSelections(dualListbox, 2);
    }

    var options = dualListbox.element.getElementsByTagName("option");
    for (var i = 0; i < options.length; i++) {
        var item = options[i];
        item.selected = false;
        item.removeAttribute("data-sortindex");
    }

    refreshSelects(dualListbox);
    triggerChangeEvent(dualListbox);
}

function filter(dualListbox, selectIndex) {
    if (!dualListbox.settings.showFilterInputs) {
        return;
    }

    saveSelections(dualListbox, selectIndex);

    dualListbox.elements['select' + selectIndex].innerHTML = '';
    dualListbox.elements['select' + selectIndex].scrollTop = 0;
    var regex,
        allOptions = dualListbox.element.querySelectorAll('option'),
        options = dualListbox.element;

    if (selectIndex === 1) {
        options = Array.from(allOptions).filter((option) => !option.selected);
    } else {
        options = Array.from(options.querySelectorAll('option:checked'));
    }

    try {
        regex = new RegExp(dualListbox.elements['filterInput' + selectIndex].value.trim(), 'gi');
    } catch (e) {
        // a regex to match nothing
        regex = new RegExp('/a^/', 'gi');
    }

    options.forEach(function (item) {
        var $item = item,
            isFiltered = true;
        if (item.text.match(regex) || (dualListbox.settings.filterOnValues && $item.getAttribute('value').match(regex))) {
            isFiltered = false;
            dualListbox.elements['select' + selectIndex].appendChild($item.cloneNode(true)).selected = $item.dataset['_selected'] === 'true';
        }
        allOptions[item.dataset['originalIndex']].dataset['filtered' + selectIndex] = isFiltered;
    });

    refreshInfo(dualListbox);
}

Object.prototype.extend =
    Object.prototype.extend ||
    function (destination, source) {
        for (var property in source) destination[property] = source[property];
        return destination;
    };

function hasEventListener(element, eventType) {
    return ('on' + eventType) in element;
}