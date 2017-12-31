package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

import lombok.Data;

import java.util.Date;

/**Lista do Contestants
 * zawiera informacje jakie terminy im odpowiadają*/
@Data
public class Vote {
    /**Variables*/
    private int day;        //dzień na który głosuję od 0-poniedziałek 6-niedziela
    private Date from;      //godzina od której może
    private Date to;        //godzina do której możę

    public Vote(int day, Date from, Date to) {
        this.day = day;
        this.from = from;
        this.to = to;
    }
}
