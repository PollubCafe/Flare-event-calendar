package pl.pollub.cs.pentagoncafe.flare.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
/*Stat*/
@Document(collection = "EventAna")
public class EventAna {
    /**Variables*/
    @Id
    private String id;
    private String Eventid;
    private List<Term> Term;

    /**Getter and Setter*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventid() {
        return Eventid;
    }

    public void setEventid(String eventid) {
        Eventid = eventid;
    }

    public List<pl.pollub.cs.pentagoncafe.flare.domain.Term> getTerm() {
        return Term;
    }

    public void setTerm(List<pl.pollub.cs.pentagoncafe.flare.domain.Term> term) {
        Term = term;
    }

    /**Constructor*/
    public EventAna() {

    }

    public EventAna(String eventid, List<Term> term) {
        Eventid = eventid;
        Term = term;
    }
}
