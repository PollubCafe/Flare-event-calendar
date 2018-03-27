package pl.pollub.cs.pentagoncafe.flare.DTO.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class SimplifiedEventResponseDTO {
    private String id;
    private String title;
    private String description;
    private String address;
    private int linksCount;
    private int votesCount;
    private boolean onlyForRegisteredUsers;
    private int yearOfEventApproval;
    private int dayOfEventApproval;
    private int mouthOfEventApproval;
    private String hourOfEventApproval;
    private int duration;
    private String imageURL;
}
