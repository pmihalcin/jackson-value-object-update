package mihalcin

import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import spock.lang.Specification

import java.time.LocalDate

import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS

class DeserializationSpec extends Specification {

    private ObjectMapper mapper = new ObjectMapper()

    def setup() {
        mapper.disable(WRITE_DATES_AS_TIMESTAMPS)
        mapper.registerModule(new JavaTimeModule())
    }

    def "deserialize Account with readValue(...) and .readerForUpdating(...).readValue(...)"() {
        when: "I deserialize using readValue(...)"
        mapper.readValue("{ \"validity\": { \"validFrom\": \"2018-02-01\", \"validTo\": \"2018-01-31\" } }", Account.class)

        then: "I get mapping exception"
        def e = thrown(JsonMappingException)
        e.cause.message == "Valid to can't be before valid from"

        when: "I deserialize using readerForUpdating(...).readvalue(...)"
        Account account = new Account(new Validity(LocalDate.now(), LocalDate.now().plusDays(1L)))
        mapper.readerForUpdating(account).readValue("\"validity\": { \"validFrom\": \"2018-02-01\", \"validTo\": \"2018-01-31\" }")

        then: "I get mapping exception"
        e = thrown(JsonMappingException)
        e.cause.message == "Valid to can't be before valid from"

    }

}
