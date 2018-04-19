package pl.pollub.cs.pentagoncafe.flare.testObjectFactories;

import pl.pollub.cs.pentagoncafe.flare.DTO.response.EventResDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Address;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;
import pl.pollub.cs.pentagoncafe.flare.domain.User;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.EventStatus;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Province;

import java.time.Instant;

public class TestEventFactory {

    public static Event createTestEvent() {
        return Event.builder()
                .title("test Event")
                .description("test event description")
                .duration(1)
                .dateOfCreation(Instant.EPOCH)
                .status(EventStatus.NEW)
                .banned(false)
                .onlyForRegisteredUsers(false)
                .imageURL("")
                .dateOfCreation(Instant.MAX)
                .address(Address.builder().town("lbn").build())
                .organizer(new User())
                .build();
    }

    public static EventResDTO createTestEventResponseDTO(){
        return EventResDTO.builder()
                .title("test Event")
                .description("test event description")
                .duration(1)
                .dateOfEndRegistration(Instant.EPOCH)
                .onlyForRegisteredUsers(false)
                .address_town("lbn")
                .address_zipcode("20-100")
                .address_province(Province.lubelskie)
                .address_blockNumber("3")
                .address_houseNumber("43")
                .address_additionalInformation("near crossroad")
                .build();
    }

}
