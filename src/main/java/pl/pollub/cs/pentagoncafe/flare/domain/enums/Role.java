package pl.pollub.cs.pentagoncafe.flare.domain.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum  Role {
    ADMIN("ADMIN"),
    DISABLED("DISABLED"),
    USER("USER");

    String name;

    Role(String name) {
        this.name = name;
    }

    public static List<String> getNames() {
        return Arrays.stream(Province.values()).map(Enum::name).collect(Collectors.toList());
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }
}
