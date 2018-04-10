package pl.pollub.cs.pentagoncafe.flare.DTO.response;

import lombok.*;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Province;

import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class EventResDTO {
    private String title;
    private String description;
    private int duration;
    private Instant dateOfApproval;

    private boolean isApproved;
    private boolean onlyForRegisteredUsers;

    private String address_town;
    private String address_zipcode;
    private String address_street;
    private Province address_province;
    private String address_blockNumber;
    private String address_houseNumber;
    private String address_additionalInformation;

}
