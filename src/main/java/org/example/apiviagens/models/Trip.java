package org.example.apiviagens.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "trip")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    //https://stackoverflow.com/questions/66374404/spring-boot-how-to-validate-fields-that-depend-on-each-other
    @AssertTrue(message = "Start date must be before end date")
    @JsonIgnore
    public boolean isStartDateBeforeEndDate() {
        if (startDate == null || endDate == null) {
            return true;
        }

        return startDate.isBefore(endDate);
    }
}
