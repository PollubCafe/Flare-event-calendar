package pl.pollub.cs.pentagoncafe.flare.mapper;

import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.EventResDTO;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;

@Component
public class EventMapper {
    public SimplifiedEventResponseDTO mapToResponseDTO(Event event){
       LocalDateTime timeOfApproval = LocalDateTime.ofInstant(event.getDateOfEndRegistration(), ZoneId.systemDefault());

        return SimplifiedEventResponseDTO.builder().
                id(event.getId().toString()).
                title(event.getTitle()).
                description(event.getDescription() == null? "":event.getDescription()).
                address(event.getAddress().toString()).
                linksCount(event.getLinks().size()).
                yearOfEventApproval(timeOfApproval.get(ChronoField.YEAR)).
                mouthOfEventApproval(timeOfApproval.get(ChronoField.MONTH_OF_YEAR)).
                dayOfEventApproval(timeOfApproval.get(ChronoField.DAY_OF_MONTH)).
                hourOfEventApproval(this.parseDateToHoursWithMinutes(timeOfApproval)).
                onlyForRegisteredUsers(event.isOnlyForRegisteredUsers()).
                duration(event.getDuration()).
                imageURL(event.getImageURL()).
                build();
    }

    public EventResDTO mapToResDTO(Event event) {

        return EventResDTO.builder()
                .title(event.getTitle())
                .description(event.getDescription() == null? "":event.getDescription())
                .duration(event.getDuration())
                .dateOfApproval(event.getDateOfEventApproval())
                .isApproved(event.isApproved())
                .onlyForRegisteredUsers(event.isOnlyForRegisteredUsers())
                .address_town(event.getAddress().getTown())
                .address_zipcode(event.getAddress().getZipCode())
                .address_street(event.getAddress().getStreet())
                .address_province(event.getAddress().getProvince())
                .address_blockNumber(event.getAddress().getBlockNumber())
                .address_houseNumber(event.getAddress().getHouseNumber())
                .address_additionalInformation(event.getAddress().getAdditionalInformation() == null? "":event.getAddress().getAdditionalInformation())
                .build();
    }

    private String parseDateToHoursWithMinutes(LocalDateTime timeOfApproval){
        int hour = timeOfApproval.get(ChronoField.HOUR_OF_DAY);
        String hourString = hour<10 ? "0"+hour : String.valueOf(hour);
        int minutes = timeOfApproval.get(ChronoField.MINUTE_OF_HOUR);
        String minutesString = minutes<10 ? "0"+minutes : String.valueOf(minutes);
        return hourString + ":" + minutesString;
    }
}
