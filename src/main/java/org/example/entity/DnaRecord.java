package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class DnaRecord implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String dna; // Guardaremos el Hash aquí para identificarlo único

    private boolean isMutant;

    // Constructor helper
    public DnaRecord(String dna, boolean isMutant) {
        this.dna = dna;
        this.isMutant = isMutant;
    }
}