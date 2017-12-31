package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
import lombok.Builder;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**Główna kolekcja przechowuje informacje
 * o wydarzeniu (komentarze przy zmiennych)
 * liste osób które się zgłosiły
 */
@Document(collection = "Event")
@Data
@Builder
public class Event {
    @Id
    private ObjectId id;                      //id typ bson

    private User creator;                 //twórca
    private String title;                   //nazwa
    private String description;                //opis
    private String status;                  //status wydarzenia
    private String town;                    //miejsce wydarzenia
    private int zipCode;                    //adres miasta bez myślnika po środku
    private String street;                  //nazwa ulicy
    private int bloc;                    //numer bloku String bo w polsce może być np A
    private int houseNumber;             //Numer pokoju mogą wystepować litery np 420J
    private int week;                       //tydzień w roku w którym może się odbyć wydarezenie
    private int year;                       //rok w którym odbędzie się wydarzenie
    private int duration;                  //długość trwania
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date date;                      //data do której można się zgłaszać/wysyłać potwierdzenie w zależności od statusu
    private boolean onlyForRegisteredUsers;               //czy wydarzenie tylko dla zarejestrowanych uczestników
    private List<Contestants> contestants;  //lista osób zgłaszających się
}
