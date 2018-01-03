package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private String town;
    private String zipCode;
    private String street;
    private Province province;
    private String blockNumber;
    private String houseNumber;
    private String additionalInformation;
}
