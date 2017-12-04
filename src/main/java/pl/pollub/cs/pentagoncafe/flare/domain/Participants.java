package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
/** Lista terminów EventFinal
 *  Zawiera informacje o tym
 *  kto się zgłosił na wydarzenie
 *  czy zaakceptował zgłoszenie
 *
 *  Przy ostatnim statusie zawiera liste osówb które na koniec wezmą udział w wydarzeniu
 *  */
public class Participants {
    /**Variables*/
    private String  entrant;     //id osoby zgłaszającej się
    private boolean didAccept;  //czy osoba potwierdziła swoją obecność

    /**Getter and Setter*/
    public String getEntrant() {
        return entrant;
    }

    public void setEntrant(String entrant) {
        this.entrant = entrant;
    }

    public boolean isDidAccept() {
        return didAccept;
    }

    public void setDidAccept(boolean didAccept) {
        this.didAccept = didAccept;
    }

    /**Constructor*/

    public Participants() {
    }

    public Participants(String entrant, boolean didAccept) {
        this.entrant = entrant;
        this.didAccept = didAccept;
    }
}
