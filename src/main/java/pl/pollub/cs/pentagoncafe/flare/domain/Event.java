package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Singular;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Document(collection = "Event")
@Data
@Builder
public class Event {
    @Id
    private ObjectId id;

    @DBRef(lazy = true)
    private User organizer;

    @Singular("links")
    @DBRef
    private List<Link> links;

    private Address address;
    @Singular("eventStatistic")
    private List<Term> eventStatistic;
    @Singular("participation")
    @DBRef
    private List<Participation> participation;

    private String title;
    private String description;
    private int duration;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateOfEventApproval;
    private boolean isApproved;
    private boolean onlyForRegisteredUsers;
    private String imageURL;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateOfCreation;

}
