package com.uaic.Lab9.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDto {
    @NotNull
    private String title;

    @NotNull
    private Integer review;

    @NotNull
    private Integer price;

    @NotNull
    private Integer libraryId;
//        private LibraryDto library;
}
