package pl.pollub.cs.pentagoncafe.flare.domain;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "User")
@Data
@EqualsAndHashCode(exclude = "organizedEvents")
@Builder
public class User {

    @Id
    private ObjectId id;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String nick;
    private String password;
    private boolean banned;
    private boolean enabled;
    private Role role;
    private VerificationToken verificationToken;

    @Singular("participation")
    @DBRef
    private List<Participation> participation;

    @Singular("organizedEvents")
    @DBRef
    private List<Event> organizedEvents;
}
