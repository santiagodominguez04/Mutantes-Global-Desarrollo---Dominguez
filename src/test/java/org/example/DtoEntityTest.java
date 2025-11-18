package org.example;

import org.example.dto.DnaRequest;
import org.example.dto.StatsResponse;
import org.example.entity.DnaRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DtoEntityTest {

    @Test
    void testDnaRequest() {
        // 1. Constructor vacío
        DnaRequest request1 = new DnaRequest();
        request1.setDna(new String[]{"A", "B"});

        // 2. Constructor con argumentos (si Lombok lo generó, si no, setters)
        DnaRequest request2 = new DnaRequest();
        request2.setDna(new String[]{"A", "B"});

        // 3. Getters
        assertArrayEquals(new String[]{"A", "B"}, request1.getDna());

        // 4. Equals & HashCode
        assertEquals(request1, request2);
        assertEquals(request1.hashCode(), request2.hashCode());
        assertNotEquals(request1, new Object());

        // 5. ToString
        assertNotNull(request1.toString());
    }

    @Test
    void testStatsResponse() {
        // 1. Constructor lleno (@AllArgsConstructor)
        StatsResponse stats1 = new StatsResponse(100L, 200L, 0.5);

        // 2. Getters
        assertEquals(100L, stats1.getCountMutantDna());
        assertEquals(200L, stats1.getCountHumanDna());
        assertEquals(0.5, stats1.getRatio());

        // 3. Setters
        stats1.setCountMutantDna(101L);
        stats1.setCountHumanDna(201L);
        stats1.setRatio(0.6);

        // 4. Equals & HashCode (con otro objeto igual)
        StatsResponse stats2 = new StatsResponse(101L, 201L, 0.6);
        assertEquals(stats1, stats2);
        assertEquals(stats1.hashCode(), stats2.hashCode());

        // 5. ToString
        assertNotNull(stats1.toString());
    }

    @Test
    void testDnaRecord() {
        // 1. Constructor vacío y Setters
        DnaRecord record1 = new DnaRecord();
        record1.setId(1L);
        record1.setDna("HASH_1");
        record1.setMutant(true);

        // 2. Constructor con argumentos (el que creamos manual)
        DnaRecord record2 = new DnaRecord("HASH_1", true);
        record2.setId(1L); // Simulamos que vienen de DB con el mismo ID

        // 3. Getters
        assertEquals(1L, record1.getId());
        assertEquals("HASH_1", record1.getDna());
        assertTrue(record1.isMutant());

        // 4. Equals & HashCode
        assertEquals(record1, record2);
        assertEquals(record1.hashCode(), record2.hashCode());

        // 5. ToString
        assertNotNull(record1.toString());
    }
}
