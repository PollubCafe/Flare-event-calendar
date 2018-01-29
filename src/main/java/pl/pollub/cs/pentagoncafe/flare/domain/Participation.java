package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "Participation")
public class Participation {
    @Id
    private ObjectId id;
    @DBRef
    private Event event;
    @DBRef
    private User user;

    private List<Vote> votes = new ArrayList<>();
}
