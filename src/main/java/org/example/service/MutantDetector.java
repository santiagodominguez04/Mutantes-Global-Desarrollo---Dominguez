package org.example.service;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class MutantDetector {

    private static final int SEQUENCE_LENGTH = 4;
    // Validación rápida de caracteres permitidos usando Regex
    private static final Pattern VALID_DNA_PATTERN = Pattern.compile("^[ATCG]+$");

    public boolean isMutant(String[] dna) {
        // 1. Validaciones iniciales (Null, Vacío, Cuadrada, Caracteres)
        if (dna == null || dna.length == 0) return false;
        int n = dna.length;

        // Convertimos a char[][] para acceso rápido O(1) en lugar de charAt()
        char[][] matrix = new char[n][n];

        for (int i = 0; i < n; i++) {
            String row = dna[i];
            // Validar que sea NxN y solo tenga letras A,T,C,G
            if (row == null || row.length() != n || !VALID_DNA_PATTERN.matcher(row).matches()) {
                return false;
                // O lanzar excepción: throw new IllegalArgumentException("ADN Inválido");
            }
            matrix[i] = row.toCharArray();
        }

        int sequenceCount = 0;

        // 2. Recorremos la matriz buscando secuencias
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {

                // Solo buscamos si hay espacio suficiente para formar una secuencia de 4

                // Búsqueda Horizontal (->)
                if (col <= n - SEQUENCE_LENGTH) {
                    if (checkHorizontal(matrix, row, col)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true; // ¡EARLY TERMINATION!
                    }
                }

                // Búsqueda Vertical (v)
                if (row <= n - SEQUENCE_LENGTH) {
                    if (checkVertical(matrix, row, col)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true; // ¡EARLY TERMINATION!
                    }
                }

                // Búsqueda Diagonal Principal (\)
                if (row <= n - SEQUENCE_LENGTH && col <= n - SEQUENCE_LENGTH) {
                    if (checkDiagonal(matrix, row, col)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true; // ¡EARLY TERMINATION!
                    }
                }

                // Búsqueda Diagonal Invertida (/)
                if (row >= SEQUENCE_LENGTH - 1 && col <= n - SEQUENCE_LENGTH) {
                    if (checkAntiDiagonal(matrix, row, col)) {
                        sequenceCount++;
                        if (sequenceCount > 1) return true; // ¡EARLY TERMINATION!
                    }
                }
            }
        }
        return false; // Menos de 2 secuencias encontradas
    }

    private boolean checkHorizontal(char[][] matrix, int row, int col) {
        char base = matrix[row][col];
        return matrix[row][col + 1] == base &&
                matrix[row][col + 2] == base &&
                matrix[row][col + 3] == base;
    }

    private boolean checkVertical(char[][] matrix, int row, int col) {
        char base = matrix[row][col];
        return matrix[row + 1][col] == base &&
                matrix[row + 2][col] == base &&
                matrix[row + 3][col] == base;
    }

    private boolean checkDiagonal(char[][] matrix, int row, int col) {
        char base = matrix[row][col];
        return matrix[row + 1][col + 1] == base &&
                matrix[row + 2][col + 2] == base &&
                matrix[row + 3][col + 3] == base;
    }

    private boolean checkAntiDiagonal(char[][] matrix, int row, int col) {
        char base = matrix[row][col];
        return matrix[row - 1][col + 1] == base &&
                matrix[row - 2][col + 2] == base &&
                matrix[row - 3][col + 3] == base;
    }
}