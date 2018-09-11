package mihalcin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    //    @JsonMerge(value = TRUE)
    private final Validity validity;

    @JsonCreator
    public Account(@JsonProperty(value = "validity", required = true) Validity validity) {
        this.validity = validity;
    }

    public Validity getValidity() {
        return validity;
    }
}
