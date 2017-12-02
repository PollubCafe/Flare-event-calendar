package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */
/**Lista do EventAna Contestants*/
public class Vote {
    /**Variables*/
    private int Day;
    private String From;
    private String To;

    /**Getter and Setter*/
    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        Day = day;
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

    public void setTo(String to) {
        To = to;
    }

    /**Constructor*/
    public Vote() {

    }

    public Vote(int day, String from, String to) {
        Day = day;
        From = from;
        To = to;
    }
}
