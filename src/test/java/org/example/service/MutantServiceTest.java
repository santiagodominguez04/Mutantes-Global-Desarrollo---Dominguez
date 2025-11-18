package org.example.service;

import org.example.entity.DnaRecord;
import org.example.repository.DnaRecordRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MutantServiceTest {

    @Mock
    private DnaRecordRepository dnaRecordRepository;

    @Mock
    private MutantDetector mutantDetector;

    @InjectMocks
    private MutantService mutantService;

    @Test
    void testAnalyzeDna_FoundInCache() {
        // Simulamos que YA existe en la BD
        DnaRecord existingRecord = new DnaRecord();
        existingRecord.setMutant(true);
        when(dnaRecordRepository.findByDna(any())).thenReturn(Optional.of(existingRecord));

        // Ejecutamos
        boolean isMutant = mutantService.analyzeDna(new String[]{"ATGC"});

        // Verificamos
        assertTrue(isMutant);
        // Aseguramos que NO llamó al detector (ahorro de recursos)
        verify(mutantDetector, never()).isMutant(any());
        // Aseguramos que NO guardó de nuevo
        verify(dnaRecordRepository, never()).save(any());
    }

    @Test
    void testAnalyzeDna_NewRecord() {
        // Simulamos que NO existe en BD
        when(dnaRecordRepository.findByDna(any())).thenReturn(Optional.empty());
        // Simulamos que el detector dice "es mutante"
        when(mutantDetector.isMutant(any())).thenReturn(true);

        boolean isMutant = mutantService.analyzeDna(new String[]{"ATGC"});

        assertTrue(isMutant);
        // Esta vez SI debió guardar
        verify(dnaRecordRepository, times(1)).save(any());
    }
}
