package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

import java.util.Date;

/**Lista do Contestants
 * zawiera informacje jakie terminy im odpowiadają*/
public class Vote {
    /**Variables*/
    private int day;        //dzień na który głosuję od 0-poniedziałek 6-niedziela
    private Date from;      //godzina od której może
    private Date to;        //godzina do której możę

    /**Getter and Setter*/

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    /**Constructor*/
    public Vote() {

    }

    public Vote(int day, Date from, Date to) {
        this.day = day;
        this.from = from;
        this.to = to;
    }
}
