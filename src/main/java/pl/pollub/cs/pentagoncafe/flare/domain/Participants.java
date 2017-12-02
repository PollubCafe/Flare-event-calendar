package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
/** Lista terminów EventEnd*/
public class Participants {
    /**Variables*/
    private String Aspirant;
    private boolean DidAccept;
    /**Getter and Setter*/
    public String getAspirant() {
        return Aspirant;
    }

    public void setAspirant(String aspirant) {
        Aspirant = aspirant;
    }

    public boolean isDidAccept() {
        return DidAccept;
    }

    public void setDidAccept(boolean didAccept) {
        DidAccept = didAccept;
    }

    /**Constructor*/
    public Participants() {

    }

    public Participants(String aspirant, boolean didAccept) {
        Aspirant = aspirant;

        DidAccept = didAccept;
    }
}
