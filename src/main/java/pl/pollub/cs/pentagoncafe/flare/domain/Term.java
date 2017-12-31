package pl.pollub.cs.pentagoncafe.flare.domain;
/** Twórca: Konrad Gryczko
 *  Data Start 2017/11/29
 */

import lombok.Data;

import java.util.Date;

/**Lista do EventStatistic
 * Zawiera dane statystyczne do wydarzenia
 * przedział czasowy
 * liczbe chętnych
 * procentowy udział*/
@Data
class Term {
    /**Variables*/
    private Date from;        //początek przedziału
    private Date to;          //koniec przedziału
    private int count;          //zliczenie liczby osób
    private double percent;      //wartość procentowa
}
