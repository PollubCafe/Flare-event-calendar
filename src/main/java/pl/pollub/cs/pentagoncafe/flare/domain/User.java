package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Zawiera informacje o użytkowniku
 */
@Document(collection = "User")
public class User {
    /**Variables*/
    @Id
    private String id;          //id
    private String name;        //imie uczestnika
    private String surname;     //nazwisko uczesnika
    private String email;       //mail
    private String nick;        //login
    private String password;    //hasło

        /**Getter and Setter*/
    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**Constructor*/
    public User() {
    }

    public User(String name, String surname, String email, String nick, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nick = nick;
        this.password = password;
    }
}
