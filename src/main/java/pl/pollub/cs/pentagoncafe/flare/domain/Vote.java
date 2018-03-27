package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Data;

import java.time.LocalTime;

@Data
public class Vote {
    private int dayOfWeek;
    private LocalTime from;
    private LocalTime to;
}
