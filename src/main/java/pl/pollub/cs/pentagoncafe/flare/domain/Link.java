package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Link")
@Data
public class Link {
    @Id
    private ObjectId id;
    @DBRef
    private Event event;
    @DBRef
    private User user;
    private boolean used;

}
