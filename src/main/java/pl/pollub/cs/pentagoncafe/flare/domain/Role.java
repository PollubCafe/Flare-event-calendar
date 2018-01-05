package pl.pollub.cs.pentagoncafe.flare.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum  Role {
    admin("ADMIN"),
    user("USER");

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
