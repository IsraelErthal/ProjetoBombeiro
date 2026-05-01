package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckoutDTO {

    @NotNull(message = "O id do posto é obrigatório")
    private Long postoId;

    @NotNull(message = "A foto é obrigatória")
    private MultipartFile foto;

    @NotNull(message = "Obrigatório")
    private int prevencoesMatutinas;

    @NotNull(message = "Obrigatório")
    private int aguasVivasMatutinas;

    @NotNull(message = "Obrigatório")
    private int prevencoesVespertinas;

    @NotNull(message = "Obrigatório")
    private int aguasVivasVespertinas;
}
