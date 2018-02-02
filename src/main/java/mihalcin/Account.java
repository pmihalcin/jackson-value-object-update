package mihalcin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Account {

    private final Validity validity;

    @JsonCreator
    public Account(@JsonProperty(value = "validity", required = true) Validity validity) {
        this.validity = validity;
    }
}
