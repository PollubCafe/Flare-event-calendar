package pl.pollub.cs.pentagoncafe.flare.domain;

@Deprecated
public class Stats {

    private Event event;

    public Stats(Event event) {
        this.event = event;
    }

    public Stats getStatistics() {
        return this;
    }
}
