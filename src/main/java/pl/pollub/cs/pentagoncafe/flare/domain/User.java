package pl.pollub.cs.pentagoncafe.flare.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;
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
public class User {

    @Id
    private ObjectId id;
    private String name;
    private String surname;
    private String email;
    private String nick;
    private String password;

    @DBRef
    private List<Vote> votes = new ArrayList<>();

    @DBRef
    private List<Event> organizedEvents = new ArrayList<>();

    public User(String name, String surname, String email, String nick, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nick = nick;
        this.password = password;
    }
}
