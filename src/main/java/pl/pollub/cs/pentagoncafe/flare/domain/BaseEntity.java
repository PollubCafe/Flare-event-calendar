package pl.pollub.cs.pentagoncafe.flare.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Objects;
import java.util.UUID;

@Setter
@Getter
public abstract class BaseEntity {
    @Id
    protected ObjectId id;

    @Indexed(unique = true)
    private UUID uuid = UUID.randomUUID();

    @Override
    public boolean equals(final Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        final BaseEntity baseEntity = (BaseEntity) o;
        return Objects.equals(uuid,baseEntity.uuid);
    }

    @Override
    public int hashCode(){
        return Objects.hash(uuid);
    }
}
