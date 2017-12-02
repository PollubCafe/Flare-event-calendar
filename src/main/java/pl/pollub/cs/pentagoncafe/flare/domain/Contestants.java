package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
import java.util.List;

public class Contestants {
    /**Variables*/
    private String Enteant;
    private List<Vote> Vote;

    /**Getter and Setter*/
    public String getEnteant() {
        return Enteant;
    }

    public void setEnteant(String enteant) {
        Enteant = enteant;
    }

    public List<Vote> getVote() {
        return Vote;
    }

    public void setVote(List<Vote> vote) {
        Vote = vote;
    }

    /**Constructor*/
    public Contestants() {

    }

    public Contestants(String enteant, List<Vote> vote) {
        Enteant = enteant;
        Vote = vote;
    }
}
