package pl.edu.pollub.battleCraft.dataLayer.domain.Address;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Province{
    lubelskie("lubelskie"),
    dolnośląskie("dolnośląskie"),
    małopolskie("małopolskie"),
    śląskie("śląskie"),
    zachodiopomorskie("zachodiopomorskie"),
    wielkopolskie("wielkopolskie"),
    opolskie("opolskie"),
    łódzkie("łódzkie"),
    podlaskie("podlaskie");

    String location;

    Province(String location) {
        this.location = location;
    }

    public static List<String> getNames() {
        return Arrays.stream(Province.values()).map(Enum::name).collect(Collectors.toList());
    }

    public boolean equalsName(String otherName) {
        return location.equals(otherName);
    }
}
