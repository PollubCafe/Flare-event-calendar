package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Document(collection = "Vote")
@Data
public class Vote {
    @Id
    private ObjectId id;
    @DBRef
    private Event event;
    @DBRef
    private User user;

    private int dayOfWeek;
    private LocalTime from;
    private LocalTime to;

    public Vote(Event event, User user, int dayOfWeek, LocalTime from, LocalTime to) {
        this.event = event;
        this.user = user;
        this.dayOfWeek = dayOfWeek;
        this.from = from;
        this.to = to;
    }
}
