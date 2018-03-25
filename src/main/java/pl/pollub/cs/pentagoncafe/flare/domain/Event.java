package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document(collection = "Event")


@Setter
@Getter
@Builder
public class Event extends BaseEntity{
    private String title;
    private String description;
    private int duration;
    private Instant dateOfEventApproval;
    private boolean isApproved;
    private boolean onlyForRegisteredUsers;
    private String imageURL;
    private Instant dateOfCreation;
    private Address address;
    @DBRef
    private User organizer;
    @Singular("links")
    @DBRef
    private Set<Link> links;
    @Singular("eventStatistic")
    private Set<Term> eventStatistic;
    @Singular("participation")
    @DBRef
    private Set<Participation> participation;
}
