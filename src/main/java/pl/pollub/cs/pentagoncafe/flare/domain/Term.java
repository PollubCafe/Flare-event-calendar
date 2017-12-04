package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
/**Lista do EventAna*/
public class Term {
    /**Variables*/
    private int Date;
    private String From;
    private String To;
    private int Count;
    private double perent;

    /**Getter and Setter*/

    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    public String getFrom() {
        return From;
    }

    public void setFrom(String from) {
        From = from;
    }

    public String getTo() {
        return To;
    }

    public void setTto(String to) {
        To = to;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public double getPerent() {
        return perent;
    }

    public void setPerent(double perent) {
        this.perent = perent;
    }

    /**Constructor*/
    public Term() {

    }

    public Term(int date, String from, String to, int count, double perent) {
        Date = date;
        From = from;
        To = to;
        Count = count;

        this.perent = perent;
    }
}
