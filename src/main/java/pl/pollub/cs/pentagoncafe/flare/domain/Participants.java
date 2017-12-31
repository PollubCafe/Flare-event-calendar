package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

import lombok.Data;
import org.bson.types.ObjectId;

/** Lista terminów EventFinal
 *  Zawiera informacje o tym
 *  kto się zgłosił na wydarzenie
 *  czy zaakceptował zgłoszenie
 *
 *  Przy ostatnim statusie zawiera liste osówb które na koniec wezmą udział w wydarzeniu
 *  */
@Data
public class Participants {
    /**Variables*/
    private ObjectId entrant;     //id osoby zgłaszającej się
    private boolean didAccept;  //czy osoba potwierdziła swoją obecność
}
