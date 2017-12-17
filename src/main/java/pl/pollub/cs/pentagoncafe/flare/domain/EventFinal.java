package pl.pollub.cs.pentagoncafe.flare.domain;

import org.bson.types.ObjectId;
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
    private ObjectId id;                          //id
    private ObjectId eventid;                     //id Wydarzenia
    private Date data;                          //ostateczna data wydarzenia
    private String hour;                        //godzina wydarzenia
    private List<Participants> participants;    //lista kandydatów/osób które zaakceptowały

    /**Getter and Setter*/
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getEventid() {
        return eventid;
    }

    public void setEventid(ObjectId eventid) {
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


    public EventFinal(ObjectId eventid, Date data,
                      String hour, List<Participants> participants) {

        this.eventid = eventid;
        this.data = data;
        this.hour = hour;
        this.participants = participants;
    }
}
