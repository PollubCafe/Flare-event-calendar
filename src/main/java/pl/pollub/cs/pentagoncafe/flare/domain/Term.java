package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Data;

import java.util.Date;

@Data
class Term {
    private Date from;
    private Date to;
    private int count;
    private double percent;
}
