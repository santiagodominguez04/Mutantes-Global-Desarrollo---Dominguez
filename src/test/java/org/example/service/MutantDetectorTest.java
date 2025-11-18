package org.example.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MutantDetectorTest {

    private MutantDetector mutantDetector;

    @BeforeEach
    void setUp() {
        mutantDetector = new MutantDetector();
    }

    @Test
    void testMutantHorizontalAndVertical() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA", // Horizontal C
                "TCACTG"
        };
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void testMutantDiagonal() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"
        };
        // Nota: Este ejemplo tiene múltiples secuencias, debería ser true
        assertTrue(mutantDetector.isMutant(dna));
    }

    @Test
    void testHuman() {
        String[] dna = {
                "ATGCGA",
                "CAGTGC",
                "TTATTT", // Solo 1 secuencia TTTT no es suficiente (necesita > 1)
                "AGACGG",
                "GCGTCA",
                "TCACTG"
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void testHumanNoSequences() {
        String[] dna = {
                "A", "B", "C", "D" // Muy corto, falso
        };
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void testNullDna() {
        assertFalse(mutantDetector.isMutant(null));
    }

    @Test
    void testEmptyDna() {
        assertFalse(mutantDetector.isMutant(new String[]{}));
    }

    @Test
    void testInvalidCharacters() {
        String[] dna = {"ATGC", "CAGT", "TTAT", "AGAX"}; // X es inválida
        assertFalse(mutantDetector.isMutant(dna));
    }

    @Test
    void testNxNValidation() {
        String[] dna = {"ATGC", "CAGT", "TTAT"}; // 3 filas, 4 columnas (No cuadrada)
        assertFalse(mutantDetector.isMutant(dna));
    }
}
