package org.example.service;

import org.example.entity.DnaRecord;
import org.example.repository.DnaRecordRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class MutantService {

    private final MutantDetector mutantDetector;
    private final DnaRecordRepository dnaRecordRepository;

    public MutantService(MutantDetector mutantDetector, DnaRecordRepository dnaRecordRepository) {
        this.mutantDetector = mutantDetector;
        this.dnaRecordRepository = dnaRecordRepository;
    }

    public boolean analyzeDna(String[] dna) {

        String dnaHash = Arrays.toString(dna);


        Optional<DnaRecord> existingRecord = dnaRecordRepository.findByDna(dnaHash);
        if (existingRecord.isPresent()) {

            return existingRecord.get().isMutant();
        }


        boolean isMutant = mutantDetector.isMutant(dna);


        DnaRecord dnaRecord = new DnaRecord(dnaHash, isMutant);
        dnaRecordRepository.save(dnaRecord);

        return isMutant;
    }
}