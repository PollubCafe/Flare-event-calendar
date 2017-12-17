package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
import org.bson.types.ObjectId;
import java.util.List;
/**Participant
 * niestety podobną nazwę już stosuję synnonim z translatora do uczestnik
 *
 * Jest to lista przechowująca informacje o tym
 * kto chce wziąć udził w wydarzeniu
 * i kiedy mu pasuje
 * */
public class Contestants {
    /**Variables*/
    private ObjectId entrant;    //id uczestnika
    private List<Vote>  vote;       //terminy które im pasują

    /**Getter and Setter*/
    public ObjectId getEntrant() {
        return entrant;
    }

    public void setEntrant(ObjectId enteant) {
        this.entrant = enteant;
    }

    public List<Vote> getVote() {
        return vote;
    }

    public void setVote(List<Vote> vote) {
        this.vote = vote;
    }

    /**Constructor*/
    public Contestants() {

    }

    public Contestants(ObjectId entrant, List<Vote> vote) {
        this.entrant = entrant;
        this.vote = vote;
    }
}
