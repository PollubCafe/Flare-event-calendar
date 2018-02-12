package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Data;

import java.time.Instant;

@Data
class Term {
    private Instant from;
    private Instant to;
    private int count;
    private double percent;
}
