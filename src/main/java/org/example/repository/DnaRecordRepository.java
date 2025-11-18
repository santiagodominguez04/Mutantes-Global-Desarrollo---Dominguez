package org.example.repository;

import org.example.entity.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaRecordRepository extends JpaRepository<DnaRecord, Long> {

    // Busca por el Hash del ADN
    Optional<DnaRecord> findByDna(String dna);

    // Cuenta cuántos mutantes o humanos hay (para las estadísticas)
    long countByIsMutant(boolean isMutant);
}