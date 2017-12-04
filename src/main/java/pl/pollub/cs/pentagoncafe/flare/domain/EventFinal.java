package pl.pollub.cs.pentagoncafe.flare.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

/** Model event Final
 * zawiera informacje kiedy twórca chce przeprowadzić wydarzenie
 * oraz listę osób co mogą wziąść udział w wydarzeniu */
@Document(collection = "EventFinal")
public class EventFinal {
    /**Variables*/
    @Id
    private String id;                          //id
    private String eventid;                     //id Wydarzenia
    private Date data;                          //ostateczna data wydarzenia
    private String hour;                        //godzina wydarzenia
    private List<Participants> participants;    //lista kandydatów/osób które zaakceptowały

    /**Getter and Setter*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public List<Participants> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participants> participants) {
        this.participants = participants;
    }


    /**Constructor*/

    public EventFinal() {
    }


    public EventFinal(String eventid, Date data, String hour, List<Participants> participants) {

        this.eventid = eventid;
        this.data = data;
        this.hour = hour;
        this.participants = participants;
    }
}
