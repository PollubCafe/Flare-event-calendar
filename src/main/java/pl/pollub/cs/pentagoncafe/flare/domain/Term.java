package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

import java.util.Date;

/**Lista do EventStatistic
 * Zawiera dane statystyczne do wydarzenia
 * przedział czasowy
 * liczbe chętnych
 * procentowy udział*/
public class Term {
    /**Variables*/
    private Date from;        //początek przedziału
    private Date to;          //koniec przedziału
    private int count;          //zliczenie liczby osób
    private double percent;      //wartość procentowa

    /**Getter and Setter*/
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    /**Constructor*/

    public Term(Date from, Date to, int count, double percent) {
        this.from = from;
        this.to = to;
        this.count = count;
        this.percent = percent;
    }

    public Term() {
    }
}
