package com.moxos.uab.domain.entity.siiga;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListViewItem {
    private String id;
    private String value;
    private Boolean select;
    private String format;

    public ListViewItem() {
    }

    public ListViewItem(String id, String value) {
        this.id = id;
        this.value = value;
    }
}
