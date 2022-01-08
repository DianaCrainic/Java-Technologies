package com.uaic.Lab9.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "groups")
@NoArgsConstructor
@NamedQueries({
        @NamedQuery(name = "Group.getByName",
                query = "SELECT group FROM Group group WHERE name = :name")
})
@Schema(name = "Group")
public class Group implements Serializable {
    @Id
    @Basic(optional = false)
    @Column(name = "name")
    @Schema(required = true, description = "Name of the group")
    private String name;

    @JsonbTransient
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "groups")
    private List<User> users = new ArrayList<>();
}