package com.duoc.biblioteca.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @NotNull(message="El campo ID es obligatorio")
    private Long id;
    @NotNull(message="El campo ISB es obligatorio")
    @NotBlank(message = "EL campo de isbn tiene que tener un valor distinto a vacio")
    private String isbn;
    private String title;
    private String editorial;
    @NotNull(message="El campo fecha es obligatorio")
    private Integer publishDate;
    private String author;
}
