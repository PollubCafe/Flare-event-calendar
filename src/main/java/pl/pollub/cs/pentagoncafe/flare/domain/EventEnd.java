package pl.pollub.cs.pentagoncafe.flare.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
@Document(collection = "EventEnd")
public class EventEnd {
    /**Variables*/
    @Id
    private String id;
    private String Event_id;
    private String Data;
    private String Hour;
    private List<Participants> Participants;

    /**Getter and Setter*/
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvent_id() {
        return Event_id;
    }

    public void setEvent_id(String event_id) {
        Event_id = event_id;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getHour() {
        return Hour;
    }

    public void setHour(String hour) {
        Hour = hour;
    }

    public List<Participants> getParticipants() {
        return Participants;
    }

    public void setParticipants(List<Participants> participants) {
        Participants = participants;
    }

    /**Constructor*/
    public EventEnd() {

    }

    public EventEnd(String event_id, String data, String hour, List<Participants> participants) {
        Event_id = event_id;
        Data = data;
        Hour = hour;
        Participants = participants;
    }
}
