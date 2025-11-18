package org.example.dto;

import lombok.Data; // Lombok genera los Getters y Setters automáticamente

@Data
public class DnaRequest {
    private String[] dna;
}