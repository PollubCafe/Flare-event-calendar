package pl.pollub.cs.pentagoncafe.flare.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class CreatedEventRequestDTO {
    @NonNull
    @Size(min = 1, max = 35, message="Title can has from 1 to 35 characters")
    private String title;
    @NonNull
    @Size(max = 200, message="Description can has only 200 characters")
    private String description;
    @Min(value = 1, message = "Event must have more than 1 hours of duration")
    @Max(value = 6, message = "Event can has only 6 hours of duration")
    private int duration;
    @NonNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = "Date of approval must be a future date")
    private Date dateOfEventApproval;
    private boolean onlyForRegisteredUsers;
    //address data
    @NonNull
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{1,39}$", message="Town name must start with big latter and has from 2 to 40 characters")
    private String address_town;
    @NonNull
    @Pattern(regexp = "^\\d{2}-\\d{3}$", message="Invalid zip code format")
    private String address_zipCode;
    @NonNull
    @Pattern(regexp = "^[0-9A-ZĄĆĘŁŃÓŚŹŻ][\\sA-ZĄĆĘŁŃÓŚŹŻ0-9a-ząćęłńóśźż. ]{1,79}$", message="Invalid street name")
    private String address_street;
    @NonNull
    @Pattern(regexp = "^(lubelskie|dolnośląskie|małopolskie|śląskie|zachodiopomorskie|wielkopolskie|opolskie|łódzkie|podlaskie)",message="Invalid province")
    private String address_province;
    @NonNull
    @Pattern(regexp = "^$|^\\d+/?\\d*[a-zA-Z]?(?<!/)$", message="Invalid block number")
    private String address_blockNumber;
    @NonNull
    @Pattern(regexp = "^\\d+/?\\d*[a-zA-Z]?(?<!/)$", message="Invalid house number")
    private String address_houseNumber;
    @NonNull
    @Size(max = 100, message="Additional information can has only 100 characters")
    private String address_additionalInformation;
}
