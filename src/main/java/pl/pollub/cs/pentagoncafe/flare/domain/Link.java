package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.UUID;

@Document(collection = "Link")

@Setter
@Getter
public class Link extends BaseEntity{

    @Indexed(unique = true)
    private UUID randomKey = UUID.randomUUID();

    private boolean used;
    @DBRef
    private Event event;
    @DBRef
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        if (!super.equals(o)) return false;
        Link link = (Link) o;
        return Objects.equals(randomKey, link.randomKey);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), randomKey);
    }
}
