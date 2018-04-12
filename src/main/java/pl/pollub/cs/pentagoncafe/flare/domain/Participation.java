package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document(collection = "Participation")

@Setter
@Getter
public class Participation extends BaseEntity{
    @DBRef
    private Event event;
    @DBRef
    private User user;
    private Set<Vote> votes = new HashSet<>();
}
