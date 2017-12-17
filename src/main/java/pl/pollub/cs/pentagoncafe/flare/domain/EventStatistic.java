package pl.pollub.cs.pentagoncafe.flare.domain;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

/**Model przechowuje informacje statystyczne
 * lista odpowiedzialna jest za przechowywanie przedziałów i statystycznych
 */
@Document(collection = "EventStatistic")
public class EventStatistic {
    /**Variables*/
    @Id
    private ObjectId id;          //id
    private ObjectId eventid;     //id eventu analizowanego
    private List<Term> term;    //lista z przedziałami

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

    public List<Term> getTerm() {
        return term;
    }

    public void setTerm(List<Term> term) {
        this.term = term;
    }
    /**Constructor*/
    public EventStatistic() {
    }

    public EventStatistic(ObjectId eventid, List<Term> term) {

        this.eventid = eventid;
        this.term = term;
    }
}
