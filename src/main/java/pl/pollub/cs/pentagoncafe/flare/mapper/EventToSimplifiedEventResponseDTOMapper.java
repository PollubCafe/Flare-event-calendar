package pl.pollub.cs.pentagoncafe.flare.mapper;

import org.springframework.stereotype.Component;
import pl.pollub.cs.pentagoncafe.flare.DTO.response.SimplifiedEventResponseDTO;
import pl.pollub.cs.pentagoncafe.flare.domain.Address;
import pl.pollub.cs.pentagoncafe.flare.domain.Event;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Component
public class EventToSimplifiedEventResponseDTOMapper {
    public SimplifiedEventResponseDTO map(Event event){
        Date dateOfApproval = event.getDateOfEventApproval();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateOfApproval);
        return SimplifiedEventResponseDTO.builder().
                id(event.getId().toString()).
                title(event.getTitle()).
                description(event.getDescription() == null? "":event.getDescription()).
                address(this.parseAddress(event.getAddress())).
                linksCount(event.getLinks().size()).
                votesCount(event.getVotes().size()).
                yearOfEventApproval(calendar.get(Calendar.YEAR)).
                mouthOfEventApproval(new SimpleDateFormat("MMM").format(calendar.getTime())).
                dayOfEventApproval(calendar.get(Calendar.DAY_OF_MONTH)).
                hourOfEventApproval(this.parseDate(calendar)).
                onlyForRegisteredUsers(event.isOnlyForRegisteredUsers()).
                duration(event.getDuration()).
                imageURL(event.getImageURL()).
                build();
    }

    private String parseAddress(Address address){
        StringBuilder addressStringBuilder =  new StringBuilder("ul.").append(address.getStreet()).append(" ");
        if(address.getBlockNumber()!=null && !address.getBlockNumber().equals(""))
            addressStringBuilder.append(address.getBlockNumber()).append(" m.");
        return addressStringBuilder.append(address.getHouseNumber()).append(", ")
                .append(address.getZipCode()).append(" ")
                .append(address.getTown()).toString();
    }

    public String parseDate(Calendar calendar){
        int hourInt = convertToLocalTimeFromUTC(calendar.get(Calendar.HOUR_OF_DAY));
        String hour = hourInt<10?"0"+hourInt:String.valueOf(hourInt);
        int minutesInt = calendar.get(Calendar.MINUTE);
        String minutes = minutesInt<10?"0"+minutesInt:String.valueOf(minutesInt);
        return new StringBuilder().append(hour).append(":").append(minutes).toString();
    }

    private int convertToLocalTimeFromUTC(int hourUTC){
        return hourUTC - 1;
    }
}
