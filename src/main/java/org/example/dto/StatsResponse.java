package org.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatsResponse {

    @JsonProperty("count_mutant_dna") // Esto fuerza que el JSON salga con guiones bajos
    private long countMutantDna;

    @JsonProperty("count_human_dna")
    private long countHumanDna;

    private double ratio;
}