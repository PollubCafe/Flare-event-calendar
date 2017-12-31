package pl.pollub.cs.pentagoncafe.flare.DTO;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class EventResponseDTO {
    private String id;
    private String creatorName;
    private String title;
    private String description;
    private String status;
    private String town;
    private int zipCode;
    private String street;
    private int block;
    private int houseNumber;
    private int week;
    private int year;
    private int duration;
    private Date date;
    private boolean onlyUser;
    private List<String> contestantsNames;
}
