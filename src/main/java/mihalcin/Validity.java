package mihalcin;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Validity {
    public static final String VALID_FROM_CANT_BE_NULL = "Valid from can't be null";
    public static final String VALID_TO_CANT_BE_BEFORE_VALID_FROM = "Valid to can't be before valid from";

    private final LocalDate validFrom;
    private final LocalDate validTo;

    @JsonCreator
    public Validity(@JsonProperty(value = "validFrom", required = true) LocalDate validFrom,
                    @JsonProperty("validTo") LocalDate validTo) {
        checkValidity(validFrom, validTo);

        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    private void checkValidity(LocalDate validFrom, LocalDate validTo) {
        checkNotNull(validFrom, VALID_FROM_CANT_BE_NULL);
        if (validTo != null) {
            checkState(validFrom.isBefore(validTo) || validFrom.isEqual(validTo), VALID_TO_CANT_BE_BEFORE_VALID_FROM);
        }
    }

    public LocalDate getValidFrom() {
        return validFrom;
    }

    public LocalDate getValidTo() {
        return validTo;
    }
}
