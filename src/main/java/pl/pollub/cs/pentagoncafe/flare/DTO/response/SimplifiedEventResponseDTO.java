package pl.pollub.cs.pentagoncafe.flare.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class EventResponseDTO {
    private String id;
    private String title;
    private String description;
    private String address;
    private String organizerName;
    private int linksNumber;
}
