package com.uaic.lab7.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDocumentDto {
    @NotNull
    private String name;

    @NotNull
    private byte[] content;

    @NotNull
    private Integer authorId;
}
