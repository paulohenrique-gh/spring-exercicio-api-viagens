package validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.apiviagens.annotations.TripDateConstraint;
import org.example.apiviagens.models.Trip;

public class TripDateValidator implements ConstraintValidator<TripDateConstraint, Trip> {
    @Override
    public boolean isValid(Trip trip, ConstraintValidatorContext context) {
        if (trip.getStartDate() == null || trip.getEndDate() == null) {
            return true;
        }

        return trip.getStartDate().isBefore(trip.getEndDate());
    }
}
