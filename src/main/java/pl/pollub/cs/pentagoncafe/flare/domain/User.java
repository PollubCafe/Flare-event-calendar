package pl.pollub.cs.pentagoncafe.flare.domain;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.pollub.cs.pentagoncafe.flare.domain.enums.Role;

import java.util.Set;

@Document(collection = "User")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User extends BaseEntity{
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String nick;
    private String password;
    private boolean banned;
    private boolean enabled;
    private Role role;
    private ActivationToken activationToken;

    @Singular("participation")
    @DBRef
    private Set<Participation> participation;

    @Singular("organizedEvents")
    @DBRef
    private Set<Event> organizedEvents;

    public void addEvent(Event event){
        if(organizedEvents.contains(event))
            throw new IllegalArgumentException("User: "+nick+" already has event "+event.id);

        event.setOrganizer(this);
        organizedEvents.add(event);
    }
}
