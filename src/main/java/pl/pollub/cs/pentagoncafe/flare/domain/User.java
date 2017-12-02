package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
public class User {
    /**Variables*/
    @Id
    private String id;
    private String Name;
    private String Surname;
    private String E_mail;
    private String Nick;
    private String Password;

    /**Getter and Setter*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getE_mail() {
        return E_mail;
    }

    public void setE_mail(String e_mail) {
        E_mail = e_mail;
    }

    public String getNick() {
        return Nick;
    }

    public void setNick(String nick) {
        Nick = nick;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    /**Constructor*/
    public User() {

    }

    public User(String name, String surname, String e_mail, String nick, String password) {

        Name = name;
        Surname = surname;
        E_mail = e_mail;
        Nick = nick;
        Password = password;
    }
}
