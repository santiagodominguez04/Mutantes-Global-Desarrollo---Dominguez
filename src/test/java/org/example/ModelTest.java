package org.example.service;

import org.example.dto.DnaRequest;
import org.example.dto.StatsResponse;
import org.example.entity.DnaRecord;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    @Test
    void testDnaRequest() {
        DnaRequest request = new DnaRequest();
        String[] dna = {"ATGC"};
        request.setDna(dna);
        assertArrayEquals(dna, request.getDna());
        assertNotNull(request.toString());

        DnaRequest request2 = new DnaRequest();
        request2.setDna(dna);
        assertEquals(request, request2); // Test equals/hashCode
        assertEquals(request.hashCode(), request2.hashCode());
    }

    @Test
    void testStatsResponse() {
        StatsResponse stats = new StatsResponse(40, 100, 0.4);
        assertEquals(40, stats.getCountMutantDna());
        assertEquals(100, stats.getCountHumanDna());
        assertEquals(0.4, stats.getRatio());

        stats.setCountMutantDna(50);
        assertEquals(50, stats.getCountMutantDna());
        assertNotNull(stats.toString());
    }

    @Test
    void testDnaRecord() {
        DnaRecord record = new DnaRecord("HASH123", true);
        record.setId(1L);

        assertEquals(1L, record.getId());
        assertEquals("HASH123", record.getDna());
        assertTrue(record.isMutant());

        record.setDna("HASH456");
        record.setMutant(false);
        assertFalse(record.isMutant());
        assertNotNull(record.toString());
    }
}