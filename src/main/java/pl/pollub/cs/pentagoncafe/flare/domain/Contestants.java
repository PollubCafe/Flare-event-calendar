package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
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
    private String      enteant;    //id uczestnika
    private List<Vote>  vote;       //terminy które im pasują

    /**Getter and Setter*/
    public String getEnteant() {
        return enteant;
    }

    public void setEnteant(String enteant) {
        this.enteant = enteant;
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

    public Contestants(String enteant, List<Vote> vote) {
        this.enteant = enteant;
        this.vote = vote;
    }
}
