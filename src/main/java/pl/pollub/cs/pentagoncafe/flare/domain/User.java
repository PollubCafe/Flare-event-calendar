package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Zawiera informacje o użytkowniku
 */

@Document(collection = "User")
@Data
public class User {
    /**Variables*/
    @Id
    private ObjectId id;          //id
    private String name;        //imie uczestnika
    private String surname;     //nazwisko uczesnika
    private String email;       //mail
    private String nick;        //login
    private String password;    //hasło

    public User(String name, String surname, String email, String nick, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nick = nick;
        this.password = password;
    }
}
