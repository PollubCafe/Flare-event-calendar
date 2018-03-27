package pl.pollub.cs.pentagoncafe.flare.domain.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EventStatus {
    NEW("new"),
    CLOSED("closed"),
    REGISTRATION("registration"),
    APPROVED("approved");

    String name;

    EventStatus(String name) {
        this.name = name;
    }

    public static List<String> getNames(){
        return Arrays.stream(EventStatus.values()).map(Enum::name).collect(Collectors.toList());
    }

    public boolean equalsName(String otherName){
        return name.equals(otherName);
    }
}
