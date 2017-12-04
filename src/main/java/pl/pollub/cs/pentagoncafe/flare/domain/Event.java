package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.List;


@Document(collection = "Event")
public class Event {

    private String id;
    private String Title;
    private String Discribe;
    private String Status;
    private String Place;
    private int Week;
    private int Year;
    private String Duration;
    private String Data;
    private String Creator;
    private boolean OnlyUser;
    private List<Contestants> Contestants;

    private List<User> entrantsList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDiscribe() {
        return Discribe;
    }

    public void setDiscribe(String discribe) {
        Discribe = discribe;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public int getWeek() {
        return Week;
    }

    public void setWeek(int week) {
        Week = week;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getCreator() {
        return Creator;
    }

    public void setCreator(String creator) {
        Creator = creator;
    }

    public boolean isOnlyUser() {
        return OnlyUser;
    }

    public void setOnlyUser(boolean onlyUser) {
        OnlyUser = onlyUser;
    }

    public List<pl.pollub.cs.pentagoncafe.flare.domain.Contestants> getContestants() {
        return Contestants;
    }

    public void setContestants(List<pl.pollub.cs.pentagoncafe.flare.domain.Contestants> contestants) {
        Contestants = contestants;
    }

    /**Constructor*/

    public Event() {

    }

    public Event(String title, String discribe, String status, String place, int week, int year, String duration, String data, String creator, boolean onlyUser, List<Contestants> contestants) {
        Title = title;
        Discribe = discribe;
        Status = status;
        Place = place;
        Week = week;
        Year = year;
        Duration = duration;
        Data = data;
        Creator = creator;
        OnlyUser = onlyUser;
        Contestants = contestants;
    }
}
