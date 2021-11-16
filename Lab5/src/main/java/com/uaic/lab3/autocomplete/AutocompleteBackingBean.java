package com.uaic.lab3.autocomplete;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AutocompleteBackingBean implements Serializable {
    @Getter
    @Setter
    protected String txt;

    public List<String> completeText() {
        return new ArrayList<>();
    }
}
