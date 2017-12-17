package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

/**Główna kolekcja przechowuje informacje
 * o wydarzeniu (komentarze przy zmiennych)
 * liste osób które się zgłosiły
 */
@Document(collection = "Event")
public class Event {

    /**Variables*/
    private ObjectId id;                      //id typ bson
    private String title;                   //nazwa
    private String describe;                //opis
    private String status;                  //status wydarzenia
    private String town;                    //miejsce wydarzenia
    private int zipCode;                    //adres miasta bez myślnika po środku
    private String street;                  //nazwa ulicy
    private int bloc;                    //numer bloku String bo w polsce może być np A
    private int houseNumber;             //Numer pokoju mogą wystepować litery np 420J
    private int week;                       //tydzień w roku w którym może się odbyć wydarezenie
    private int year;                       //rok w którym odbędzie się wydarzenie
    private int duration;                  //długość trwania
    private Date date;                      //data do której można się zgłaszać/wysyłać potwierdzenie w zależności od statusu
    private ObjectId creator;                 //id twórcy
    private boolean onlyUser;               //czy wydarzenie tylko dla zarejestrowanych uczestników
    private List<Contestants> contestants;  //lista osób zgłaszających się

    /**Getter and Setter*/
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBloc() {
        return bloc;
    }

    public void setBloc(int bloc) {
        this.bloc = bloc;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ObjectId getCreator() {
        return creator;
    }

    public void setCreator(ObjectId creator) {
        this.creator = creator;
    }

    public boolean isOnlyUser() {
        return onlyUser;
    }

    public void setOnlyUser(boolean onlyUser) {
        this.onlyUser = onlyUser;
    }

    public List<Contestants> getContestants() {
        return contestants;
    }

    public void setContestants(List<Contestants> contestants) {
        this.contestants = contestants;
    }

    /**Constructor*/
    public Event() {
    }

    public Event(String title, String describe,
                 String status, String town, int zipCode,
                 String street, int bloc, int houseNumber, int week,
                 int year, int duration, Date date, ObjectId creator,
                 boolean onlyUser, List<Contestants> contestants) {
        this.title = title;
        this.describe = describe;
        this.status = status;
        this.town = town;
        this.zipCode = zipCode;
        this.street = street;
        this.bloc = bloc;
        this.houseNumber = houseNumber;
        this.week = week;
        this.year = year;
        this.duration = duration;
        this.date = date;
        this.creator = creator;
        this.onlyUser = onlyUser;
        this.contestants = contestants;
    }
}
