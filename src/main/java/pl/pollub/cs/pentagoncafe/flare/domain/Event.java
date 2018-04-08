package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.EventStatus;


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
    private Instant dateOfEndRegistration;
    private EventStatus status;
    private boolean banned;
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
