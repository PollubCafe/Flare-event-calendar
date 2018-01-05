package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Data
public class Vote {
    private int dayOfWeek;
    private LocalTime from;
    private LocalTime to;

    public Vote(int dayOfWeek, LocalTime from, LocalTime to) {
        this.dayOfWeek = dayOfWeek;
        this.from = from;
        this.to = to;
    }
}
