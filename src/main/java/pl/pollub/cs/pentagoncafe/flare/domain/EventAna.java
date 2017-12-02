package pl.pollub.cs.pentagoncafe.flare.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

@Document(collection = "EventAna")
public class EventAna {
    /**Variables*/
    @Id
    private String id;
    private String Event_id;
    private List<Term> Term;

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

    public List<Term> getTerm() {
        return Term;
    }

    public void setTerm(List<Term> term) {
        Term = term;
    }

    /**Constructor*/
    public EventAna() {

    }

    public EventAna(String event_id, List<Term> term) {
        Event_id = event_id;
        Term = term;
    }
}
