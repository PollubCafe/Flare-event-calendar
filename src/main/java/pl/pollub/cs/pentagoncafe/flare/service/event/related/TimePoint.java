package pl.pollub.cs.pentagoncafe.flare.service.event.related;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class TimePoint implements Comparable<TimePoint>{
    private Integer dayOfTheWeek;
    private LocalTime time;
    private TimePointType type;

    @Override
    public int compareTo(TimePoint o) {
        int weekDayCompare = getDayOfTheWeek().compareTo(o.getDayOfTheWeek());
        return weekDayCompare==0 ? time.compareTo(o.time) : weekDayCompare;
    }
}
