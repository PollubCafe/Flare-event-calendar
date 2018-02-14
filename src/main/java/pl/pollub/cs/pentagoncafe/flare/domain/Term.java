package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
@AllArgsConstructor
public class Term {
    private LocalTime from;
    private LocalTime to;
    private Integer count;
    private Integer dayOfTheWeek;
    private Double percent;
}
