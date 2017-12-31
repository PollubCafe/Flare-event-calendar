package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
import lombok.Data;
import org.bson.types.ObjectId;
import java.util.List;
/**Participant
 * niestety podobną nazwę już stosuję synnonim z translatora do uczestnik
 *
 * Jest to lista przechowująca informacje o tym
 * kto chce wziąć udził w wydarzeniu
 * i kiedy mu pasuje
 * */
@Data
public class Contestants {
    /**Variables*/
    private ObjectId entrant;    //id uczestnika
    private List<Vote>  vote;       //terminy które im pasują
}
