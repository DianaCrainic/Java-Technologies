package com.uaic.lab3.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "resources")
@NamedQueries({
        @NamedQuery(name = "Resource.getAll", query = "SELECT resource FROM Resource resource")
})
public class Resource extends AbstractEntity {
    @Basic(optional = false)
    @Column(name = "name")
    private String name;

    @Basic(optional = false)
    @Column(name = "quantity")
    private Integer quantity;
}
