package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
/**Lista do EventAna*/
public class Term {
    /**Variables*/
    private int Date;
    private String Time_from;
    private String Time_to;
    private int Count;
    private double perent;

    /**Getter and Setter*/
    public int getDate() {
        return Date;
    }

    public void setDate(int date) {
        Date = date;
    }

    public String getTime_from() {
        return Time_from;
    }

    public void setTime_from(String time_from) {
        Time_from = time_from;
    }

    public String getTime_to() {
        return Time_to;
    }

    public void setTime_to(String time_to) {
        Time_to = time_to;
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

    public Term(int date, String time_from, String time_to, int count, double perent) {
        Date = date;
        Time_from = time_from;
        Time_to = time_to;
        Count = count;

        this.perent = perent;
    }
}
