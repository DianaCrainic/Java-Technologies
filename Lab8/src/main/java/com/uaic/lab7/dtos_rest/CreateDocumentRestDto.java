package com.uaic.lab7.dtos_rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDocumentRestDto {
    @NotNull
    private String name;

    @NotNull
    private String content;

    @NotNull
    private Integer authorId;
}
