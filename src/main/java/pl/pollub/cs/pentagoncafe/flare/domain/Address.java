package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Province;

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

    public String toString(){
        StringBuilder addressStringBuilder =  new StringBuilder("ul.").append(street).append(" ");
        if(blockNumber!=null && !blockNumber.equals(""))
            addressStringBuilder.append(blockNumber).append(" m.");
        return addressStringBuilder.append(houseNumber).append(", ")
                .append(zipCode).append(" ")
                .append(town).toString();
    }
}
