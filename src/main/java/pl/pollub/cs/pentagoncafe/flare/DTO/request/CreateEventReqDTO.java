package pl.pollub.cs.pentagoncafe.flare.DTO.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
public class CreateEventReqDTO {
    @NotBlank
    @Pattern(regexp = "^.*\\S.*$", message="validation.event.title.pattern")
    @Size(min = 1, max = 35, message="validation.event.title.size")
    private String title;

    @NotNull
    @Size(max = 200, message="validation.event.description.size")
    private String description;

    @Min(value = 1, message = "validation.event.duration.min")
    @Max(value = 6, message = "validation.event.duration.max")
    private int duration;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Future(message = "validation.event.dateOfEventApproval.future")
    private Date dateOfEventApproval;

    private boolean onlyForRegisteredUsers;

    //address data
    @NotBlank
    @Pattern(regexp = "^[A-ZĄĆĘŁŃÓŚŹŻ][a-ząćęłńóśźż]{1,39}$",
            message="validation.address.town.pattern")
    private String address_town;

    @NotBlank
    @Pattern(regexp = "^\\d{2}-\\d{3}$", message="validation.address.zipCode.pattern")
    private String address_zipCode;

    @NotBlank
    @Pattern(regexp = "^[0-9A-ZĄĆĘŁŃÓŚŹŻ][\\sA-ZĄĆĘŁŃÓŚŹŻ0-9a-ząćęłńóśźż. ]{1,79}$",
            message="validation.address.street.pattern")
    private String address_street;

    @NotBlank
    @Pattern(regexp = "^(lubelskie|dolnośląskie|małopolskie|śląskie|zachodiopomorskie|wielkopolskie|opolskie|łódzkie|podlaskie)",
            message="validation.address.province.pattern")
    private String address_province;

    @NotNull
    @Pattern(regexp = "^$|^\\d+/?\\d*[a-zA-Z]?(?<!/)$", message="validation.address.blockNumber.pattern")
    private String address_blockNumber;

    @NotBlank
    @Pattern(regexp = "^\\d+/?\\d*[a-zA-Z]?(?<!/)$", message="validation.address.houseNumber.pattern")
    private String address_houseNumber;

    @NotNull
    @Size(max = 100, message="validation.address.additionalInformation.size")
    private String address_additionalInformation;
}
