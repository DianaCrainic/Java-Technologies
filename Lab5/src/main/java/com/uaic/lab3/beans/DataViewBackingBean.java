package com.uaic.lab3.beans;

import com.uaic.lab3.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class DataViewBackingBean<T extends AbstractEntity> implements Serializable {
    @Getter
    @Setter
    protected T selectedEntity;
    protected List entities;

    public List getEntities() {
        return entities;
    }
}
