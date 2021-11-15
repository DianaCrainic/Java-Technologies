package com.uaic.lab3.beans;

import com.uaic.lab3.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public class DataViewBackingBean<T extends AbstractEntity<ID>, ID> implements Serializable {
    @Getter
    @Setter
    protected T selectedEntity;
    protected List<T> entities;

    public List<T> getEntities() throws SQLException {
        return entities;
    }
}
