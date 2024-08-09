package org.example.apiviagens.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.apiviagens.annotations.TripDateConstraint;

import java.time.LocalDate;

@Entity
@Table(name = "trip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TripDateConstraint // https://stackoverflow.com/questions/43785817/springboot-form-validation-for-ensuring-one-date-does-not-come-before-another
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    private String title;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne
    private Destination destination;
}
