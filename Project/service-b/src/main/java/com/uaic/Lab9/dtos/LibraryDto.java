package com.uaic.Lab9.dtos;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class LibraryDto {
    @NotNull
    private String name;

    @NotNull
    private Integer budget;
}
